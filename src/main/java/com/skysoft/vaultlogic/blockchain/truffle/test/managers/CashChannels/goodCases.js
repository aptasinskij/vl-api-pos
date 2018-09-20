const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const {convertToNumber} = require('../../helpers');

contract('CashChannelsManager', () => {

    describe('open closed channel', () => {

        let capitalHeroInstance;
        let resGet1;
        let resGetStatus1;
        let resGetStatus2;
        let resGetStatus3;

        before(async () => {
            /* get instances */
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();

            /*
            CashChannelsManager.deployed().then(i => channelsManager = i);
            SessionManager.deployed().then(i => sessionManager = i);
            ApplicationManager.deployed().then(i => appManager = i);
            CapitalHero.deployed().then(i => capital = i);
            CashInStorage.deployed().then(i => cashStorage = i);
            SessionStorage.deployed().then(i => sessionStorage = i);
            appManager.registerApplication(2, 'capital', web3.eth.accounts[0], 'http', capital.address);
            sessionManager.createSession(5, 2, 'test');
            */

            /* registerApplication Capital Hero */
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession for Capital Hero */
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /* activate session */
            await sessionManagerInstance.activate(1);
            /* openCashInChannel */
            await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            resGet1 = await cashInStorageInstance.get(0);
            resGet1 = convertToNumber(resGet1, true);
            /* setStatus (3) */
            await cashInStorageInstance.setStatus(0, 3);
            resGetStatus1 = await cashInStorageInstance.getStatus(0);
            resGetStatus1 = Number(resGetStatus1);
            /* confirmClose */
            await cashChannelsManagerInstance.confirmClose(0);
            resGetStatus2 = await cashInStorageInstance.getStatus(0);
            resGetStatus2 = Number(resGetStatus2);
            /* openCashInChannel ones again */
            await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            resGetStatus3 = await cashInStorageInstance.getStatus(1);
            resGetStatus3 = Number(resGetStatus3);
        });

        it('allow to open second CashInChannel after first close', () => {
            /* opened CashInChannel */
            assert.strictEqual(resGet1[0], 1, 'channel id is not equal');
            assert.strictEqual(resGet1[1], capitalHeroInstance.address, 'channel address is not equal');
            assert.strictEqual(resGet1[4], 0, 'channel status is not equal');
            /* setStatus (3) */
            assert.strictEqual(resGetStatus1, 3, 'channel status is not equal');
            /* confirmClose */
            assert.strictEqual(resGetStatus2, 4, 'channel status is not equal');
            /* openCashInChannel ones again */
            assert.strictEqual(resGetStatus3, 0, 'channel status is not equal');
        });
    });
});
