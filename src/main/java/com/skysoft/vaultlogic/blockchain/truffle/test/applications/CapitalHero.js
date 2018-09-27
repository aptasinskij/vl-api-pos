const assert = require('chai').assert;
const CashInController = artifacts.require('CashInController');
const cashChannelsManager = artifacts.require('cashChannelsManager');
const CashInStorage = artifacts.require('CashInStorage');
const ApplicationManager = artifacts.require('ApplicationManager');
const ApplicationStorage = artifacts.require('ApplicationStorage');
const ParameterStorage = artifacts.require('ParameterStorage');
const SessionManager = artifacts.require('SessionManager');
const CapitalHero = artifacts.require('CapitalHero');
const {convertToNumber, sleep} = require('../helpers');


contract('CapitalHero', accounts => {

    describe('tests for open/close CashInChannel methods', () => {
        const ownerAddress = accounts[0];
        let resOpen;
        let resGet1;
        let resGet2;
        let resClose;
        let cashInStorageEvents = [];
        let capitalHeroInstance;

        before(async () => {
            const cashInControllerInstance = await CapitalHero.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();
            const parameterStorageInstance = await ParameterStorage.deployed();
            const cashChannelsManagerInstance = await cashChannelsManager.deployed();

            const cashInStorageInstance = await CashInStorage.deployed();

            /* ApplicationManager.deployed().then(i => appManager = i);
             CapitalHero.deployed().then(i => capital = i);
             SessionManager.deployed().then(i => sessionManager = i);

             appManager.registerApplication(123, 'CapitalHero', web3.eth.accounts[0], 'http://capital-hero', capital.address);
             sessionManager.createSession(111, 123, "test");
             sessionManager.activate(111);
             */

            /* watch CapitalHero events */
            cashInStorageInstance.CashInStatusUpdated().watch((err, response) => {
                cashInStorageEvents.push(convertToNumber(response.args, true));
            });
            cashInStorageInstance.CashInSaved().watch((err, response) => {
                cashInStorageEvents.push(convertToNumber(response.args, true));
            });
            /* setVLFee of ParameterStorage */
            await parameterStorageInstance.setVLFee(1000);

            /* registerApplication */
            await applicationManagerInstance.registerApplication(
                123,
                'CapitalHero',
                ownerAddress,
                'http://capital-hero',
                capitalHeroInstance.address
            );
            /* enableApplication */
            await applicationManagerInstance.enableApplication(123);
            /* createSession */
            await sessionManagerInstance.createSession(321, 123, 'SessionXToken');
            /* activate */
            await sessionManagerInstance.activate(321);
            /* openCashInChannel */
            resOpen = await capitalHeroInstance.openCashInChannel(321);
            /* get by CashInStorage */
            resGet1 = convertToNumber(await cashInStorageInstance.get(0), true);

            /* confirmOpen */
            await cashChannelsManagerInstance.confirmOpen(0);



            /* updateCashInBalance */
            await cashChannelsManagerInstance.updateCashInBalance(0, 100000);


            /* closeCashInChannel */
            resClose = await capitalHeroInstance.closeCashInChannel(321, 0, [0,0], [1,2]);
            resGet2 = convertToNumber(await cashInStorageInstance.get(0), true);


        });

        it('openCashInChannel', () => {
            /* from open method */
            assert.isAbove(resOpen.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resOpen.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resOpen.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from CashInStorage event */
            assert.strictEqual(cashInStorageEvents[0].channelId, 0);
            assert.strictEqual(cashInStorageEvents[0].sessionId, 321);
            assert.strictEqual(cashInStorageEvents[0].application, capitalHeroInstance.address);
            assert.strictEqual(cashInStorageEvents[0].status, 0);
            /* from get method of CashInStorage */
            assert.strictEqual(resGet1[0], 321, 'session id is not equal');
            assert.strictEqual(resGet1[1], capitalHeroInstance.address, 'app address in not equal');
            assert.strictEqual(resGet1[2], 0, 'channel balance in not 0');
            assert.strictEqual(resGet1[3], 0, 'channel status is not equal');
            assert.strictEqual(resGet1[4], 0, 'channel splitSize is not equal');
        });
        it('closeCashInChannel', () => {
            console.log('cashInStorageEvents', cashInStorageEvents);
            /* from open method */
            assert.isAbove(resClose.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resClose.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resClose.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from CashInStorage event */
            assert.strictEqual(cashInStorageEvents[2].channelId, 0);
            assert.strictEqual(cashInStorageEvents[2].status, 3);
            /* from get method of CashInStorage */
            assert.strictEqual(resGet2[0], 321, 'session id is not equal');
            assert.strictEqual(resGet2[1], capitalHeroInstance.address, 'app address in not equal');
            assert.strictEqual(resGet2[2], 0, 'channel balance in not 0');
            assert.strictEqual(resGet2[3], 0, 'channel status is not equal');
            assert.strictEqual(resGet2[4], 0, 'channel splitSize is not equal');
        })
    })
});
