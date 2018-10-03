/*
const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const TokenManager = artifacts.require('TokenManager.sol');
const ParameterStorage = artifacts.require('ParameterStorage.sol');
const {convertToNumber} = require('../../../helpers');

contract('CashChannelsManager', (accounts) => {

    describe('tricky money amounts', () => {
        const ownerAccount = accounts[0];
        let capitalHeroInstance;
        let resGetStatus2;
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
        let resIsHasActiveCashIn;

        before(async () => {
            /!* get instances *!/
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            const tokenManagerInstance = await TokenManager.deployed();
            const parameterStorageInstance = await ParameterStorage.deployed();
            capitalHeroInstance = await CapitalHero.deployed();

            /!* registerApplication Capital Hero *!/
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /!* createSession for Capital Hero *!/
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /!* activate session *!/
            await sessionManagerInstance.activate(1);

            /!* setVLFee of ParameterStorage *!/
            await parameterStorageInstance.setVLFee(1300);
            /!* openCashInChannel *!/
            await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            /!* confirmOpen *!/
            await cashChannelsManagerInstance.confirmOpen(0);

            /!* updateCashInBalance *!/
            await cashChannelsManagerInstance.updateCashInBalance(0, 123212);
            resGetBalance = Number(await cashInStorageInstance.getBalance(0));
            /!* balanceOf *!/
            resBalanceOf = Number(await cashChannelsManagerInstance.balanceOf(capitalHeroInstance.address, 0));

            /!* closeCashInChannel *!/
            await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [50005, 40190], [1, 2]);
            requestCloseChannelInfo = {
                VLFee: Number(await cashInStorageInstance.getVLFee(0)),
                appBalance: Number(await cashInStorageInstance.getApplicationBalance(0)),
                channelSplitSize: Number(await cashInStorageInstance.getSplitSize(0)),
                channelBalance: Number(await cashInStorageInstance.getBalance(0)),
                channelStatus: Number(await cashInStorageInstance.getStatus(0))
            };

            /!* get splits *!/
            // must be moved to helpers
            let resGetSplitSize = Number(await cashInStorageInstance.getSplitSize(0));
            for (let i = 0; i < resGetSplitSize; i++) {
                let resGetSplit = await cashInStorageInstance.getSplit(0, i);
                splitsArray.push(convertToNumber(resGetSplit));
            }
            /!* --- *!/

            /!* confirmClose *!/
            await cashChannelsManagerInstance.confirmClose(0);
            /!* check different params *!/
            resIsHasActiveCashIn = await sessionManagerInstance.isHasActiveCashIn(0);
            resGetStatus2 = Number(await cashInStorageInstance.getStatus(0));
            /!* balanceOf receivers (TokenManager contract) *!/
            resBalanceOfReceiver1 = Number(await tokenManagerInstance.balanceOf(1));
            resBalanceOfReceiver2 = Number(await tokenManagerInstance.balanceOf(2));
            resBalanceOfReceiver3 = Number(await tokenManagerInstance.balanceOf(3));
            /!* get sender balance (CashChannelsManager contract) *!/
            resBalanceOfSender1 = Number(await cashChannelsManagerInstance.balanceOf(capitalHeroInstance.address, 0));
            /!* get sender balance (TokenManager contract) *!/
            resBalanceOfSender2 = Number(await tokenManagerInstance.balanceOf(capitalHeroInstance.address));
            resBalanceOwner = Number(await tokenManagerInstance.balanceOf(ownerAccount));
        });

        it('closeCashInChannel', () => {
            /!* check splits info *!/
            assert.strictEqual(splitsArray[0][0], 1, 'first receiver address is not equal');
            assert.strictEqual(splitsArray[1][0], 2, 'second receiver address is not equal');
            assert.strictEqual(splitsArray[0][1], 50005, 'first receiver balance is not equal');
            assert.strictEqual(splitsArray[1][1], 40190, 'second receiver balance is not equal');
            /!* check rest channel props *!/
            assert.strictEqual(requestCloseChannelInfo.channelStatus, 3, 'channel status is not equal');
            assert.strictEqual(requestCloseChannelInfo.channelBalance, 123212, 'channel balance is not equal');
            assert.strictEqual(requestCloseChannelInfo.appBalance, 17000, 'application balance is not equal');
            assert.strictEqual(requestCloseChannelInfo.channelSplitSize, 2, 'channel split size is not equal');
            assert.strictEqual(requestCloseChannelInfo.VLFee, 16017, 'application fee amount is not equal');
        });
        it('confirmClose', () => {
            /!* from cashInStorage method *!/
            assert.strictEqual(resGetStatus2, 4, 'channel status is not equal');
            /!* from SessionManager method *!/
            assert.strictEqual(resIsHasActiveCashIn, false, 'session has active channel');
            /!* check balance of receivers *!/
            assert.strictEqual(resBalanceOfReceiver1, 50005, 'first receiver balance not equal');
            assert.strictEqual(resBalanceOfReceiver2, 40190, 'second receiver balance not equal');
            assert.strictEqual(resBalanceOfSender1, 123212, 'channel balance changed');
            assert.strictEqual(resBalanceOfSender2, 17000, 'sender balance not equal');
            /!* balance of VaultLogic *!/
            assert.strictEqual(resBalanceOwner, 16017, 'vaultLogic balance is not equal');
        });
    });
});*/