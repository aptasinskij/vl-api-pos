import React, {Component} from 'react';
import Web3 from 'web3';
import TruffleContract from 'truffle-contract';
/*import VaultLogicPlatform from '../../../build/contracts/VaultLogicPlatform.json';
import CapitalHero from '../../../build/contracts/CapitalHero.json';*/
import {adminActions, generalActions} from '../../Actions';
import {connect} from 'react-redux';
import {Timer} from '../../Utils';
import {Header} from '../Header/Header';

import {Container, Segment, Button, Label, Loader, Dimmer, Input} from 'semantic-ui-react';
import './Admin.css';

class Admin extends Component {
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
        await watchEvents();
    }

    componentDidUpdate(prevProps) {
        const {requestIsActive, requestIsActiveMetaMask, requestType} = this.props;
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
    }

    changeMoney = event => {
        this.props.changeMoney(event.target.value);
    };

    watchEvents = () => {
        const {VaultLogicPlatformInstance, CapitalHeroInstance} = this.props.contractsInstances;
        const {clearRequestType} = this.props;

        VaultLogicPlatformInstance
            .CashInChannelOpened({}, {
            fromBlock: 'latest',
            toBlock: 'latest'
        })
            .watch((error, data) => {
                const {setChannelID} = this.props;

            if (data) {
                console.log('CashInChannelOpened for admin', data);
                let channelID = data.args._channelId.toNumber();

                setChannelID(channelID);

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
                const {clearChannelID} = this.props;

            if (data) {
                console.log('CashInChannelClosed for admin', data);

                clearChannelID();
            } else {
                console.error('error', error);
            }
        });

        CapitalHeroInstance
            .ChannelMoneyInserted({}, {
                fromBlock: 'latest',
                toBlock: 'latest'
            })
            .watch(() => {
                if (Timer.isActive()) {
                    Timer.stop();
                    clearRequestType();
                }
            })
    };

    render(){
        const {changeMoney} = this;
        const {VaultLogicPlatformInstance} = this.props.contractsInstances;
        const {increaseMoneyToTen, moneyValue, channelID, account, disabledCashChannel, insertMoney, requestIsActiveMetaMask} = this.props;

        return (
            <div className="admin-dashboard">
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
                            <div className="input-container">
                                <Input
                                    min="0"
                                    onChange={changeMoney}
                                    value={moneyValue}
                                    disabled={disabledCashChannel}
                                    labelPosition='left'
                                    type='number'
                                    placeholder='Amount'>
                                    <Label basic>$</Label>
                                    <input/>

                                </Input>
                            </div>
                            <Button.Group className="control-buttons">
                                <Button
                                    disabled={disabledCashChannel}
                                    onClick={increaseMoneyToTen}
                                    content='+10'
                                    color='teal'>
                                </Button>
                                <Button
                                    disabled={disabledCashChannel}
                                    onClick={() => insertMoney({VaultLogicPlatformInstance, channelID, moneyValue, account})}
                                    content='Insert Money!'
                                    color='teal'>
                                </Button>
                            </Button.Group>
                        </Segment>
                    </Segment.Group>
                </Container>
            </div>
        )
    }
}

const connectedAdmin = connect(state => {
    const {moneyValue, contractsInstances, account, channelID, disabledCashChannel,requestIsActive, requestIsActiveMetaMask, requestType} = state.adminReducer;
    return {
        moneyValue,
        contractsInstances,
        account,
        channelID,
        disabledCashChannel,
        requestIsActive,
        requestIsActiveMetaMask,
        requestType
    };
},{
    setAccountAddress: adminActions.setAccountAddress,
    setContractsInstances: adminActions.setContractsInstances,
    increaseMoneyToTen: adminActions.increaseMoneyToTen,
    setChannelID: adminActions.setChannelID,
    clearChannelID: adminActions.clearChannelID,
    changeMoney: adminActions.changeMoney,
    insertMoney: adminActions.insertMoney,
    clearRequestType: generalActions.clearRequestType
})(Admin);

export default Admin = connectedAdmin;
