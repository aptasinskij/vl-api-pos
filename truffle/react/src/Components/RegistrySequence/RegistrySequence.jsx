import React, {Component} from 'react';
import Web3 from 'web3';
import TruffleContract from 'truffle-contract';
import ContractRegistry from '../../../../../back-end/src/main/java/com/skysoft/vaultlogic/blockchain/truffle/build/contracts/ContractRegistry.json';
import {Header} from '../Header/Header';

import {Button, Radio, Container, Label, Segment, Table, Message, Transition} from 'semantic-ui-react';
import './RegistrySequence.css';

class RegistrySequence extends Component {

    constructor(props) {
        super(props);
        this.state = {
            ContractRegistryInstance: null,
            components: [],
            account: '',
            messageShow: false
        }
    }

    // set web3Provider to Component and account-address to reducer
    establishConfiguration = async () => {
        if (typeof web3 !== 'undefined') {
            this.web3Provider = web3.currentProvider;
            this.metaMask = true;
        } else {
            this.web3Provider = new Web3.providers.HttpProvider('http://localhost:7545');
            this.metaMask = false;
        }

        this.web3 = new Web3(this.web3Provider);
        await this.web3.eth.getCoinbase((err, account) => {
            if (err === null) {
                this.setState({
                    account
                })
            }
        });
    };

    // set contract instances to reducer
    establishContracts = async () => {
        const {web3Provider} = this;

        this.ContractRegistry = TruffleContract(ContractRegistry);
        this.ContractRegistry.setProvider(web3Provider);
        const ContractRegistryInstance = await this.ContractRegistry.deployed();

        this.setState({
            ContractRegistryInstance
        })
    };

    async componentDidMount() {
        const {establishContracts, establishConfiguration, watchEvents, getAllContracts} = this;

        await establishConfiguration();
        await establishContracts();
        watchEvents();
        await getAllContracts();

        this.updateInfoInterval = setInterval(getAllContracts, 5000);
    }

    componentWillUnmount() {
        clearInterval(this.updateInfoInterval)
    }

    getAllContracts = async () => {
        const {ContractRegistryInstance} = this.state;
        // get components counter
        const counter = Number(await ContractRegistryInstance.numberOfComponents());
        //get components properties array
        let components = [];
        for (let i = 0; i < counter; i++) {
            // names
            components.push({
                name: await ContractRegistryInstance.names(i)
            });
            // state
            components[i].enabled = await ContractRegistryInstance.getState(components[i].name);
            // address
            components[i].address = await ContractRegistryInstance.get(components[i].name);
        }
        this.setState({
            components,
            messageShow: true
        });
        setTimeout(() => {
            this.setState({
                messageShow: false
            })
        }, 1500)
    };

    watchEvents = () => {
        const {ContractRegistryInstance} = this.state;
        const {getAllContracts} = this;

        ContractRegistryInstance
            .ComponentRegistered({}, {
                fromBlock: 'latest',
                toBlock: 'latest'
            })
            .watch((error, data) => {
                if (data) console.log('ComponentRegistered event catch', data);
                else console.error('error to catch ComponentRegistered event', error);

                getAllContracts();
            });

        ContractRegistryInstance
            .ComponentAddressUpdated({}, {
                fromBlock: 'latest',
                toBlock: 'latest'
            })
            .watch((error, data) => {
                if (data) console.log('ComponentAddressUpdated event catch', data);
                else console.error('error to catch ComponentAddressUpdated event', error);

                getAllContracts();
            });
    };

    changeContractState = async (contractName, event, data) => {
        const {ContractRegistryInstance, account} = this.state;

        if (data.checked) {
            await ContractRegistryInstance.enable(contractName, {from: account, gas: 2000000});
        } else {
            await ContractRegistryInstance.disable(contractName, {from: account, gas: 2000000});
        }

        /*this.setState({
            components: components.map(item => item.name === contractName ? {...item, enabled: data.checked} : item)
        })*/
    };

    refreshInfo = async contractIndex => {
        const {components, ContractRegistryInstance} = this.state;

        //get contract info
        const name = await ContractRegistryInstance.names(contractIndex);
        const enabled = await ContractRegistryInstance.getState(name);
        const address = await ContractRegistryInstance.get(name);

        this.setState({
            components: components.map((item, index) => index === contractIndex ? {...item, name, enabled, address} : item),
            messageShow: true
        });
        setTimeout(() => {
            this.setState({
                messageShow: false
            })
        }, 1000)

    };

    render() {
        const {components, messageShow} = this.state;
        const {changeContractState, refreshInfo, getAllContracts} = this;

        return (
            <div className="registry-sequence">
                <Header/>
                <Container>
                    {components && components.length ? (
                        <Segment
                            padded>
                            <div className="table-describe-block">
                                <Label
                                    size='big'
                                    content='Contracts'>
                                </Label>
                                <Button
                                    className="refresh-contracts-button"
                                    onClick={getAllContracts}
                                    color='blue'
                                    compact
                                    circular
                                    icon='refresh'/>
                                <Transition
                                    visible={messageShow}
                                    animation='scale'
                                    duration={1000}>
                                    <Message
                                        className='message-block'
                                        size='mini'
                                        compact>
                                        <span>Information updated</span>
                                    </Message>
                                </Transition>
                            </div>
                            <Table
                                selectable>
                                <Table.Header>
                                    <Table.Row>
                                        <Table.HeaderCell>Index</Table.HeaderCell>
                                        <Table.HeaderCell>Name</Table.HeaderCell>
                                        <Table.HeaderCell>Address</Table.HeaderCell>
                                        <Table.HeaderCell>State</Table.HeaderCell>
                                        <Table.HeaderCell>Refresh info</Table.HeaderCell>
                                    </Table.Row>
                                </Table.Header>
                                <Table.Body>
                                    {components.map((item, index) => (
                                        <Table.Row
                                            key={index}>
                                            <Table.Cell>{index}</Table.Cell>
                                            <Table.Cell>{item.name}</Table.Cell>
                                            <Table.Cell>{item.address}</Table.Cell>
                                            <Table.Cell>
                                                <Radio
                                                    onChange={(event, data) => changeContractState(item.name, event, data)}
                                                    checked={item.enabled}
                                                    toggle/>
                                            </Table.Cell>
                                            <Table.Cell>
                                                <Button
                                                    onClick={() => refreshInfo(index)}
                                                    color='blue'
                                                    compact
                                                    circular
                                                    icon='refresh'/>
                                            </Table.Cell>
                                        </Table.Row>
                                    ))}
                                </Table.Body>
                            </Table>
                        </Segment>
                    ) : ('')}
                </Container>
            </div>
        )
    }
}

export default RegistrySequence;