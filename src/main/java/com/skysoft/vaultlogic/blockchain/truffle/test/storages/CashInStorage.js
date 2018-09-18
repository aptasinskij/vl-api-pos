const assert = require('chai').assert;
const CashInStorage = artifacts.require('CashInStorage.sol');
const {convertToNumber} = require('../helpers');

/*\
* CashInStorage
\*/

contract('CashInStorage', () => {

    describe('tests for all methods', () => {
        let resSave;
        let resGet;
        let resGetSessionID;
        let resGetApplication;
        let resGetApplicationAndSessionId;
        let resSetBalance;
        let resGetBalance;
        let resSetStatus;
        let resGetStatus;
        let resAddSplit;
        let resAddSplits;
        let resGetSplitSize1;
        let resGetSplitSize2;
        let resGetSplit1;
        let resGetSplit2;
        let resGetSplit3;
        let resGetAfterAll;

        before(async () => {
            const instance = await CashInStorage.deployed();
            /* save */
            resSave = await instance.save(1, 2, 3);
            resSave = convertToNumber(resSave.logs[0].args);
            /* get */
            resGet = await instance.get(0);
            resGet = convertToNumber(resGet);
            /* getSessionId */
            resGetSessionID = await instance.getSessionId(0);
            resGetSessionID = Number(resGetSessionID);
            /* getApplication */
            resGetApplication = await instance.getApplication(0);
            resGetApplication = Number(resGetApplication);
            /* getApplicationAndSessionId */
            resGetApplicationAndSessionId = await instance.getApplicationAndSessionId(0);
            resGetApplicationAndSessionId = convertToNumber(resGetApplicationAndSessionId);
            /* setBalance */
            resSetBalance = await instance.setBalance(0, 500);
            resSetBalance = convertToNumber(resSetBalance.logs[0].args);
            /* getBalance */
            resGetBalance = await instance.getBalance(0);
            resGetBalance = Number(resGetBalance);
            /* setStatus */
            resSetStatus = await instance.setStatus(0, 5);
            resSetStatus = convertToNumber(resSetStatus.logs[0].args);
            /* getStatus */
            resGetStatus = await instance.getStatus(0);
            resGetStatus = Number(resGetStatus);
            /* addSplit */
            resAddSplit = await instance.addSplit(0, 10, 20);
            resAddSplit = convertToNumber(resAddSplit.logs[0].args);
            /* addSplits */
            resAddSplits = await instance.addSplits(8, [1,2,3,4,5], [10,20,30,40,50]);
            /* getSplitSize */
            resGetSplitSize1 = await instance.getSplitSize(0);
            resGetSplitSize1 = Number(resGetSplitSize1);
            resGetSplitSize2 = await instance.getSplitSize(8);
            resGetSplitSize2 = Number(resGetSplitSize2);
            /* getSplit */
            resGetSplit1 = await instance.getSplit(0, 0);
            resGetSplit1 = convertToNumber(resGetSplit1);
            resGetSplit2 = await instance.getSplit(8, 0);
            resGetSplit2 = convertToNumber(resGetSplit2);
            resGetSplit3 = await instance.getSplit(8, 4);
            resGetSplit3 = convertToNumber(resGetSplit3);
            /* get (after all) */
            resGetAfterAll = await instance.get(0);
            resGetAfterAll = convertToNumber(resGetAfterAll);
        });

        /*\
         # <hr>
         # <h4> save(sessionId, application, status) </h4>
         # save new cashInChannel
         > Arguments
         - (uint256) sessionId - session id
         - (address) application - application address
         - (uint256) status - session status
         > Emits
         - (event) CashInSaved
        \*/
        it('save', () => {
            assert.strictEqual(resSave.sessionId, 1, 'sessionId is not equal');
            assert.strictEqual(resSave.application, 2, 'application address is not equal');
            assert.strictEqual(resSave.status, 3, 'status is not equal');
            assert.strictEqual(resSave.channelId, 0, 'index is not equal');
        });

        /*\
         # <hr>
         # <h4> get(index) </h4>
         # get all info of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         > Returns
         - (uint256) sessionId - session id
         - (address) application - application address
         - (uint256) balance - cashInChannel balance
         - (uint256) status - cashInChannel status
         - (uint256) splitSize - cashInChannel splitSize
        \*/
        it('get', () => {
            assert.strictEqual(resGet[0], 1, 'sessionId is not equal');
            assert.strictEqual(resGet[1], 2, 'application address is not equal');
            assert.strictEqual(resGet[2], 0, 'balance is not equal');
            assert.strictEqual(resGet[3], 3, 'status is not equal');
            assert.strictEqual(resGet[4], 0, 'index is not equal');
            /* after all */
            assert.strictEqual(resGetAfterAll[0], 1, 'sessionId is not equal (after all)');
            assert.strictEqual(resGetAfterAll[1], 2, 'application address is not equal (after all)');
            assert.strictEqual(resGetAfterAll[2], 500, 'balance is not equal (after all)');
            assert.strictEqual(resGetAfterAll[3], 5, 'status is not equal (after all)');
            assert.strictEqual(resGetAfterAll[4], 1, 'index is not equal (after all)');
        });

        /*\
         # <hr>
         # <h4> getSessionId(index) </h4>
         # get session id of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         > Returns
         - (uint256) sessionId - session id
        \*/
        it('getSessionId', () => {
            assert.strictEqual(resGetSessionID, 1, 'sessionId is not equal');
        });

        /*\
         # <hr>
         # <h4> getApplicationAddress(index) </h4>
         # get application address of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         > Returns
         - (address) application - application address
        \*/
        it('getApplicationAddress', () => {
            assert.strictEqual(resGetApplication, 2, 'application address is not equal');
        });

        /*\
         # <hr>
         # <h4> getApplicationAndSessionId(index) </h4>
         # get application address and session id of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         > Returns
         - (address) application - application address
         - (uint256) sessionId - session id
        \*/
        it('getApplicationAndSessionId', () => {
            assert.strictEqual(resGetApplicationAndSessionId[0], 2, 'application address is not equal');
            assert.strictEqual(resGetApplicationAndSessionId[1], 1, 'sessionId is not equal');
        });

        /*\
         # <hr>
         # <h4> setBalance(index, amount) </h4>
         # save set balance of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         - (address) amount - cashInChannel money to insert amount
         > Emits
         - (event) CashInBalanceUpdated
        \*/
        it('setBalance', () => {
            assert.strictEqual(resSetBalance.channelId, 0, 'index is not equal');
            assert.strictEqual(resSetBalance.amount, 500, 'amount is not equal');
        });

        /*\
         # <hr>
         # <h4> getBalance(index) </h4>
         # get balance of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         - (address) amount - cashInChannel amount
         > Emits
         - (event) CashInBalanceUpdated
        \*/
        it('getBalance', () => {
            assert.strictEqual(resGetBalance, 500, 'amount is not equal');
        });

        /*\
         # <hr>
         # <h4> setStatus(index) </h4>
         # set status of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         - (uint256) status - cashInChannel status
         > Emits
         - (event) CashInStatusUpdated
        \*/
        it('setStatus', () => {
            assert.strictEqual(resSetStatus.channelId, 0, 'index is not equal');
            assert.strictEqual(resSetStatus.status, 5, 'status is not equal');
        });

        /*\
         # <hr>
         # <h4> addSplit(index, party, amount) </h4>
         # add split to cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         - (address) party - cashInChannel party
         - (uint256) amount - cashInChannel amount
         > Emits
         - (event) CashInSplitAdded
        \*/
        it('addSplit', () => {
            assert.strictEqual(resAddSplit.channelId, 0, 'index is not equal');
            assert.strictEqual(resAddSplit.party, 10, 'party is not equal');
            assert.strictEqual(resAddSplit.amount, 20, 'amount is not equal');
        });

        /*\
         # <hr>
         # <h4> addSplit(index, parties, amounts) </h4>
         # get status of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         - (address[]) parties - array of cashInChannel parties
         - (uint256[]) amounts - array of cashInChannel amounts
        \*/
        it('addSplits', () => {
            assert.notEqual(resAddSplits.receipt.transactionHash, '', 'transactionHash is empty');
            assert.isAbove(resAddSplits.receipt.gasUsed, 0, 'gasUsed is 0');
        });

        /*\
         # <hr>
         # <h4> getSplitSize(index) </h4>
         # get split size of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
          > Returns
         - (uint256) splitSize - cashInChannel split size
        \*/
        it('getSplitSize', () => {
            assert.strictEqual(resGetSplitSize1, 1, 'split1: split size is not equal');
            assert.strictEqual(resGetSplitSize2, 5, 'split2: split size is not equal');
        });

        /*\
         # <hr>
         # <h4> getSplit(index, subIndex) </h4>
         # get split size of cashInChannel
         > Arguments
         - (uint256) index - cashInChannel id
         - (uint256) subIndex - cashInChannel split index in array
         > Returns
         - (address) party - cashInChannel party
         - (uint256) subIndex - cashInChannel split index in array
        \*/
        it('getSplit', () => {
            assert.strictEqual(resGetSplit1[0], 10, 'split 1, subSplit 0: party is not equal');
            assert.strictEqual(resGetSplit1[1], 20, 'split 1, subSplit 0: amount is not equal');
            assert.strictEqual(resGetSplit2[0], 1, 'split 8, subSplit 0: party is not equal');
            assert.strictEqual(resGetSplit2[1], 10, 'split 8, subSplit 0: amount is not equal');
            assert.strictEqual(resGetSplit3[0], 5, 'split 8, subSplit 4: party is not equal');
            assert.strictEqual(resGetSplit3[1], 50, 'split 8, subSplit 0: amount is not equal');
        });

        /* events description */

        /*\
         # <hr>
         # <h4> CashInSaved </h4>
         # get info of saved cashInChannel (emits on "save" method call)
         > Returns
         - (uint256) channelId - channel id
         - (uint256) sessionId - session id
         - (address) application - application address
         - (uint256) status - session status
        \*/

        /*\
         # <hr>
         # <h4> CashInBalanceUpdated </h4>
         # get updated balance of cashInChannel (emits on "setBalance" method call)
         > Returns
         - (uint256) channelId - channel id
         - (uint256) amount - channel balance
        \*/

        /*\
         # <hr>
         # <h4> CashInStatusUpdated </h4>
         # get updated status of cashInChannel (emits on "setStatus" method call)
         > Returns
         - (uint256) channelId - channel id
         - (uint256) status - channel status
        \*/

        /*\
         # <hr>
         # <h4> CashInSplitAdded </h4>
         # get updated split info of cashInChannel (emits on "addSplit" method call)
         > Returns
         - (uint256) channelId - channel id
         - (address) party - party address
         - (uint256) amount - party balance
        \*/
    })
});