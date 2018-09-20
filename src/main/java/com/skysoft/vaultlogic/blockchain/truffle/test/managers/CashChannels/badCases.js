const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const {convertToNumber} = require('../../helpers');

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

    describe('try to openCashInChannel with application not owns the session', () => {
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
            /* activate session */
            await sessionManagerInstance.activate(1);
            /* try to openCashInChannel with wrong app address */
            try {
                await cashChannelsManagerInstance.openCashInChannel(132, 1);
                resOpenCashInChannel = 'Method Allowed';
            } catch (e) {
                resOpenCashInChannel = e.message;
            }
        });

        it('restrict to openCashInChannel', () => {
            assert.notEqual(resOpenCashInChannel, 'Method Allowed', 'openCashInChannel method allowed');
        });
    });

    describe('try to open two openCashInChannels', () => {
        let resGet;
        let capitalHeroInstance;
        let resOpenCashInChannel;

        before(async () => {
            /* get instances */
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();

            /* registerApplication Capital Hero */
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession for Capital Hero */
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /* activate session */
            await sessionManagerInstance.activate(1);
            /* openCashInChannel 1 */
            cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            /* get first channel */
            resGet = await cashInStorageInstance.get(0);
            resGet = convertToNumber(resGet, true);

            /* try to open second channel */
            try {
                await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
                resOpenCashInChannel = 'Method Allowed';
            } catch (e) {
                resOpenCashInChannel = e.message;
            }
        });

        it('restrict to openCashInChannel', () => {
            /* open first channel, get it's info */
            assert.strictEqual(resGet[0], 1, 'first channel id is not equal');
            assert.strictEqual(resGet[1], capitalHeroInstance.address, 'first channel address is not equal');
            assert.strictEqual(resGet[4], 0, 'first channel status is not equal');
            /* try to open second channel */
            assert.notEqual(resOpenCashInChannel, 'Method Allowed', 'allowed to call openCashInChannel method twice');
        });
    });

    describe('try to open two openCashInChannels', () => {
        let resGet;
        let capitalHeroInstance;
        let resOpenCashInChannel;

        before(async () => {
            /* get instances */
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();

            /* registerApplication Capital Hero */
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession for Capital Hero */
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /* activate session */
            await sessionManagerInstance.activate(1);
            /* openCashInChannel 1 */
            cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            /* get first channel */
            resGet = await cashInStorageInstance.get(0);
            resGet = convertToNumber(resGet, true);

            /* try to open second channel */
            try {
                await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
                resOpenCashInChannel = 'Method Allowed';
            } catch (e) {
                resOpenCashInChannel = e.message;
            }
        });

        it('restrict to openCashInChannel', () => {
            /* open first channel, get it's info */
            assert.strictEqual(resGet[0], 1, 'first channel id is not equal');
            assert.strictEqual(resGet[1], capitalHeroInstance.address, 'first channel address is not equal');
            assert.strictEqual(resGet[4], 0, 'first channel status is not equal');
            /* try to open second channel */
            assert.notEqual(resOpenCashInChannel, 'Method Allowed', 'allowed to call openCashInChannel method twice');
        });
    });
});
