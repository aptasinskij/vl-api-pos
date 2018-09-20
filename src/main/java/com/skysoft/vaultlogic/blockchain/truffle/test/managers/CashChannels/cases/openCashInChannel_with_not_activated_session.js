const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');

contract('CashChannelsManager', () => {

    describe('try to openCashInChannel with not activated session', () => {
        let resOpenCashInChannel;

        before(async () => {
            /* get instances */
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            const capitalHeroInstance = await CapitalHero.deployed();
            /* registerApplication Capital Hero */
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession for Capital Hero */
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');

            /* miss the step to activate session */

            /* try to openCashInChannel */
            try {
                await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
                resOpenCashInChannel = 'Method Allowed';
            } catch (e) {
                resOpenCashInChannel = e.message;
            }
        });

        it('restrict to openCashInChannel', () => {
            assert.notEqual(resOpenCashInChannel, 'Method Allowed', 'openCashInChannel method allowed');
        });
    });
});
