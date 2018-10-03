/*
const assert = require('chai').assert;
const ApplicationStorage = artifacts.require('ApplicationStorage.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const {convertToNumber, sleep} = require('../helpers');

contract('ApplicationManager', () => {

    describe('tests for all methods', () => {
        let resRegisterApplication;
        let resGet;
        let resEnableApplication;
        let resGetApplicationStatus;

        let ApplicationSavedEvents = [];
        let ApplicationStatusUpdatedEvents = [];

        before(async () => {
            const storageInstance = await ApplicationStorage.deployed();
            const managerInstance = await ApplicationManager.deployed();
            /!* watch events *!/
            storageInstance.ApplicationSaved().watch((err, response) => {
                ApplicationSavedEvents.push(convertToNumber(response.args));
            });
            storageInstance.ApplicationStatusUpdated().watch((err, response) => {
                ApplicationStatusUpdatedEvents.push(convertToNumber(response.args, true));
            });
            /!* registerApplication *!/
            resRegisterApplication = await managerInstance.registerApplication(1, 'my-app', 235, 'http://my-app', 465);
            resGet = await storageInstance.get(1);
            resGet = convertToNumber(resGet);
            /!* enableApplication *!/
            resEnableApplication = await managerInstance.enableApplication(1);
            resGetApplicationStatus = await storageInstance.getApplicationStatus(1);
            resGetApplicationStatus = Number(resGetApplicationStatus);

            sleep(3000);
        });

        it('registerApplication', () => {
            /!* from manager event *!/
            assert.isAbove(resRegisterApplication.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resRegisterApplication.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resRegisterApplication.receipt.gasUsed, 0, 'gasUsed is 0');
            /!* from cashInStorage event *!/
            assert.strictEqual(ApplicationSavedEvents[0].name, 'my-app', 'application name is not equal');
            assert.strictEqual(ApplicationSavedEvents[0].owner, 235, 'session id is not equal');
            assert.strictEqual(ApplicationSavedEvents[0].url, 'http://my-app', 'application address is not equal');
            assert.strictEqual(ApplicationSavedEvents[0].status, 0, 'channel status is not equal');
            /!* from storage method *!/
            assert.strictEqual(resGet[0], 'my-app', 'application name is not equal');
            assert.strictEqual(resGet[1], 235, 'application owner is not equal');
            assert.strictEqual(resGet[2], 'http://my-app', 'application url is not equal');
            assert.strictEqual(resGet[3], 465, 'application address is not equal');
            assert.strictEqual(resGet[4], 0, 'application status is not equal');
        });
        it('enableApplication', () => {
            /!* from manager event *!/
            assert.isAbove(resEnableApplication.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resEnableApplication.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resEnableApplication.receipt.gasUsed, 0, 'gasUsed is 0');
            /!* from cashInStorage event *!/
            assert.strictEqual(ApplicationStatusUpdatedEvents[0].appId, 1, 'application id is not equal');
            assert.strictEqual(ApplicationStatusUpdatedEvents[0].status, 1, 'status is not equal');
            /!* from storage method *!/
            assert.strictEqual(resGetApplicationStatus, 1, 'application status is not equal');
        });
    });
});*/
