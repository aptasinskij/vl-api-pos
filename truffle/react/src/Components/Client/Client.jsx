import React, {Component} from 'react';
import TruffleContract from 'truffle-contract';
/*import VaultLogicPlatform from '../../../build/contracts/VaultLogicPlatform.json';
import CapitalHero from '../../../build/contracts/CapitalHero.json';*/
import {connect} from 'react-redux';
import {clientActions, generalActions} from '../../Actions/index';
import Web3 from 'web3';
import {Timer} from '../../Utils/index';
import {Header} from '../Header/Header';

import {Container, Segment, Button, Label, Loader, Dimmer, Table} from 'semantic-ui-react';
import './Client.css'

class Client extends Component {

    // set web3Provider to Component and account-address to reducer
    establishConfiguration = async () => {
        const {setAccountAddress} = this.props;
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
                setAccountAddress(account);
            }
        });
    };

    // set contract instances to reducer
    establishContracts = async () => {
        const {web3Provider} = this;
        const {setContractsInstances} = this.props;

        this.VaultLogicPlatform = TruffleContract(VaultLogicPlatform);
        this.VaultLogicPlatform.setProvider(web3Provider);
        let VaultLogicPlatformInstance = await this.VaultLogicPlatform.deployed();


        this.CapitalHero = TruffleContract(CapitalHero);
        this.CapitalHero.setProvider(web3Provider);
        let CapitalHeroInstance = await this.CapitalHero.deployed();

        setContractsInstances({
            VaultLogicPlatformInstance,
            CapitalHeroInstance
        })
    };

    async componentDidMount() {
        const {establishContracts, establishConfiguration, watchEvents} = this;

        await establishConfiguration();
        await establishContracts();
        watchEvents();
    }

    getAllChannels = async CapitalHeroInstance => {
        if (CapitalHeroInstance) {
            let array = [];
            let count = await CapitalHeroInstance.channelsCounter();
            let counter = count.toNumber();

            for (let i = 1; i <= counter; i++) {
                let response = await CapitalHeroInstance.channelIdChannel(i);
                array.push({
                    amount: response[0].toNumber(),
                    isOpen: response[1]
                });

            }
            console.log('array', array);
        }
    };

    componentDidUpdate(prevProps) {
        const {requestIsActive, requestIsActiveMetaMask, requestType} = this.props;
        const {CapitalHeroInstance} = this.props.contractsInstances;
        const {metaMask} = this;

        if (!metaMask && requestIsActive && requestIsActive !== prevProps.requestIsActive) {
            // start prod timer
            console.log('start prod timer');
            Timer.start(requestType);
        }
        if (metaMask && requestIsActiveMetaMask && requestIsActiveMetaMask !== prevProps.requestIsActiveMetaMask) {
            // start timer for metaMask
            console.log('start timer for metaMask');
            Timer.start(requestType);
        }

        /*this.getAllChannels(CapitalHeroInstance)*/
    }

    watchEvents = () => {
        const {VaultLogicPlatformInstance, CapitalHeroInstance} = this.props.contractsInstances;
        const {clearRequestType} = this.props;

        VaultLogicPlatformInstance
            .CashInChannelOpened({}, {
                fromBlock: 'latest',
                toBlock: 'latest'
            })
            .watch((error, data) => {
                const {channels, saveChannels} = this.props;
                let resultChannels = channels.map(item => item);

                if (Timer.isActive()) {
                    Timer.stop();
                    clearRequestType();
                }

                if (data) {
                    /*console.log('CashInChannelOpened event data', data);*/

                    let newChannelID = data.args._channelId.toNumber();

                    if (resultChannels.length === 0) { // if no channels
                        resultChannels.push({
                            channelID: newChannelID,
                            amount: 0,
                            open: true
                        })
                    } else if (resultChannels.length > 0) {
                        let channelNotExist = true;
                        resultChannels.forEach(item => {
                            if (item.channelID === newChannelID) { // if channel exist
                                item.amount = 0;
                                item.open = true;
                                channelNotExist = false;
                            }
                        });
                        if (channelNotExist) { // if channel not exist
                            resultChannels.push({
                                channelID: newChannelID,
                                amount: 0,
                                open: true
                            })
                        }
                    }

                    saveChannels(resultChannels)
                } else {
                    console.error('error', error);
                }
            });

        VaultLogicPlatformInstance
            .CashInChannelClosed({}, {
                fromBlock: 'latest',
                toBlock: 'latest'
            })
            .watch((error, data) => {
                const {channels, saveChannels} = this.props;
                let resultChannels = channels.map(item => item);

                if (Timer.isActive()) {
                    Timer.stop();
                    clearRequestType();
                }

                if (data) {

                    /*console.log('CashInChannelClosed event data', data);*/

                    let newChannelID = data.args._channelId.toNumber();
                    let newAmount = data.args._amount.toNumber();

                    resultChannels.forEach(item => {
                        if (item.channelID === newChannelID) {
                            item.amount = newAmount;
                            item.open = false;
                        }
                    });

                    saveChannels(resultChannels);
                } else {
                    console.error('error', error);
                }
            });

        CapitalHeroInstance
            .ChannelMoneyInserted({}, {
                fromBlock: 'latest',
                toBlock: 'latest'
            })
            .watch((error, data) => {

                if (data) {
                    const {channels, saveChannels} = this.props;
                    let resultChannels = channels.map(item => item);

                    let channelID = data.args._channelId.toNumber();
                    let newAmount = data.args._value.toNumber();

                    resultChannels.forEach(item => {
                        if (item.channelID === channelID) {
                            item.amount += newAmount;
                        }
                    });
                    saveChannels(resultChannels);
                } else {
                    console.error(error);
                }
            })
    };

    render() {
        const {account, channels, closeCashChannel, openCashChannel, requestIsActiveMetaMask} = this.props;
        const {CapitalHeroInstance} = this.props.contractsInstances;

        let channelID; // id of last channel
        let channelIsOpen; // open prop of last channel
        if (channels.length) {
            channelID = channels[channels.length - 1].channelID;
            channelIsOpen = channels[channels.length - 1].open;
        }

        return (
            <div className="client-dashboard">
                <Header/>
                <Container>
                    <Segment.Group>
                        <Dimmer active={requestIsActiveMetaMask}>
                            <Loader
                                content='Waiting for event...'
                                active={requestIsActiveMetaMask}/>
                        </Dimmer>
                        <Segment
                            textAlign='center'
                            padded='very'>
                            <div className="operations-label">
                                <Label
                                    size="large"
                                    content='CashInChannel'/>
                            </div>
                            <Button.Group className="control-buttons">
                                <Button
                                    disabled={channelIsOpen || requestIsActiveMetaMask}
                                    onClick={() => openCashChannel({CapitalHeroInstance, account})}
                                    content='Open'
                                    color='teal'>
                                </Button>
                                <Button.Or/>
                                <Button
                                    disabled={!channelIsOpen || !channels.length || requestIsActiveMetaMask}
                                    onClick={() => closeCashChannel({CapitalHeroInstance, account, channelID})}
                                    content='Close'
                                    color='red'>
                                </Button>
                            </Button.Group>
                        </Segment>

                        {channels && channels.length ? (
                            <Segment
                                padded>
                                <Table
                                    selectable>
                                    <Table.Header>
                                        <Table.Row>
                                            <Table.HeaderCell>Channel ID</Table.HeaderCell>
                                            <Table.HeaderCell>Amount</Table.HeaderCell>
                                            <Table.HeaderCell>Open</Table.HeaderCell>
                                        </Table.Row>
                                    </Table.Header>
                                    <Table.Body>
                                    {channels.map((channel, index) => (
                                        <Table.Row
                                            key={index}
                                            positive={index === (channels.length - 1) && channel.open}>
                                            <Table.Cell>{channel.channelID}</Table.Cell>
                                            <Table.Cell>{channel.amount}</Table.Cell>
                                            <Table.Cell>{channel.open.toString()}</Table.Cell>
                                        </Table.Row>
                                    ))}
                                    </Table.Body>
                                </Table>
                            </Segment>
                        ) : ('')}
                    </Segment.Group>
                </Container>
            </div>
        )
    }
}

const connectedClient = connect(state => {
    const {account, contractsInstances, channels, requestIsActive, requestIsActiveMetaMask, requestType} = state.clientReducer;
    return {
        account,
        contractsInstances,
        channels,
        requestIsActive,
        requestIsActiveMetaMask,
        requestType
    };
}, {
    openCashChannel: clientActions.openCashChannel,
    closeCashChannel: clientActions.closeCashChannel,
    setAccountAddress: clientActions.setAccountAddress,
    setContractsInstances: clientActions.setContractsInstances,
    saveChannels: clientActions.saveChannels,
    clearRequestType: generalActions.clearRequestType
})(Client);

export default Client = connectedClient;