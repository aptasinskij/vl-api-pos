const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');

contract('CashChannelsManager', () => {

    describe('CashInChannel in "opened" status (1)', () => {
        let capitalHeroInstance;
        let resConfirmOpen;
        let resConfirmClose;
        let resOpenCashInChannel;
        let resGetStatus;
        let resCloseCashInChannel1;
        let resCloseCashInChannel2;

        before(async () => {
            /* get instances */
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();

            /* registerApplication Capital Hero */
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession for Capital Hero */
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /* activate session */
            await sessionManagerInstance.activate(1);
            /* openCashInChannel */
            await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            /* confirmOpen */
            await cashChannelsManagerInstance.confirmOpen(0);
            resGetStatus = await cashInStorageInstance.getStatus(0);
            resGetStatus = Number(resGetStatus);
            /* updateCashInBalance */
            await cashChannelsManagerInstance.updateCashInBalance(0, 1000);
            /* try to openCashInChannel */
            try {
                await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
                resOpenCashInChannel = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
            /* try to confirmClose */
            try {
                await cashChannelsManagerInstance.confirmClose(0);
                resConfirmClose = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
            /* try to confirmOpen ones again */
            try {
                await cashChannelsManagerInstance.confirmOpen(0);
                resConfirmOpen = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
            /* try to closeCashInChannel with wrong application address */
            try {
                await cashChannelsManagerInstance.closeCashInChannel(321123, 1, 0, [1,2], [1,2]);
                resCloseCashInChannel1 = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel1 = e.message;
            }
            /* try to closeCashInChannel with wrong session id */
            try {
                await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 33, 0, [1,2], [1,2]);
                resCloseCashInChannel2 = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel2 = e.message;
            }
            /* try to closeCashInChannel with wrong arrays sizes */
            try {
                await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 33, 0, [1], [1,2,3]);
                resCloseCashInChannel2 = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel2 = e.message;
            }
        });

        it('restrict to openCashInChannel', () => {
            assert.strictEqual(resGetStatus, 1, 'channel status is not equal');
            /* restrict to confirmOpen channel */
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

    });
});