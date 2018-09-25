const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const TokenManager = artifacts.require('TokenManager.sol');
const ParameterStorage = artifacts.require('ParameterStorage.sol');
const {convertToNumber, sleep} = require('../../helpers');

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
        let resGetStatus3;
        let resUpdateCashInBalance;
        let resGetBalance;
        let resBalanceOf1;
        let resBalanceOf2;
        let resBalanceOf3;
        let splitsArray = [];
        let resBalanceOfReceiver1;
        let resBalanceOfReceiver2;
        let resBalanceOfReceiver3;
        let resBalanceOfSender1;
        let resBalanceOfSender2;
        let resBalanceOwner;
        let resSetVLFee;
        let resGetVLFee;

        let CashInSavedEvents = [];
        let CashInBalanceUpdatedEvents = [];
        let CashInStatusUpdatedEvents = [];

        before(async () => {
            /* get instances */
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            const tokenManagerInstance = await TokenManager.deployed();
            const parameterStorageInstance = await ParameterStorage.deployed();
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
            /* activate session */
            await sessionManagerInstance.activate(1);

            /* openCashInChannel */
            resOpenCashInChannel = await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            resGet = await cashInStorageInstance.get(0);
            resGet = convertToNumber(resGet, true);
            /* confirmOpen  */
            resConfirmOpen = await cashChannelsManagerInstance.confirmOpen(0);
            resGetStatus1 = await cashInStorageInstance.getStatus(0);
            resGetStatus1 = Number(resGetStatus1);

            /* setVLFee of ParameterStorage */
            await parameterStorageInstance.setVLFee(1000);
            /* getVLFee of ParameterStorage */
            console.log('getVLFee of ParameterStorage', Number(await parameterStorageInstance.getVLFee()));

           /* /!* setVLFee *!/
            resSetVLFee = await cashInStorageInstance.setVLFee(0, 10);
            /!* getVLFee *!/
            resGetVLFee = Number(await cashInStorageInstance.getVLFee(0));
            console.log('getVLFee of CashInStorage', resGetVLFee);*/

            /* updateCashInBalance */
            resUpdateCashInBalance = await cashChannelsManagerInstance.updateCashInBalance(0, 100000);
            resGetBalance = await cashInStorageInstance.getBalance(0);
            resGetBalance = Number(resGetBalance);
            /* --- balanceOf --- */
            resBalanceOf1 = await cashChannelsManagerInstance.balanceOf(capitalHeroInstance.address, 0);
            resBalanceOf1 = Number(resBalanceOf1);
            /* try get balanceOf wrong channel */
            /*try {
                await cashChannelsManagerInstance.balanceOf(capitalHeroInstance.address, 1);
                resBalanceOf2 = 'Method Allowed';
            } catch (e) {
                resBalanceOf2 = e.message;
            }
            /!* try get balanceOf wrong application address *!/
            try {
                await cashChannelsManagerInstance.balanceOf(321, 0);
                resBalanceOf3 = 'Method Allowed';
            } catch (e) {
                resBalanceOf3 = e.message;
            }*/
            /* closeCashInChannel */
            resCloseCashInChannel = await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [30000,35000,20000], [1,2,3]);
            resGetStatus2 = await cashInStorageInstance.getStatus(0);
            resGetStatus2 = Number(resGetStatus2);

            console.log('getVLFee of cashInStorage', Number(await cashInStorageInstance.getVLFee(0)));
            console.log('getApplicationBalance', Number(await cashInStorageInstance.getApplicationBalance(0)));
            console.log('getSplitSize', Number(await cashInStorageInstance.getSplitSize(0)));
            console.log('getBalance', Number(await cashInStorageInstance.getBalance(0)));

            /* get splits */
            // must be moved to helpers
            let resGetSplitSize = Number(await cashInStorageInstance.getSplitSize(0));
            for (let i = 0; i < resGetSplitSize; i++) {
                let resGetSplit = await cashInStorageInstance.getSplit(0, i);
                resGetSplit = convertToNumber(resGetSplit);
                splitsArray.push(resGetSplit);
            }
            /* --- */

            /* need to set Parameter fee VL before */
            /* confirmClose */
            try {
                resConfirmClose = await cashChannelsManagerInstance.confirmClose(0);
            } catch (e) {
                console.log(11111111, e)
            }
            /*resGetStatus3 = await cashInStorageInstance.getStatus(0);
            resGetStatus3 = Number(resGetStatus3);*/

            /* balanceOf receivers (TokenManager contract) */
            /*resBalanceOfReceiver1 = await tokenManagerInstance.balanceOf(1);
            resBalanceOfReceiver1 = Number(resBalanceOfReceiver1);
            resBalanceOfReceiver2 = await tokenManagerInstance.balanceOf(2);
            resBalanceOfReceiver2 = Number(resBalanceOfReceiver2);
            resBalanceOfReceiver3 = await tokenManagerInstance.balanceOf(3);
            resBalanceOfReceiver3 = Number(resBalanceOfReceiver3);
            /!* get sender balance (CashChannelsManager contract) *!/
            resBalanceOfSender1 = await cashChannelsManagerInstance.balanceOf(capitalHeroInstance.address, 0);
            resBalanceOfSender1 = Number(resBalanceOfSender1);
            /!* get sender balance (TokenManager contract) *!/
            resBalanceOfSender2 = await tokenManagerInstance.balanceOf(capitalHeroInstance.address);
            resBalanceOfSender2 = Number(resBalanceOfSender2);
            /!*console.log(11111111, await tokenManagerInstance.owner());*!/
            console.log(ownerAccount, ownerAccount);
            resBalanceOwner = await tokenManagerInstance.balanceOf(ownerAccount);
            resBalanceOwner = Number(resBalanceOwner);*/

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
        });
        it('balanceOf', () => {
            assert.strictEqual(resBalanceOf1, 100000, 'channel balance is not equal');
            /* restrict to call balanceOf wrong channel */
            assert.notEqual(resBalanceOf3, 'Method Allowed', 'allow to call balanceOf wrong channel');
            /* restrict to call balanceOf wrong application address */
            assert.notEqual(resBalanceOf3, 'Method Allowed', 'allow to call balanceOf wrong application address');
        });
        it('closeCashInChannel', () => {
            /* from CashChannelsManager logs */
            assert.isAbove(resCloseCashInChannel.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resCloseCashInChannel.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resCloseCashInChannel.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from cashInStorage event */
            assert.strictEqual(CashInStatusUpdatedEvents[1].channelId, 0, 'channel id is not equal');
            assert.strictEqual(CashInStatusUpdatedEvents[1].status, 3, 'channel status is not equal');
            /* from cashInStorage method */
            assert.strictEqual(resGetStatus2, 3, 'channel status is not equal');
            /* check splits info */
            assert.strictEqual(splitsArray[0][0], 1, 'first receiver address is not equal');
            assert.strictEqual(splitsArray[1][0], 2, 'second receiver address is not equal');
            assert.strictEqual(splitsArray[2][0], 3, 'third receiver address is not equal');
            assert.strictEqual(splitsArray[0][1], 30000, 'first receiver balance is not equal');
            assert.strictEqual(splitsArray[1][1], 35000, 'second receiver balance is not equal');
            assert.strictEqual(splitsArray[2][1], 20000, 'third receiver balance is not equal');
        });
        it('confirmClose', () => {
            /* from CashChannelsManager logs */
            /*assert.isAbove(resConfirmClose.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resConfirmClose.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resConfirmClose.receipt.gasUsed, 0, 'gasUsed is 0');
            /!* from cashInStorage event *!/
            assert.strictEqual(CashInStatusUpdatedEvents[2].channelId, 0, 'channel id is not equal');
            assert.strictEqual(CashInStatusUpdatedEvents[2].status, 4, 'channel status is not equal');
            /!* from cashInStorage method *!/
            assert.strictEqual(resGetStatus3, 4, 'channel status is not equal');*/
            /* check balance of receivers */
           /* assert.strictEqual(resBalanceOfReceiver1, 300, 'first receiver balance not equal');
            assert.strictEqual(resBalanceOfReceiver2, 350, 'second receiver balance not equal');
            assert.strictEqual(resBalanceOfReceiver3, 200, 'third receiver balance not equal');
            assert.strictEqual(resBalanceOfSender1, 1000, 'channel balance changed');
            assert.strictEqual(resBalanceOfSender2, 50, 'sender balance not equal');*/
        });
    });
});
