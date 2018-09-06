const assert = require('chai').assert;
const CashInStorage = artifacts.require('./repositories/cash-in/CashInStorage.sol');

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
        });

        it('save', () => {
            assert.strictEqual(resSave.sessionId, 1, 'sessionId is not equal');
            assert.strictEqual(resSave.application, 2, 'application address is not equal');
            assert.strictEqual(resSave.status, 3, 'status is not equal');
            assert.strictEqual(resSave.index, 0, 'index is not equal');
        });
        it('get', () => {
            assert.strictEqual(resGet[0], 1, 'sessionId is not equal');
            assert.strictEqual(resGet[1], 2, 'application address is not equal');
            assert.strictEqual(resGet[2], 0, 'balance is not equal');
            assert.strictEqual(resGet[3], 3, 'status is not equal');
            assert.strictEqual(resGet[4], 0, 'index is not equal');
        });
        it('getSessionId', () => {
            assert.strictEqual(resGetSessionID, 1, 'sessionId is not equal');
        });
        it('getApplicationAddress', () => {
            assert.strictEqual(resGetApplication, 2, 'application address is not equal');
        });
        it('getApplicationAndSessionId', () => {
            assert.strictEqual(resGetApplicationAndSessionId[0], 2, 'application address is not equal');
            assert.strictEqual(resGetApplicationAndSessionId[1], 1, 'sessionId is not equal');
        });
        it('setBalance', () => {
            assert.strictEqual(resSetBalance.index, 0, 'index is not equal');
            assert.strictEqual(resSetBalance.amount, 500, 'amount is not equal');
        });
        it('getBalance', () => {
            assert.strictEqual(resGetBalance, 500, 'amount is not equal');
        });
        it('setStatus', () => {
            assert.strictEqual(resSetStatus.index, 0, 'index is not equal');
            assert.strictEqual(resSetStatus.status, 5, 'status is not equal');
        });
        it('addSplit', () => {
            assert.strictEqual(resAddSplit.index, 0, 'index is not equal');
            assert.strictEqual(resAddSplit.party, 10, 'party is not equal');
            assert.strictEqual(resAddSplit.amount, 20, 'amount is not equal');
        });
        it('addSplits', () => {
            assert.notEqual(resAddSplits.receipt.transactionHash, '', 'transactionHash is empty');
            assert.isAbove(resAddSplits.receipt.gasUsed, 0, 'gasUsed is 0');
        });
        it('getSplitSize', () => {
            assert.strictEqual(resGetSplitSize1, 1, 'split1: split size is not equal');
            assert.strictEqual(resGetSplitSize2, 5, 'split1: split size is not equal');
        });
        it('getSplit', () => {
            assert.strictEqual(resGetSplit1[0], 10, 'split 1, subSplit 0: party is not equal');
            assert.strictEqual(resGetSplit1[1], 20, 'split 1, subSplit 0: amount is not equal');
            assert.strictEqual(resGetSplit2[0], 1, 'split 8, subSplit 0: party is not equal');
            assert.strictEqual(resGetSplit2[1], 10, 'split 8, subSplit 0: amount is not equal');
            assert.strictEqual(resGetSplit3[0], 5, 'split 8, subSplit 4: party is not equal');
            assert.strictEqual(resGetSplit3[1], 50, 'split 8, subSplit 0: amount is not equal');
        });
    })
});

function convertToNumber(structure) { // convert props/elements no number
    if (structure instanceof Object && !Array.isArray(structure)) { // if Object
        for (let key in structure) {
            if (structure.hasOwnProperty(key)) {
                structure[key] = Number(structure[key]);
            }
        }
    } else if (Array.isArray(structure)) { // if Array
        structure = structure.map(item => Number(item));
    }
    return structure;
}