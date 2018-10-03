/*
const assert = require('chai').assert;
const SessionStorage = artifacts.require('SessionStorage.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const {convertToNumber, sleep} = require('../helpers');

contract('SessionManager', () => {

    describe('tests for all methods', () => {
        let resCreateSession;
        let resGetSession;
        let resCloseSession;
        let resGetStatus1;
        let resConfirmClose;
        let resGetStatus2;

        let SavedEvents = [];
        let StatusUpdatedEvents = [];

        before(async () => {
            /!* get instances *!/
            const sessionStorageInstance = await SessionStorage.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            const capitalHeroInstance = await CapitalHero.deployed();
            /!* watch events *!/
            sessionStorageInstance.Saved().watch((err, response) => {
                SavedEvents.push(convertToNumber(response.args, true));
            });
            sessionStorageInstance.StatusUpdated().watch((err, response) => {
                StatusUpdatedEvents.push(convertToNumber(response.args, true));
            });
            /!* registerApplication Capital Hero *!/
            applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /!* createSession *!/
            resCreateSession = await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            resGetSession = await sessionStorageInstance.getSession(1);
            resGetSession = convertToNumber(resGetSession);
            /!* closeSession *!/
            resCloseSession = await sessionManagerInstance.closeSession(1);
            resGetStatus1 = await sessionStorageInstance.getStatus(1);
            resGetStatus1 = Number(resGetStatus1);
            /!* confirmClose *!/
            resConfirmClose = await sessionManagerInstance.confirmClose(1);
            resGetStatus2 = await sessionStorageInstance.getStatus(1);
            resGetStatus2 = Number(resGetStatus2);

            sleep(3000); // for make sure events handles
        });

        it('createSession', () => {
            /!* from SessionManager logs *!/
            assert.isAbove(resCreateSession.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resCreateSession.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resCreateSession.receipt.gasUsed, 0, 'gasUsed is 0');
            /!* from SessionStorage event *!/
            assert.strictEqual(SavedEvents[0].sessionId, 1, 'session id is not equal');
            assert.strictEqual(SavedEvents[0].appId, 2, 'application id is not equal');
            assert.strictEqual(SavedEvents[0].xToken, '1a2b3c', 'xToken is not equal');
            assert.strictEqual(SavedEvents[0].status, 1, 'channel status is not equal');
            /!* from SessionStorage method *!/
            assert.strictEqual(resGetSession[0], 2, 'session id is not equal');
            assert.strictEqual(resGetSession[1], '1a2b3c', 'xToken is not equal');
            assert.strictEqual(resGetSession[2], 1, 'session status is not equal');
        });
        it('closeSession', () => {
            /!* from SessionManager event *!/
            assert.isAbove(resCloseSession.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resCloseSession.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resCloseSession.receipt.gasUsed, 0, 'gasUsed is 0');
            /!* from SessionStorage event *!/
            assert.strictEqual(StatusUpdatedEvents[0].index, 1, 'index is not equal');
            assert.strictEqual(StatusUpdatedEvents[0].status, 3, 'status is not equal');
            /!* from SessionStorage method *!/
            assert.strictEqual(resGetStatus1, 3, 'session status is not equal');
        });
        it('confirmClose', () => {
            /!* from SessionManager event *!/
            assert.isAbove(resConfirmClose.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resConfirmClose.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resConfirmClose.receipt.gasUsed, 0, 'gasUsed is 0');
            /!* from SessionStorage event *!/
            assert.strictEqual(StatusUpdatedEvents[1].index, 1, 'index is not equal');
            assert.strictEqual(StatusUpdatedEvents[1].status, 4, 'status is not equal');
            /!* from SessionStorage method *!/
            assert.strictEqual(resGetStatus2, 4, 'session status is not equal');
        });
    });
});*/
