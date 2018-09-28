const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');
const ParameterStorage = artifacts.require('ParameterStorage.sol');

contract('CashChannelsManager', () => {

    describe('CashInChannel in "closed" status (4)', () => {
        let capitalHeroInstance;
        let resConfirmOpen;
        let resGetStatus;
        let resCloseCashInChannel;
        let resConfirmClose;
        let resGetStatus2;

        before(async () => {
            /* get instances */
            const cashInStorageInstance = await CashInStorage.deployed();
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();
            const parameterStorageInstance = await ParameterStorage.deployed();

            /* registerApplication Capital Hero */
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession for Capital Hero */
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /* activate session */
            await sessionManagerInstance.activate(1);
            /* setVLFee of ParameterStorage (10%) */
            await parameterStorageInstance.setVLFee(1000);
            /* openCashInChannel */
            await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            /* confirmOpen */
            await cashChannelsManagerInstance.confirmOpen(0);
            /* updateCashInBalance */
            await cashChannelsManagerInstance.updateCashInBalance(0, 200000);
            /* closeCashInChannel */
            await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [60000,30000,10000,50000,30000], [1,2,3,4,5]);
            /* confirmClose */
            await cashChannelsManagerInstance.confirmClose(0);
            resGetStatus = await cashInStorageInstance.getStatus(0);
            resGetStatus = Number(resGetStatus);
            /* try to confirmOpen */
            try {
                await cashChannelsManagerInstance.confirmOpen(0);
                resConfirmOpen = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
            /* try to closeCashInChannel */
            try {
                await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [1,2], [1,2]);
                resCloseCashInChannel = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel = e.message;
            }
            /* try to confirmClose */
            try {
                await cashChannelsManagerInstance.confirmClose(0);
                resConfirmClose = 'Method Allowed';
            } catch (e) {
                resConfirmClose = e.message;
            }
            /* openCashInChannel ones again */
            await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            resGetStatus2 = await cashInStorageInstance.getStatus(1);
            resGetStatus2 = Number(resGetStatus2);

        });

        it('restrict to confirmOpen', () => {
            assert.strictEqual(resGetStatus, 4, 'channel status is not equal');
            /* restrict to confirmOpen channel */
            assert.notEqual(resConfirmOpen, 'Method Allowed', 'allowed to call confirmOpen method');
        });
        it('restrict to closeCashInChannel', () => {
            assert.notEqual(resCloseCashInChannel, 'Method Allowed', 'allowed to call closeCashInChannel method');
        });
        it('restrict to confirmClose ones again', () => {
            assert.notEqual(resConfirmClose, 'Method Allowed', 'allowed to call confirmClose method');
        });
        it('allow to openCashInChannel', () => {
            /* open second CashInChannel */
            assert.strictEqual(resGetStatus2, 0, 'channel status is not equal');
        });
    });
});
