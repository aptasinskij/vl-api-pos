const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const TokenManager = artifacts.require('TokenManager.sol');
const ParameterStorage = artifacts.require('ParameterStorage.sol');
const {convertToNumber, sleep} = require('../../../helpers');

contract('CashChannelsManager', (accounts) => {

    describe('tests for all methods', () => {
        const ownerAccount = accounts[0];
        let resOpenCashInChannel;
        let resGet;
        let resConfirmOpen;
        let resGetStatus1;
        let capitalHeroInstance;
        let resGetStatus2;
        let resCloseCashInChannel;
        let resConfirmClose;
        let resUpdateCashInBalance;
        let resGetBalance;
        let resBalanceOf;
        let splitsArray = [];
        let resBalanceOfReceiver1;
        let resBalanceOfReceiver2;
        let resBalanceOfReceiver3;
        let resBalanceOfSender1;
        let resBalanceOfSender2;
        let resBalanceOwner;
        let requestCloseChannelInfo;
        let resIsHasActiveCashIn0;
        let resIsHasActiveCashIn;

        let CashInSavedEvents = [];
        let CashInBalanceUpdatedEvents = [];
        let CashInStatusUpdatedEvents = [];
        let CapitalHeroEvents = [];

        before(async () => {
            /* get instances */
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            const tokenManagerInstance = await TokenManager.deployed();
            const parameterStorageInstance = await ParameterStorage.deployed();
            capitalHeroInstance = await CapitalHero.deployed();
            /* --- watch events --- */
            /* CashInStorage events */
            cashInStorageInstance.CashInStatusUpdated().watch((err, response) => {
                CashInStatusUpdatedEvents.push(convertToNumber(response.args, true));
            });
            cashInStorageInstance.CashInSaved().watch((err, response) => {
                CashInSavedEvents.push(convertToNumber(response.args, true));
            });
            cashInStorageInstance.CashInBalanceUpdated().watch((err, response) => {
                CashInBalanceUpdatedEvents.push(convertToNumber(response.args, true));
            });
            /* CapitalHero events */
            capitalHeroInstance.CashInOpened().watch((err, response) => {
                CapitalHeroEvents.push(convertToNumber(response.args, true));
            });
            capitalHeroInstance.CashInClosed().watch((err, response) => {
                CapitalHeroEvents.push(convertToNumber(response.args, true));
            });
            capitalHeroInstance.CashInBalanceUpdated().watch((err, response) => {
                CapitalHeroEvents.push(convertToNumber(response.args, true));
            });
            /* --- */

            /* registerApplication Capital Hero */
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession for Capital Hero */
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /* activate session */
            await sessionManagerInstance.activate(1);

            /* setVLFee of ParameterStorage */
            await parameterStorageInstance.setVLFee(1000);

            /* openCashInChannel */
            resOpenCashInChannel = await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            resGet = convertToNumber(await cashInStorageInstance.get(0), true);
            /* confirmOpen */
            resConfirmOpen = await cashChannelsManagerInstance.confirmOpen(0);
            resGetStatus1 = Number(await cashInStorageInstance.getStatus(0));

            /* isHasActiveCashIn */
            resIsHasActiveCashIn0 = await sessionManagerInstance.isHasActiveCashIn(1);

            /* updateCashInBalance */
            resUpdateCashInBalance = await cashChannelsManagerInstance.updateCashInBalance(0, 100000);
            resGetBalance = Number(await cashInStorageInstance.getBalance(0));
            /* balanceOf */
            resBalanceOf = Number(await cashChannelsManagerInstance.balanceOf(capitalHeroInstance.address, 0));

            /* closeCashInChannel */
            resCloseCashInChannel = await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [30000,35000,20000], [1,2,3]);
            requestCloseChannelInfo = {
                VLFee: Number(await cashInStorageInstance.getVLFee(0)),
                appBalance: Number(await cashInStorageInstance.getApplicationBalance(0)),
                channelSplitSize: Number(await cashInStorageInstance.getSplitSize(0)),
                channelBalance: Number(await cashInStorageInstance.getBalance(0)),
                channelStatus: Number(await cashInStorageInstance.getStatus(0))
            };

            /* get splits */
            // must be moved to helpers
            let resGetSplitSize = Number(await cashInStorageInstance.getSplitSize(0));
            for (let i = 0; i < resGetSplitSize; i++) {
                let resGetSplit = await cashInStorageInstance.getSplit(0, i);
                splitsArray.push(convertToNumber(resGetSplit));
            }
            /* --- */

            /* confirmClose */
            resConfirmClose = await cashChannelsManagerInstance.confirmClose(0);
            /* check different params */
            resIsHasActiveCashIn = await sessionManagerInstance.isHasActiveCashIn(1);
            resGetStatus2 = Number(await cashInStorageInstance.getStatus(0));
            /* balanceOf receivers (TokenManager contract) */
            resBalanceOfReceiver1 = Number(await tokenManagerInstance.balanceOf(1));
            resBalanceOfReceiver2 = Number(await tokenManagerInstance.balanceOf(2));
            resBalanceOfReceiver3 = Number(await tokenManagerInstance.balanceOf(3));
            /* get sender balance (CashChannelsManager contract) */
            resBalanceOfSender1 = Number(await cashChannelsManagerInstance.balanceOf(capitalHeroInstance.address, 0));
            /* get sender balance (TokenManager contract) */
            resBalanceOfSender2 = Number(await tokenManagerInstance.balanceOf(capitalHeroInstance.address));
            resBalanceOwner = Number(await tokenManagerInstance.balanceOf(ownerAccount));

            sleep(3000); // for make sure events handles
        });

        it('openCashInChannel', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resOpenCashInChannel.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resOpenCashInChannel.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resOpenCashInChannel.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInSavedEvents[0].channelId, 0, 'channel id is not equal');
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
            assert.strictEqual(CashInStatusUpdatedEvents[0].channelId, 0, 'channel id is not equal');
            assert.strictEqual(CashInStatusUpdatedEvents[0].status, 1, 'channel status is not equal');
            /* from cashInStorage method */
            assert.strictEqual(resGetStatus1, 1, 'channel status is not equal');
            /* from CapitalHero event */
            assert.strictEqual(CapitalHeroEvents[0].channelId, 0, 'channel id is not equal');
            assert.strictEqual(CapitalHeroEvents[0].sessionId, 1, 'session id is not equal');
        });
        it('activate', () => {
            /* from SessionManager method */
            assert.strictEqual(resIsHasActiveCashIn0, true, 'session has no active channel');
        });
        it('updateCashInBalance', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resUpdateCashInBalance.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resUpdateCashInBalance.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resUpdateCashInBalance.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInBalanceUpdatedEvents[0].channelId, 0, 'channel id is not equal');
            assert.strictEqual(CashInBalanceUpdatedEvents[0].amount, 100000, 'channel amount is not equal');
            /* from cashInStorage method */
            assert.strictEqual(resGetBalance, 100000, 'channel balance is not equal');
            /* from CapitalHero event */
            assert.strictEqual(CapitalHeroEvents[1].channelId, 0, 'channel id is not equal');
            assert.strictEqual(CapitalHeroEvents[1].sessionId, 1, 'session id is not equal');
            assert.strictEqual(CapitalHeroEvents[1].balance, 100000, 'channel balance is not equal');
        });
        it('balanceOf', () => {
            assert.strictEqual(resBalanceOf, 100000, 'channel balance is not equal');
        });
        it('closeCashInChannel', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resCloseCashInChannel.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resCloseCashInChannel.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resCloseCashInChannel.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInStatusUpdatedEvents[1].channelId, 0, 'channel id is not equal');
            assert.strictEqual(CashInStatusUpdatedEvents[1].status, 3, 'channel status is not equal');
            /* check splits info */
            assert.strictEqual(splitsArray[0][0], 1, 'first receiver address is not equal');
            assert.strictEqual(splitsArray[1][0], 2, 'second receiver address is not equal');
            assert.strictEqual(splitsArray[2][0], 3, 'third receiver address is not equal');
            assert.strictEqual(splitsArray[0][1], 30000, 'first receiver balance is not equal');
            assert.strictEqual(splitsArray[1][1], 35000, 'second receiver balance is not equal');
            assert.strictEqual(splitsArray[2][1], 20000, 'third receiver balance is not equal');
            /* check rest channel props */
            assert.strictEqual(requestCloseChannelInfo.channelStatus, 3, 'channel status is not equal');
            assert.strictEqual(requestCloseChannelInfo.channelBalance, 100000, 'channel balance is not equal');
            assert.strictEqual(requestCloseChannelInfo.appBalance, 5000, 'application balance is not equal');
            assert.strictEqual(requestCloseChannelInfo.channelSplitSize, 3, 'channel split size is not equal');
            assert.strictEqual(requestCloseChannelInfo.VLFee, 10000, 'application fee amount is not equal');
        });
        it('confirmClose', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resConfirmClose.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resConfirmClose.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resConfirmClose.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInStatusUpdatedEvents[2].channelId, 0, 'channel id is not equal');
            assert.strictEqual(CashInStatusUpdatedEvents[2].status, 4, 'channel status is not equal');
            /* from cashInStorage method */
            assert.strictEqual(resGetStatus2, 4, 'channel status is not equal');
            /* from SessionManager method */
            assert.strictEqual(resIsHasActiveCashIn, false, 'session has active channel');
            /* check balance of receivers */
            assert.strictEqual(resBalanceOfReceiver1, 30000, 'first receiver balance not equal');
            assert.strictEqual(resBalanceOfReceiver2, 35000, 'second receiver balance not equal');
            assert.strictEqual(resBalanceOfReceiver3, 20000, 'third receiver balance not equal');
            assert.strictEqual(resBalanceOfSender1, 100000, 'channel balance changed');
            assert.strictEqual(resBalanceOfSender2, 5000, 'sender balance not equal');
            /* balance of VaultLogic */
            assert.strictEqual(resBalanceOwner, 10000, 'vaultLogic balance is not equal');
            /* from CapitalHero event */
            assert.strictEqual(CapitalHeroEvents[2].channelId, 0, 'channel id is not equal');
            assert.strictEqual(CapitalHeroEvents[2].sessionId, 1, 'session id is not equal');
        });
    });
});
