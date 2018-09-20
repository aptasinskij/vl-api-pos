/*
const assert = require('chai').assert;
const SessionLib = artifacts.require('SessionLib.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const SessionStorage = artifacts.require('SessionStorage.sol');
const Database = artifacts.require('Database.sol');
const {convertToNumber} = require('../helpers');

/!*\
* SessionLib
\*!/

contract('SessionLib', () => {

    describe('tests for "read" methods', () => {

        let resGet;
        let appInstance;
        let resGetAppId;
        let resGetToken;
        let resGetStatus;

        before(async () => {
            appInstance = await CapitalHero.deployed();
            const sessionLibInstance = await SessionLib.deployed();
            const sessionStorageInstance = await SessionStorage.deployed();
            const databaseInstance = await Database.deployed();

            /!* save by SessionStorage method *!/
            await sessionStorageInstance.save(0, 1, '1a2b3c', 3);
            /!* get *!/
            resGet = await sessionLibInstance.get(databaseInstance.address, 0);
            resGet = convertToNumber(resGet, true);
            /!* getAppId *!/
            resGetAppId = await sessionLibInstance.getAppId(databaseInstance.address, 0);
            resGetAppId = Number(resGetAppId);
            /!* getXToken *!/
            resGetToken = await sessionLibInstance.getXToken(databaseInstance.address, 0);
            /!* setStatus by SessionStorage method *!/
            await sessionStorageInstance.setStatus(0, 2);
            /!* getStatus *!/
            resGetStatus = await sessionLibInstance.getStatus(databaseInstance.address, 0);
            resGetStatus = Number(resGetStatus);
        });

        /!*\
         # <hr>
         # <h4> get(self, index) </h4>
         # Get all info of session
         > Arguments
         - (address) self - self database address
         - (uint256) index - session id
         > Returns
         - (uint256) appId - application id
         - (string) xToken - session xToken
         - (uint256) status - session status
        \*!/
        it('get', () => {
            assert.strictEqual(resGet[0], 1, 'application id is not equal');
            assert.strictEqual(resGet[1], '1a2b3c', 'session xToken is not equal');
            assert.strictEqual(resGet[2], 3, 'session status is not equal');
        });

        /!*\
         # <hr>
         # <h4> getAppId(self, index) </h4>
         # Get application id of session
         > Arguments
         - (address) self - self database address
         - (uint256) index - session id
         > Returns
         - (uint256) appId - application id
        \*!/
        it('getAppId', () => {
            assert.strictEqual(resGetAppId, 1, 'application id is not equal');
        });

        /!*\
         # <hr>
         # <h4> getXToken(self, index) </h4>
         # Get xToken of session
         > Arguments
         - (address) self - self database address
         - (uint256) index - session id
         > Returns
         - (string) xToken - session xToken
        \*!/
        it('getToken', () => {
            assert.strictEqual(resGetToken, '1a2b3c', 'session xToken is not equal');
        });

        /!*\
         # <hr>
         # <h4> getStatus(self, index) </h4>
         # Get status of session
         > Arguments
         - (address) self - self database address
         - (uint256) index - session id
         > Returns
         - (uint256) status - session status
        \*!/
        it('getStatus', () => {
            assert.strictEqual(resGetStatus, 2, 'cashInChannel status is not equal');
        });

        /!* methods below are not tested, only description provided *!/

        /!*\
         # <hr>
         # <h4> save(self, sessionId, appId, xToken, status) </h4>
         # Save new session
         > Arguments
         - (address) self - self database address
         - (uint256) sessionId - session id
         - (uint256) appId - application id
         - (string) xToken - session xToken
         - (uint256) status - session status
        \*!/

        /!*\
         # <hr>
         # <h4> setStatus(self, index, status) </h4>
         # Set status of session
         > Arguments
         - (address) self - self database address
         - (uint256) index - session id
         - (string) status - session status
        \*!/
    })
});*/
