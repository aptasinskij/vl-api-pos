const assert = require('chai').assert;
const ApplicationLib = artifacts.require('ApplicationLib.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const ApplicationStorage = artifacts.require('ApplicationStorage.sol');
const Database = artifacts.require('Database.sol');
const convertToNumber = require('../helpers').convertToNumber;

contract('ApplicationLib', (accounts) => {

    describe('tests for "read" methods', () => {

        let resGet;
        let appInstance;
        let resGetOwner;
        let resGetUrl;
        let resGetAddress;
        let resGetStatus;
        let resGetName;

        before(async () => {
            appInstance = await CapitalHero.deployed();
            const applicationLibInstance = await ApplicationLib.deployed();
            const applicationStorageInstance = await ApplicationStorage.deployed();
            const databaseInstance = await Database.deployed();

            /* save by ApplicationStorage method */
            await applicationStorageInstance.save(123,
                'capitalHero',
                accounts[0],
                'http://capital-hero',
                appInstance.address,
                0);
            /* get */
            resGet = await applicationLibInstance.get(databaseInstance.address, 123);
            resGet = convertToNumber(resGet, true);
            /* getName */
            resGetName = await applicationLibInstance.getName(databaseInstance.address, 123);
            /* getOwner */
            resGetOwner = await applicationLibInstance.getOwner(databaseInstance.address, 123);
            /* setApplicationUrl by ApplicationStorage */
            await applicationStorageInstance.setApplicationUrl(123, 'http://updated-url');
            /* getUrl */
            resGetUrl = await applicationLibInstance.getUrl(databaseInstance.address, 123);
            /* setApplicationAddress by ApplicationStorage */
            await applicationStorageInstance.setApplicationAddress(123, 13245);
            /* getAddress */
            resGetAddress = await applicationLibInstance.getAddress(databaseInstance.address, 123);
            resGetAddress = Number(resGetAddress);
            /* setApplicationStatus by ApplicationStorage */
            await applicationStorageInstance.setApplicationStatus(123, 2);
            /* getStatus */
            resGetStatus = await applicationLibInstance.getStatus(databaseInstance.address, 123);
            resGetStatus = Number(resGetStatus);
        });

        /*\
         # <hr>
         # <h4> get(index) </h4>
         # Description: use to get all cashInChannel info
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
            assert.strictEqual(resGet[0], 'capitalHero', 'application name is not equal');
            assert.strictEqual(resGet[1], accounts[0], 'application owner is not equal');
            assert.strictEqual(resGet[2], 'http://capital-hero', 'application url is not equal');
            assert.strictEqual(resGet[3], appInstance.address, 'application address is not equal');
            assert.strictEqual(resGet[4], 0, 'application status is not equal');
        });
        it('getOwner', () => {
            assert.strictEqual(resGetOwner, accounts[0], 'application owner name is not equal');
        });
        it('getUrl', () => {
            assert.strictEqual(resGetUrl, 'http://updated-url', 'application url is not equal');
        });
        it('getAddress', () => {
            assert.strictEqual(resGetAddress, 13245, 'application url is not equal');
        });
        it('getStatus', () => {
            assert.strictEqual(resGetStatus, 2, 'application status is not equal');
        });
        it('getName', () => {
            assert.strictEqual(resGetName, 'capitalHero', 'application name is not equal');
        });
    })
});