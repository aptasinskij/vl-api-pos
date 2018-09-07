const assert = require('chai').assert;
const SessionStorage = artifacts.require('./repositories/cash-in/SessionStorage.sol');
const SessionManager = artifacts.require('./services/SessionManager.sol');
const ApplicationManager = artifacts.require('./services/ApplicationManager.sol');
const CapitalHero = artifacts.require('./application/CapitalHero.sol');
const convertToNumber = require('./helpers').convertToNumber;

contract('SessionManager', () => {

    describe('tests for all methods', () => {
        let resCreateSession;
        let resGetSession;
        let resCloseSession;
        let resGetStatus1;
        let resConfirmClose;
        let resGetStatus2;

        before(async () => {
            /* get instances */
            const sessionStorageInstance = await SessionStorage.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            const capitalHeroInstance = await CapitalHero.deployed();

            /* registerApplication Capital Hero */
            applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /* createSession */
            resCreateSession = await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            resGetSession = await sessionStorageInstance.getSession(1);
            resGetSession = convertToNumber(resGetSession);
            /* closeSession */
            resCloseSession = await sessionManagerInstance.closeSession(1);
            resGetStatus1 = await sessionStorageInstance.getStatus(1);
            resGetStatus1 = Number(resGetStatus1);
            /* confirmClose */
            resConfirmClose = await sessionManagerInstance.confirmClose(1);
            resGetStatus2 = await sessionStorageInstance.getStatus(1);
            resGetStatus2 = Number(resGetStatus2);
        });

        it('createSession', () => {
            /* from SessionManager event */
            assert.isAbove(resCreateSession.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resCreateSession.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resCreateSession.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from SessionStorage method */
            assert.strictEqual(resGetSession[0], 2, 'session id is not equal');
            assert.strictEqual(resGetSession[1], '1a2b3c', 'xToken is not equal');
            assert.strictEqual(resGetSession[2], 0, 'session status is not equal');
        });
        it('closeSession', () => {
            /* from SessionManager event */
            assert.isAbove(resCloseSession.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resCloseSession.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resCloseSession.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from SessionStorage method */
            assert.strictEqual(resGetStatus1, 1, 'session status is not equal');
        });
        it('confirmClose', () => {
            /* from SessionManager event */
            assert.isAbove(resConfirmClose.receipt.logs.length, 0, 'transaction logs are empty');
            assert.notEqual(resConfirmClose.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resConfirmClose.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from SessionStorage method */
            assert.strictEqual(resGetStatus2, 2, 'session status is not equal');
        });
    });
});