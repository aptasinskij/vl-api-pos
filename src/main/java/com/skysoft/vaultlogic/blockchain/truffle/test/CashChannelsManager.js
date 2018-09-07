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
        let resCloseCashInChannel;
        let resGetStatus;

        before(async () => {
            /* get instances */
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            const capitalHeroInstance = await CapitalHero.deployed();

            /* registerApplication Capital Hero */
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession for Capital Hero */
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /* openCashInChannel */
            resOpenCashInChannel = await cashChannelsManagerInstance.openCashInChannel(1);
            resGet = await cashInStorageInstance.get(0);
            resGet = convertToNumber(resGet, true);
            /* confirmOpe  */
            resCloseCashInChannel = await cashChannelsManagerInstance.confirmOpen(0);
            resGetStatus = await cashInStorageInstance.getStatus(0);
            resGetStatus = Number(resGetStatus);

            console.log('resGetStatus',resGetStatus);
        });

        it('openCashInChannel', () => {
            /* from CashChannelsManager event */
            assert.isAbove(resOpenCashInChannel.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resOpenCashInChannel.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resOpenCashInChannel.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from CashChannelsManager method */
            assert.strictEqual(resGet[0], 1, 'channel id is not equal');
            assert.notEqual(resGet[1], '', 'channel address is empty');
            assert.strictEqual(resGet[4], 0, 'channel status is not equal');
        });
        it('confirmOpen', () => {
            /* from CashChannelsManager event */
            assert.isAbove(resCloseCashInChannel.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resCloseCashInChannel.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resCloseCashInChannel.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from CashChannelsManager method */
            assert.strictEqual(resGetStatus, 1, 'channel status is not equal');
        });

    });
});