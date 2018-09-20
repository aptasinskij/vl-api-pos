const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');

contract('CashChannelsManager', () => {

    describe('allow to open second CashInChannel if first in "closed" status (4)', () => {
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
            /* setStatus 3 */
            await cashInStorageInstance.setStatus(0, 3);
            /* confirmClose */
            await cashChannelsManagerInstance.confirmClose(0);
            resGetStatus = await cashInStorageInstance.getStatus(0);
            resGetStatus = Number(resGetStatus);
            /* try to openCashInChannel channel */
            try {
                await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
                resConfirmOpen = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
        });

        it('allow to openCashInChannel', () => {
            assert.strictEqual(resGetStatus, 4, 'channel status is not equal');
            /* restrict to confirmOpen channel */
            assert.strictEqual(resConfirmOpen, 'Method Allowed', 'restrict to call openCashInChannel method');
        });
    });
});