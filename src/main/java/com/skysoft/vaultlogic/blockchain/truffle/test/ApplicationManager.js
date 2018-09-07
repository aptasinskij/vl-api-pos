const assert = require('chai').assert;
const ApplicationStorage = artifacts.require('./repositories/cash-in/ApplicationStorage.sol');
const ApplicationManager = artifacts.require('./services/ApplicationManager.sol');
const convertToNumber = require('./helpers').convertToNumber;

contract('ApplicationManager', () => {

    describe('tests for all methods', () => {
        let resRegisterApplication;
        let resGet;
        let resEnableApplication;
        let resGetApplicationStatus;

        before(async () => {
            const storageInstance = await ApplicationStorage.deployed();
            const managerInstance = await ApplicationManager.deployed();
            /* registerApplication */
            resRegisterApplication = await managerInstance.registerApplication(1, 'my-app', 235, 'http://my-app', 465);
            resGet = await storageInstance.get(1);
            resGet = convertToNumber(resGet);
            /* enableApplication */
            resEnableApplication = await managerInstance.enableApplication(1);
            resGetApplicationStatus = await storageInstance.getApplicationStatus(1);
            resGetApplicationStatus = Number(resGetApplicationStatus);
        });

        it('registerApplication', () => {
            /* from manager event */
            assert.isAbove(resRegisterApplication.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resRegisterApplication.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resRegisterApplication.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from storage method */
            assert.strictEqual(resGet[0], 'my-app', 'application name is not equal');
            assert.strictEqual(resGet[1], 235, 'application owner is not equal');
            assert.strictEqual(resGet[2], 'http://my-app', 'application url is not equal');
            assert.strictEqual(resGet[3], 465, 'application address is not equal');
            assert.strictEqual(resGet[4], 0, 'application status is not equal');
        });
        it('enableApplication', () => {
            /* from manager event */
            assert.isAbove(resEnableApplication.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resEnableApplication.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resEnableApplication.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from storage method */
            assert.strictEqual(resGetApplicationStatus, 1, 'application status is not equal');
        });
    });
});