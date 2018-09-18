const assert = require('chai').assert;
const CashInLib = artifacts.require('CashInLib.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');
const Database = artifacts.require('Database.sol');
const {convertToNumber} = require('../helpers');

/*\
* CashInLib
\*/

contract('CashInLib', () => {

    describe('tests for "read" methods', () => {

        let resGet;
        let appInstance;
        let resGetSessionId;
        let resGetApplication;
        let resGetBalance;
        let resGetStatus;
        let resGetSplitSize;
        let resGetSplit;

        before(async () => {
            appInstance = await CapitalHero.deployed();
            const cashInLibInstance = await CashInLib.deployed();
            const cashInStorageInstance = await CashInStorage.deployed();
            const databaseInstance = await Database.deployed();

            /* save by CashInStorage method */
            await cashInStorageInstance.save(123,
                appInstance.address,
                1);
            /* get */
            resGet = await cashInLibInstance.get(databaseInstance.address, 0);
            resGet = convertToNumber(resGet, true);
            /* getSessionId */
            resGetSessionId = await cashInLibInstance.getSessionId(databaseInstance.address, 0);
            resGetSessionId = Number(resGetSessionId);
            /* getApplication */
            resGetApplication = await cashInLibInstance.getApplication(databaseInstance.address, 0);
            /* setBalance by CashInStorage method */
            await cashInStorageInstance.setBalance(0, 100);
            /* getBalance */
            resGetBalance = await cashInLibInstance.getBalance(databaseInstance.address, 0);
            resGetBalance = Number(resGetBalance);
            /* setStatus by CashInStorage method */
            await cashInStorageInstance.setStatus(0, 2);
            /* getStatus */
            resGetStatus = await cashInLibInstance.getStatus(databaseInstance.address, 0);
            resGetStatus = Number(resGetStatus);
            /* addSplit by CashInStorage method */
            await cashInStorageInstance.addSplit(0, 1, 500);
            /* addSplits by CashInStorage method */
            await cashInStorageInstance.addSplits(0, [2,3], [200, 300]);
            /* getSplitSize */
            resGetSplitSize = await cashInLibInstance.getSplitSize(databaseInstance.address, 0);
            resGetSplitSize = Number(resGetSplitSize);
            /* getSplit */
            resGetSplit = await cashInLibInstance.getSplit(databaseInstance.address, 0, 1);
            resGetSplit = convertToNumber(resGetSplit);
        });

        /*\
         # <hr>
         # <h4> get(self, index) </h4>
         # Get all info of cashInChannel
         > Arguments
         - (address) self - self database address
         - (uint256) index - cashInChannel id
         > Returns
         - (string) sessionId - session id
         - (address) application - application address
         - (uint256) balance - cashInChannel balance
         - (uint256) status - cashInChannel status
         - (uint256) splitSize - cashInChannel amount of splits
        \*/
        it('get', () => {
            assert.strictEqual(resGet[0], 123, 'session id is not equal');
            assert.strictEqual(resGet[1], appInstance.address, 'application address is not equal');
            assert.strictEqual(resGet[2], 0, 'cashInChannel balance is not equal');
            assert.strictEqual(resGet[3], 1, 'cashInChannel status is not equal');
            assert.strictEqual(resGet[4], 0, 'cashInChannel split size is not equal');
        });

        /*\
         # <hr>
         # <h4> getSessionId(self, index) </h4>
         # Get session id of cashInChannel
         > Arguments
         - (address) self - self database address
         - (uint256) index - cashInChannel id
         > Returns
         - (address) sessionId - session id
        \*/
        it('getSessionId', () => {
            assert.strictEqual(resGetSessionId, 123, 'session id is not equal');
        });

        /*\
         # <hr>
         # <h4> getApplication(self, index) </h4>
         # Get application address of cashInChannel
         > Arguments
         - (address) self - self database address
         - (uint256) index - cashInChannel id
         > Returns
         - (address) application - application address
        \*/
        it('getApplication', () => {
            assert.strictEqual(resGetApplication, appInstance.address, 'application address is not equal');
        });

        /*\
         # <hr>
         # <h4> getBalance(self, index) </h4>
         # Get balance of cashInChannel
         > Arguments
         - (address) self - self database address
         - (uint256) index - cashInChannel id
         > Returns
         - (uint256) balance - cashInChannel balance
        \*/
        it('getBalance', () => {
            assert.strictEqual(resGetBalance, 100, 'cashInChannel balance is not equal');
        });

        /*\
         # <hr>
         # <h4> getStatus(self, index) </h4>
         # Get status of application
         > Arguments
         - (address) self - self database address
         - (uint256) index - application id
         > Returns
         - (uint256) status - application status
        \*/
        it('getStatus', () => {
            assert.strictEqual(resGetStatus, 2, 'cashInChannel status is not equal');
        });/*

        /*\
         # <hr>
         # <h4> getSplitSize(self, appId) </h4>
         # Get split's amount of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId -  cashInChannel id
         > Returns
         - (uint256) size - amount of splits
        \*/
        it('getSplitSize', () => {
            assert.strictEqual(resGetSplitSize, 3, 'cashInChannel split size is not equal');
        });

        /*\
         # <hr>
         # <h4> getSplit(self, appId, subIndex) </h4>
         # Get owner of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         - (uint256) subIndex - split party id of cashInChannel
         > Returns
         - (address) party -  split party address of cashInChannel
         - (uint256) fee - split party fee size of cashInChannel
        \*/
        it('getSplit', () => {
            assert.strictEqual(resGetSplit[0], 2, 'cashInChannel split party address is not equal');
            assert.strictEqual(resGetSplit[1], 200, 'cashInChannel split party fee is not equal');
        });

        /* methods below are not tested, only description provided */

        /*\
         # <hr>
         # <h4> save(self, sessionId, application, status) </h4>
         # Save new cashInChannel
         > Arguments
         - (address) self - self database address
         - (uint256) sessionId - session id
         - (address) application - application address
         - (uint256) status - cashInChannel status
        \*/

        /*\
         # <hr>
         # <h4> setBalance(self, index, amount) </h4>
         # Set balance of cashInChannel
         > Arguments
         - (address) self - self database address
         - (uint256) index - cashInChannel id
         - (string) amount - cashInChannel money amount
        \*/

        /*\
         # <hr>
         # <h4> setStatus(self, index, status) </h4>
         # Set status of cashInChannel
         > Arguments
         - (address) self - self database address
         - (uint256) index - cashInChannel id
         - (address) status - cashInChannel status
        \*/

        /*\
         # <hr>
         # <h4> addSplit(self, index, receiver, amount) </h4>
         # Save new split of cashInChannel
         > Arguments
         - (address) self - self database address
         - (uint256) index - cashInChannel id
         - (uint256) receiver - receiver address
         - (uint256) amount - fee size
        \*/

        /*\
         # <hr>
         # <h4> addSplits(self, index, receivers, amounts) </h4>
         # Save new split of cashInChannel
         > Arguments
         - (address) self - self database address
         - (uint256) index - cashInChannel id
         - (uint256) receiver - array of receivers addresses
         - (uint256) amount - array of fee sizes
        \*/
    })
});