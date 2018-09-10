const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('./services/CashChannelsManager.sol');
const CashInStorage = artifacts.require('./repositories/cash-in/CashInStorage.sol');
const SessionManager = artifacts.require('./services/SessionManager.sol');
const ApplicationManager = artifacts.require('./services/ApplicationManager.sol');
const CapitalHero = artifacts.require('./application/CapitalHero.sol');
const convertToNumber = require('./helpers').convertToNumber;

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
            /* openCashInChannel */
            resOpenCashInChannel = await cashChannelsManagerInstance.openCashInChannel(1);
            resGet = await cashInStorageInstance.get(0);
            resGet = convertToNumber(resGet, true);
            /* confirmOpen  */
            resConfirmOpen = await cashChannelsManagerInstance.confirmOpen(0);
            resGetStatus1 = await cashInStorageInstance.getStatus(0);
            resGetStatus1 = Number(resGetStatus1);
            /* closeCashInChannel */
            resCloseCashInChannel = await cashChannelsManagerInstance.closeCashInChannel(1, 0);
            resGetStatus2 = await cashInStorageInstance.getStatus(0);
            resGetStatus2 = Number(resGetStatus2);
            /* confirmClose */
            resConfirmClose = await cashChannelsManagerInstance.confirmClose(0);
            resGetStatus3 = await cashInStorageInstance.getStatus(0);
            resGetStatus3 = Number(resGetStatus3);
        });

        it('openCashInChannel', () => {
            /* from CashChannelsManager event */
            assert.isAbove(resOpenCashInChannel.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resOpenCashInChannel.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resOpenCashInChannel.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage method */
            assert.strictEqual(resGet[0], 1, 'channel id is not equal');
            assert.strictEqual(resGet[1], capitalHeroInstance.address, 'channel address is not equal');
            assert.strictEqual(resGet[4], 0, 'channel status is not equal');
        });
        it('confirmOpen', () => {
            /* from CashChannelsManager event */
            assert.isAbove(resConfirmOpen.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resConfirmOpen.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resConfirmOpen.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage method */
            assert.strictEqual(resGetStatus1, 1, 'channel status is not equal');
        });
        it('closeCashInChannel', () => {
            /* from CashChannelsManager event */
            assert.isAbove(resCloseCashInChannel.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resCloseCashInChannel.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resCloseCashInChannel.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage method */
            assert.strictEqual(resGetStatus2, 2, 'channel status is not equal');
        });
        it('confirmClose', () => {
            /* from CashChannelsManager event */
            assert.isAbove(resConfirmClose.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resConfirmClose.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resConfirmClose.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage method */
            assert.strictEqual(resGetStatus3, 3, 'channel status is not equal');
        });
    });
});