const assert = require('chai').assert;
const SessionStorage = artifacts.require('./repositories/cash-in/SessionStorage.sol');
const convertToNumber = require('./helpers').convertToNumber;

contract('SessionStorage', () => {

    describe('tests for all methods', () => {
        let resSave;
        let resGetSession;
        let resGetStatusAndXToken;
        let resGetAppId;
        let resGetXToken;
        let resGetAppIdAndXToken;
        let resSetStatus;
        let resGetStatus;
        let resGetSessionAfterAll;

        before(async () => {
            const instance = await SessionStorage.deployed();
            /* save */
            resSave = await instance.save(1, 2, '1a2b3c', 3);
            resSave = convertToNumber(resSave.logs[0].args);
            /* getSession */
            resGetSession = await instance.getSession(1);
            resGetSession = convertToNumber(resGetSession);
            /* getStatusAndXToken */
            resGetStatusAndXToken = await instance.getStatusAndXToken(1);
            resGetStatusAndXToken = convertToNumber(resGetStatusAndXToken);
            /* getAppId */
            resGetAppId = await instance.getAppId(1);
            resGetAppId = Number(resGetAppId);
            /* getXToken */
            resGetXToken = await instance.getXToken(1);
            /* getAppIdAndXToken */
            resGetAppIdAndXToken = await instance.getAppIdAndXToken(1);
            resGetAppIdAndXToken = convertToNumber(resGetAppIdAndXToken);
            /* setStatus */
            resSetStatus = await instance.setStatus(1, 9);
            resSetStatus = convertToNumber(resSetStatus.logs[0].args);
            /* getStatus */
            resGetStatus = await instance.getStatus(1);
            resGetStatus = Number(resGetStatus);
            /* getSession (after all) */
            resGetSessionAfterAll = await instance.getSession(1);
            resGetSessionAfterAll = convertToNumber(resGetSessionAfterAll);
        });

        it('save', () => {
            assert.strictEqual(resSave.sessionId, 1, 'session Id is not equal');
            assert.strictEqual(resSave.appId, 2, 'application Id is not equal');
            assert.strictEqual(resSave.xToken, '1a2b3c', 'xToken is not equal');
            assert.strictEqual(resSave.status, 3, 'status is not equal');
        });
        it('getSession', () => {
            assert.strictEqual(resGetSession[0], 2, 'application Id is not equal');
            assert.strictEqual(resGetSession[1], '1a2b3c', 'xToken Id is not equal');
            assert.strictEqual(resGetSession[2], 3, 'status Id is not equal');
            /* after all */
            assert.strictEqual(resGetSessionAfterAll[0], 2, 'application Id is not equal (after all)');
            assert.strictEqual(resGetSessionAfterAll[1], '1a2b3c', 'xToken Id is not equal (after all)');
            assert.strictEqual(resGetSessionAfterAll[2], 9, 'status Id is not equal (after all)');
        });
        it('getStatusAndXToken', () => {
            assert.strictEqual(resGetStatusAndXToken[0], 3, 'status Id is not equal');
            assert.strictEqual(resGetStatusAndXToken[1], '1a2b3c', 'xToken is not equal');
        });
        it('getAppId', () => {
            assert.strictEqual(resGetAppId, 2, 'application Id Id is not equal');
        });
        it('getXToken', () => {
            assert.strictEqual(resGetXToken, '1a2b3c', 'xToken is not equal');
        });
        it('getAppIdAndXToken', () => {
            assert.strictEqual(resGetAppIdAndXToken[0], 2, 'application Id is not equal');
            assert.strictEqual(resGetAppIdAndXToken[1], '1a2b3c', 'xToken is not equal');
        });
        it('setStatus', () => {
            assert.strictEqual(resSetStatus.index, 1, 'session Id is not equal');
            assert.strictEqual(resSetStatus.status, 9, 'status is not equal');
        });
        it('getStatus', () => {
            assert.strictEqual(resGetStatus, 9, 'status is not equal');
        });
    })
});