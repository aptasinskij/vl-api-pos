const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');

contract('CashChannelsManager', () => {

    describe('try to openCashInChannel in "opened" status (1)', () => {
        let capitalHeroInstance;
        let resConfirmOpen;
        let resGetStatus;

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
            /* try to confirmOpen channel */
            try {
                await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
                resConfirmOpen = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
        });

        it('restrict to openCashInChannel', () => {
            assert.strictEqual(resGetStatus, 1, 'channel status is not equal');
            /* restrict to confirmOpen channel */
            assert.notEqual(resConfirmOpen, 'Method Allowed', 'allowed to call openCashInChannel method');
        });
    });
});