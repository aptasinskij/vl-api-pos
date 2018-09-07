const assert = require('chai').assert;
const ApplicationStorage = artifacts.require('./repositories/cash-in/ApplicationStorage.sol');
const convertToNumber = require('./helpers').convertToNumber;

contract('ApplicationStorage', () => {

    describe('tests for all methods', () => {
        let resSave;
        let resGet;
        let resGetApplicationName;
        let resGetApplicationOwner;
        let resGetApplicationUrl;
        let resSetApplicationUrl;
        let resSetApplicationAddress;
        let resGetApplicationAddress;
        let resSetApplicationStatus;
        let resGetApplicationStatus;
        let resGetAfterAll;

        before(async () => {
            const instance = await ApplicationStorage.deployed();
            /* save */
            resSave = await instance.save(1, 'app name', 235, 'https://my-app-url', 456, 4);
            resSave = convertToNumber(resSave.logs[0].args);
            /* get */
            resGet = await instance.get(1);
            resGet = convertToNumber(resGet);
            /* getApplicationName */
            resGetApplicationName = await instance.getApplicationName(1);
            /* getApplicationOwner */
            resGetApplicationOwner = await instance.getApplicationOwner(1);
            resGetApplicationOwner = Number(resGetApplicationOwner);
            /* setApplicationUrl */
            resSetApplicationUrl = await instance.setApplicationUrl(1, 'http://new-url');
            resSetApplicationUrl = convertToNumber(resSetApplicationUrl.logs[0].args);
            /* getApplicationUrl */
            resGetApplicationUrl = await instance.getApplicationUrl(1);
            /* setApplicationAddress */
            resSetApplicationAddress = await instance.setApplicationAddress(1, 999);
            resSetApplicationAddress = convertToNumber(resSetApplicationAddress.logs[0].args);
            /* getApplicationAddress */
            resGetApplicationAddress = await instance.getApplicationAddress(1);
            resGetApplicationAddress = Number(resGetApplicationAddress);
            /* setApplicationStatus */
            resSetApplicationStatus = await instance.setApplicationStatus(1, 8);
            resSetApplicationStatus = convertToNumber(resSetApplicationStatus.logs[0].args);
            /* getApplicationStatus */
            resGetApplicationStatus = await instance.getApplicationStatus(1);
            resGetApplicationStatus = Number(resGetApplicationStatus);
            /* get (after all) */
            resGetAfterAll = await instance.get(1);
            resGetAfterAll = convertToNumber(resGetAfterAll);
        });

        it('save', () => {
            assert.strictEqual(resSave.appId, 1, 'application Id is not equal');
            assert.strictEqual(resSave.name, 'app name', 'application name is not equal');
            assert.strictEqual(resSave.owner, 235, 'application owner is not equal');
            assert.strictEqual(resSave.url, 'https://my-app-url', 'application url is not equal');
            assert.strictEqual(resSave.appAddr, 456, 'application address is not equal');
            assert.strictEqual(resSave.status, 4, 'application status is not equal');
        });
        it('get', () => {
            assert.strictEqual(resGet[0], 'app name', 'application name is not equal');
            assert.strictEqual(resGet[1], 235, 'application owner is not equal');
            assert.strictEqual(resGet[2], 'https://my-app-url', 'application url is not equal');
            assert.strictEqual(resGet[3], 456, 'application address is not equal');
            assert.strictEqual(resGet[4], 4, 'application status is not equal');
            /* after all */
            assert.strictEqual(resGetAfterAll[0], 'app name', 'application name is not equal (after all)');
            assert.strictEqual(resGetAfterAll[1], 235, 'application owner is not equal (after all)');
            assert.strictEqual(resGetAfterAll[2], 'http://new-url', 'application url is not equal (after all)');
            assert.strictEqual(resGetAfterAll[3], 999, 'application address is not equal (after all)');
            assert.strictEqual(resGetAfterAll[4], 8, 'application status is not equal (after all)');
        });
        it('getApplicationName', () => {
            assert.strictEqual(resGetApplicationName, 'app name', 'application name is not equal');
        });
        it('getApplicationOwner', () => {
            assert.strictEqual(resGetApplicationOwner, 235, 'application owner is not equal');
        });
        it('setApplicationUrl', () => {
            assert.strictEqual(resSetApplicationUrl.appId, 1, 'application Id is not equal');
            assert.strictEqual(resSetApplicationUrl.url, 'http://new-url', 'application url is not equal');
        });
        it('getApplicationUrl', () => {
            assert.strictEqual(resGetApplicationUrl, 'http://new-url', 'application url is not equal');
        });
        it('setApplicationAddress', () => {
            assert.strictEqual(resSetApplicationAddress.appId, 1, 'application Id is not equal');
            assert.strictEqual(resSetApplicationAddress.appAddr, 999, 'application address is not equal');
        });
        it('getApplicationAddress', () => {
            assert.strictEqual(resGetApplicationAddress, 999, 'application url is not equal');
        });
        it('setApplicationStatus', () => {
            assert.strictEqual(resSetApplicationStatus.appId, 1, 'application Id is not equal');
            assert.strictEqual(resSetApplicationStatus.status, 8, 'application status is not equal');
        });
        it('getApplicationStatus', () => {
            assert.strictEqual(resGetApplicationStatus, 8, 'application status is not equal');
        });
    })
});