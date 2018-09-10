const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('./services/CashChannelsManager.sol');
const CashInStorage = artifacts.require('./repositories/cash-in/CashInStorage.sol');
const SessionManager = artifacts.require('./services/SessionManager.sol');
const ApplicationManager = artifacts.require('./services/ApplicationManager.sol');
const CapitalHero = artifacts.require('./application/CapitalHero.sol');
const convertToNumber = require('./helpers').convertToNumber;
const sleep = require('./helpers').sleep;

contract('CashChannelsManager', () => {

    describe('tests for all methods', () => {
        let resOpenCashInChannel;
        let resGet;
        let resConfirmOpen;
        let resGetStatus1;
        let capitalHeroInstance;
        let resGetStatus2;
        let resCloseCashInChannel;
        let resConfirmClose;
        let resGetStatus3;
        let resUpdateCashInBalance;
        let resGetBalance;

        let CashInSavedEvents = [];
        let CashInBalanceUpdatedEvents = [];
        let CashInStatusUpdatedEvents = [];

        before(async () => {
            /* get instances */
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();
            /* watch events */
            cashInStorageInstance.CashInStatusUpdated().watch((err, response) => {
                CashInStatusUpdatedEvents.push(convertToNumber(response.args, true));
            });
            cashInStorageInstance.CashInSaved().watch((err, response) => {
                CashInSavedEvents.push(convertToNumber(response.args, true));
            });
            cashInStorageInstance.CashInBalanceUpdated().watch((err, response) => {
                CashInBalanceUpdatedEvents.push(convertToNumber(response.args, true));
            });
            /* registerApplication Capital Hero */
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession for Capital Hero */
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /* openCashInChannel */
            resOpenCashInChannel = await cashChannelsManagerInstance.openCashInChannel(1);

            resGet = await cashInStorageInstance.get(0);
            resGet = convertToNumber(resGet, true);
            /* confirmOpen  */
            resConfirmOpen = await cashChannelsManagerInstance.confirmOpen(0);
            resGetStatus1 = await cashInStorageInstance.getStatus(0);
            resGetStatus1 = Number(resGetStatus1);
            /* updateCashInBalance */
            resUpdateCashInBalance = await cashChannelsManagerInstance.updateCashInBalance(0, 100);
            resGetBalance = await cashInStorageInstance.getBalance(0);
            resGetBalance = Number(resGetBalance);
            /* closeCashInChannel */
            resCloseCashInChannel = await cashChannelsManagerInstance.closeCashInChannel(1, 0);
            resGetStatus2 = await cashInStorageInstance.getStatus(0);
            resGetStatus2 = Number(resGetStatus2);
            /* confirmClose */
            resConfirmClose = await cashChannelsManagerInstance.confirmClose(0);
            resGetStatus3 = await cashInStorageInstance.getStatus(0);
            resGetStatus3 = Number(resGetStatus3);

           /* sleep(3000); // for make sure events handles*/
        });

        it('openCashInChannel', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resOpenCashInChannel.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resOpenCashInChannel.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resOpenCashInChannel.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInSavedEvents[0].index, 0, 'channel id is not equal');
            assert.strictEqual(CashInSavedEvents[0].sessionId, 1, 'session id is not equal');
            assert.strictEqual(CashInSavedEvents[0].application, capitalHeroInstance.address, 'application address is not equal');
            assert.strictEqual(CashInSavedEvents[0].status, 0, 'channel status is not equal');
            /* from cashInStorage method */
            assert.strictEqual(resGet[0], 1, 'channel id is not equal');
            assert.strictEqual(resGet[1], capitalHeroInstance.address, 'channel address is not equal');
            assert.strictEqual(resGet[4], 0, 'channel status is not equal');
        });
        it('confirmOpen', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resConfirmOpen.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resConfirmOpen.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resConfirmOpen.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInStatusUpdatedEvents[0].index, 0, 'channel id is not equal');
            assert.strictEqual(CashInStatusUpdatedEvents[0].status, 1, 'channel status is not equal');
            /* from cashInStorage method */
            assert.strictEqual(resGetStatus1, 1, 'channel status is not equal');
        });
        it('updateCashInBalance', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resUpdateCashInBalance.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resUpdateCashInBalance.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resUpdateCashInBalance.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInBalanceUpdatedEvents[0].index, 0, 'channel id is not equal');
            assert.strictEqual(CashInBalanceUpdatedEvents[0].amount, 100, 'channel amount is not equal');
            /* from cashInStorage method */
            assert.strictEqual(resGetBalance, 100, 'channel balance is not equal');
        });
        it('closeCashInChannel', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resCloseCashInChannel.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resCloseCashInChannel.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resCloseCashInChannel.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInStatusUpdatedEvents[1].index, 0, 'channel id is not equal');
            assert.strictEqual(CashInStatusUpdatedEvents[1].status, 2, 'channel status is not equal');
            /* from cashInStorage method */
            assert.strictEqual(resGetStatus2, 2, 'channel status is not equal');
        });
        it('confirmClose', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resConfirmClose.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resConfirmClose.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resConfirmClose.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInStatusUpdatedEvents[2].index, 0, 'channel id is not equal');
            assert.strictEqual(CashInStatusUpdatedEvents[2].status, 3, 'channel status is not equal');
            /* from cashInStorage method */
            assert.strictEqual(resGetStatus3, 3, 'channel status is not equal');
        });

    });
});