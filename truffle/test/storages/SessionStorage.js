const assert = require('chai').assert;
const SessionStorage = artifacts.require('SessionStorage.sol');
const {convertToNumber} = require('../helpers/index');

/*\
* SessionStorage
\*/

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
            resSetStatus = await instance.setStatus(1, 1);
            resSetStatus = convertToNumber(resSetStatus.logs[0].args);
            /* getStatus */
            resGetStatus = await instance.getStatus(1);
            resGetStatus = Number(resGetStatus);
            /* getSession (after all) */
            resGetSessionAfterAll = await instance.getSession(1);
            resGetSessionAfterAll = convertToNumber(resGetSessionAfterAll);
        });

        /*\
         # <hr>
         # <h4> Save(sessionId, appId, xToken, status) </h4>
         # Save new session
         > Arguments
         - (uint256) sessionId - session id
         - (uint256) appId - application id
         - (string) xToken - session xToken
         - (uint256) status - session status
         > Emits
         - (event) Saved
        \*/
        it('save', () => {
            assert.strictEqual(resSave.sessionId, 1, 'session Id is not equal');
            assert.strictEqual(resSave.appId, 2, 'application Id is not equal');
            assert.strictEqual(resSave.xToken, '1a2b3c', 'xToken is not equal');
            assert.strictEqual(resSave.status, 3, 'status is not equal');
        });

        /*\
         # <hr>
         # <h4> getSession(index) </h4>
         # Get session info
         > Arguments
         - (uint256) index - session id
         > Returns
         - (uint256) appId - application id
         - (string) xToken - session xToken
         - (uint256) status - session status
        \*/
        it('getSession', () => {
            assert.strictEqual(resGetSession[0], 2, 'application Id is not equal');
            assert.strictEqual(resGetSession[1], '1a2b3c', 'xToken Id is not equal');
            assert.strictEqual(resGetSession[2], 3, 'status Id is not equal');
            /* after all */
            assert.strictEqual(resGetSessionAfterAll[0], 2, 'application Id is not equal (after all)');
            assert.strictEqual(resGetSessionAfterAll[1], '1a2b3c', 'xToken Id is not equal (after all)');
            assert.strictEqual(resGetSessionAfterAll[2], 1, 'status Id is not equal (after all)');
        });

        /*\
         # <hr>
         # <h4> getStatusAndXToken(index) </h4>
         # Get session status and xToken
         > Arguments
         - (uint256) index - session id
         > Returns
         - (string) xToken - session xToken
         - (uint256) status - session status
        \*/
        it('getStatusAndXToken', () => {
            assert.strictEqual(resGetStatusAndXToken[0], 3, 'status Id is not equal');
            assert.strictEqual(resGetStatusAndXToken[1], '1a2b3c', 'xToken is not equal');
        });

        /*\
         # <hr>
         # <h4> getAppId(index) </h4>
         # Get application ID of session
         > Arguments
         - (uint256) index - session index
         > Returns
         - (uint256) appId - application id
        \*/
        it('getAppId', () => {
            assert.strictEqual(resGetAppId, 2, 'application Id Id is not equal');
        });

        /*\
         # <hr>
         # <h4> getXToken(index) </h4>
         # Get session xToken
         > Arguments
         - (uint256) index - session id
         > Returns
         - (string) xToken - session xToken
        \*/
        it('getXToken', () => {
            assert.strictEqual(resGetXToken, '1a2b3c', 'xToken is not equal');
        });

        /*\
         # <hr>
         # <h4> getAppIdAndXToken(index) </h4>
         # Get application ID and xToken of session
         > Arguments
         - (uint256) index - session id
         > Returns
         - (uint256) appId - application id
         - (string) xToken - session xToken
        \*/
        it('getAppIdAndXToken', () => {
            assert.strictEqual(resGetAppIdAndXToken[0], 2, 'application Id is not equal');
            assert.strictEqual(resGetAppIdAndXToken[1], '1a2b3c', 'xToken is not equal');
        });

        /*\
         # <hr>
         # <h4> setStatus(index, status) </h4>
         # Set session status
         > Arguments
         - (uint256) index - session id
         - (uint256) status - session status
         > Emits
         - (event) StatusUpdated
        \*/
        it('setStatus', () => {
            assert.strictEqual(resSetStatus.index, 1, 'session Id is not equal');
            assert.strictEqual(resSetStatus.status, 1, 'status is not equal');
        });

        /*\
         # <hr>
         # <h4> getStatus(index) </h4>
         # Get session status
         > Arguments
         - (uint256) index - session id
         > Returns
         - (uint256) status - session status
        \*/
        it('getStatus', () => {
            assert.strictEqual(resGetStatus, 1, 'status is not equal');
        });

        /* events description */

        /*\
         # <hr>
         # <h4> Saved </h4>
         # Get info of saved session (emits on "save" method call)
         > Returns
         - (uint256) sessionId - session id
         - (uint256) appId - application id
         - (string) xToken - application xToken
         - (uint256) status - application status
        \*/

        /*\
         # <hr>
         # <h4> StatusUpdated </h4>
         # Get updated status of session (emits on "setStatus" method call)
         > Returns
         - (uint256) index - session id
         - (uint256) status - session status
        \*/
    })
});