const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');

contract('CashChannelsManager', () => {

    describe('try to confirmOpen CashInChannel in "closed" status (4)', () => {
        let capitalHeroInstance;
        let resConfirmOpen;
        let resGetStatus;

        before(async () => {
            /* get instances */
            const cashInStorageInstance = await CashInStorage.deployed();
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();

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
            /* try to confirmOpen channel */
            try {
                await cashChannelsManagerInstance.confirmOpen(0);
                resConfirmOpen = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
        });

        it('restrict to confirmOpen', () => {
            assert.strictEqual(resGetStatus, 4, 'channel status is not equal');
            /* restrict to confirmOpen channel */
            assert.notEqual(resConfirmOpen, 'Method Allowed', 'allowed to call confirmOpen method');
        });
    });
});