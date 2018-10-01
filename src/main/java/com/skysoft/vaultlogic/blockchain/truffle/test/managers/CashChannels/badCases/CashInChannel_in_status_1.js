/*
const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');
const ParameterStorage = artifacts.require('ParameterStorage.sol');

contract('CashChannelsManager', () => {

    describe('CashInChannel in "opened" status (1)', () => {
        let capitalHeroInstance;
        let resConfirmOpen;
        let resConfirmClose;
        let resOpenCashInChannel;
        let resGetStatus;
        let resCloseCashInChannel1;
        let resCloseCashInChannel2;
        let resCloseCashInChannel3;
        let resCloseCashInChannel4;
        let resCloseCashInChannel5;
        let resCloseCashInChannel6;
        let resBalanceOf;
        let resBalanceOf2;
        let resBalanceOf3;

        before(async () => {
            /!* get instances *!/
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();
            const parameterStorageInstance = await ParameterStorage.deployed();

            /!* registerApplication Capital Hero *!/
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /!* createSession for Capital Hero *!/
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /!* activate session *!/
            await sessionManagerInstance.activate(1);
            /!* setVLFee of ParameterStorage (10%) *!/
            await parameterStorageInstance.setVLFee(1000);

            /!* openCashInChannel *!/
            await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            /!* confirmOpen *!/
            await cashChannelsManagerInstance.confirmOpen(0);
            resGetStatus = await cashInStorageInstance.getStatus(0);
            resGetStatus = Number(resGetStatus);
            /!* updateCashInBalance *!/
            await cashChannelsManagerInstance.updateCashInBalance(0, 1000);
            /!* try to openCashInChannel *!/
            try {
                await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
                resOpenCashInChannel = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
            /!* try to confirmClose *!/
            try {
                await cashChannelsManagerInstance.confirmClose(0);
                resConfirmClose = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
            /!* try to confirmOpen ones again *!/
            try {
                await cashChannelsManagerInstance.confirmOpen(0);
                resConfirmOpen = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
            /!* try to closeCashInChannel with wrong application address *!/
            try {
                await cashChannelsManagerInstance.closeCashInChannel(321123, 1, 0, [1,2], [1,2]);
                resCloseCashInChannel1 = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel1 = e.message;
            }
            /!* try to closeCashInChannel with wrong session id *!/
            try {
                await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 33, 0, [1,2], [1,2]);
                resCloseCashInChannel2 = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel2 = e.message;
            }
            /!* try to closeCashInChannel with wrong arrays sizes *!/
            try {
                await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [1], [1,2,3]);
                resCloseCashInChannel3 = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel3 = e.message;
            }
            try {
                await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [1,2,3,4], [1,2]);
                resCloseCashInChannel4 = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel4 = e.message;
            }
            /!* --- try to closeCashInChannel with wrong splits amount --- *!/
            /!* break condition: (balance) - (splits amount) < (10% of balance) *!/
            try {
                await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [600,301], [1,2]);
                resCloseCashInChannel5 = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel5 = e.method;
            }
            /!* make balance = 20 000 (19 000 + previous 1 000) *!/
            await cashChannelsManagerInstance.updateCashInBalance(0, 19000);
            resBalanceOf = await cashChannelsManagerInstance.balanceOf(capitalHeroInstance.address, 0);
            resBalanceOf = Number(resBalanceOf);
            /!* break condition: (balance) - (splits amount) < (10% of balance) *!/
            try {
                await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [6000,3000,1000,5000,3001], [1,2,3,4,5]);
                resCloseCashInChannel6 = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel6 = e.method;
            }

            /!* try get balanceOf wrong channel *!/
            try {
                await cashChannelsManagerInstance.balanceOf(capitalHeroInstance.address, 1);
                resBalanceOf2 = 'Method Allowed';
            } catch (e) {
                resBalanceOf2 = e.message;
            }
            /!* try get balanceOf wrong application address *!/
            try {
                await cashChannelsManagerInstance.balanceOf(321, 0);
                resBalanceOf3 = 'Method Allowed';
            } catch (e) {
                resBalanceOf3 = e.message;
            }
        });

        it('restrict to openCashInChannel', () => {
            assert.strictEqual(resGetStatus, 1, 'channel status is not equal');
            /!* restrict to confirmOpen channel *!/
            assert.notEqual(resOpenCashInChannel, 'Method Allowed', 'allowed to call openCashInChannel method');
        });
        it('restrict to confirmClose', () => {
            assert.notEqual(resConfirmClose, 'Method Allowed', 'allowed to call openCashInChannel method');
        });
        it('restrict to confirmOpen ones again', () => {
            assert.notEqual(resConfirmOpen, 'Method Allowed', 'allowed to call confirmOpen method');
        });
        it('restrict to closeCashInChannel with wrong application address', () => {
            assert.notEqual(resCloseCashInChannel1, 'Method Allowed', 'allowed to call closeCashInChannel');
        });
        it('restrict to closeCashInChannel with wrong session id', () => {
            assert.notEqual(resCloseCashInChannel2, 'Method Allowed', 'allowed to call closeCashInChannel');
        });
        it('restrict to closeCashInChannel with wrong arrays sizes', () => {
            assert.notEqual(resCloseCashInChannel3, 'Method Allowed', 'allowed to call closeCashInChannel [1], [1,2,3]');
            assert.notEqual(resCloseCashInChannel4, 'Method Allowed', 'allowed to call closeCashInChannel [1,2,3,4], [1,2]');
        });
        it('restrict to closeCashInChannel with wrong splits amount', () => {
            /!* restrict to break condition: (balance) - (splits amount) < (10% of balance) *!/
            assert.notEqual(resCloseCashInChannel5, 'Method Allowed', 'allowed to call closeCashInChannel with break condition: (balance [1000]) - (splits amount [600,301]) < (10% of balance [100])');
            assert.strictEqual(resBalanceOf, 20000, 'channel balance is not equal');
            assert.notEqual(resCloseCashInChannel6, 'Method Allowed', 'allowed to call closeCashInChannel with break condition: (balance [20000]) - (splits amount [6000,3000,1000,5000,3001]) < (10% of balance [2000])');
        });
        it('restrict to get balanceOf wrong channel', () => {
            assert.notEqual(resBalanceOf2, 'Method Allowed', 'allow to call balanceOf wrong channel');
        });
        it('restrict to get balanceOf wrong application address', () => {
            assert.notEqual(resBalanceOf3, 'Method Allowed', 'allow to call balanceOf wrong application address');
        });
    });
});
*/
