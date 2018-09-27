/*const assert = require('chai').assert;
const ApplicationLib = artifacts.require('ApplicationLib.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const ApplicationStorage = artifacts.require('ApplicationStorage.sol');
const Database = artifacts.require('Database.sol');
const {convertToNumber} = require('../helpers');*/

/*\
* ApplicationLib
\*/

/*contract('ApplicationLib', (accounts) => {

    describe('tests for "read" methods', () => {

        let resGet;
        let appInstance;
        let resGetOwner;
        let resGetUrl;
        let resGetAddress;
        let resGetStatus;
        let resGetName;
        let resIsRegistered1;
        let resIsRegistered2;

        before(async () => {
            appInstance = await CapitalHero.deployed();
            const applicationLibInstance = await ApplicationLib.deployed();
            const applicationStorageInstance = await ApplicationStorage.deployed();
            const databaseInstance = await Database.deployed();

            /!* isRegistered *!/
            resIsRegistered1 = await applicationLibInstance.isRegistered(databaseInstance.address, appInstance.address);
            console.log('resIsRegistered1', resIsRegistered1);

            /!* save by ApplicationStorage method *!/
            let res = await applicationStorageInstance.save(
                123,
                'capitalHero',
                accounts[0],
                'http://capital-hero',
                appInstance.address,
                0
            );
            console.log('res', convertToNumber(res.logs[0].args, true));
            /!* isRegistered *!/
            resIsRegistered2 = await applicationStorageInstance.isRegistered(appInstance.address);

            console.log('resIsRegistered2', resIsRegistered2);

            /!* get *!/
            resGet = convertToNumber(await applicationLibInstance.get(databaseInstance.address, 123), true);
            /!* getName *!/
            resGetName = await applicationLibInstance.getName(databaseInstance.address, 123);
            /!* getOwner *!/
            resGetOwner = await applicationLibInstance.getOwner(databaseInstance.address, 123);
            /!* setApplicationUrl by ApplicationStorage *!/
            await applicationStorageInstance.setApplicationUrl(123, 'http://updated-url');
            /!* getUrl *!/
            resGetUrl = await applicationLibInstance.getUrl(databaseInstance.address, 123);
            /!* setApplicationAddress by ApplicationStorage *!/
            await applicationStorageInstance.setApplicationAddress(123, 13245);
            /!* getAddress *!/
            resGetAddress = Number(await applicationLibInstance.getAddress(databaseInstance.address, 123));
            /!* setApplicationStatus by ApplicationStorage *!/
            await applicationStorageInstance.setApplicationStatus(123, 2);
            /!* getStatus *!/
            resGetStatus = Number(await applicationLibInstance.getStatus(databaseInstance.address, 123));
        });*/

        /*\
         # <hr>
         # <h4> get(self, appId) </h4>
         # Get all info of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         > Returns
         - (string) name - application name
         - (address) owner - application owner
         - (string) url - application url
         - (address) appAddr - application address
         - (uint256) status - application status
        \*/
        /*it('get', () => {
            assert.strictEqual(resGet[0], 'capitalHero', 'application name is not equal');
            assert.strictEqual(resGet[1], accounts[0], 'application owner is not equal');
            assert.strictEqual(resGet[2], 'http://capital-hero', 'application url is not equal');
            assert.strictEqual(resGet[3], appInstance.address, 'application address is not equal');
            assert.strictEqual(resGet[4], 0, 'application status is not equal');
        });*/

        /*\
         # <hr>
         # <h4> getOwner(self, appId) </h4>
         # Get owner of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         > Returns
         - (address) owner - application owner
        \*/
        /*it('getOwner', () => {
            assert.strictEqual(resGetOwner, accounts[0], 'application owner name is not equal');
        });*/

        /*\
         # <hr>
         # <h4> getUrl(self, appId) </h4>
         # Get owner of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         > Returns
         - (string) url - application url
        \*/
        /*it('getUrl', () => {
            assert.strictEqual(resGetUrl, 'http://updated-url', 'application url is not equal');
        });*/

        /*\
         # <hr>
         # <h4> getAddress(self, appId) </h4>
         # Get owner of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         > Returns
         - (address) appAddr - application address
        \*/
        /*it('getAddress', () => {
            assert.strictEqual(resGetAddress, 13245, 'application url is not equal');
        });*/

        /*\
         # <hr>
         # <h4> getStatus(self, appId) </h4>
         # Get owner of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         > Returns
         - (uint256) status - application status
        \*/
        /*it('getStatus', () => {
            assert.strictEqual(resGetStatus, 2, 'application status is not equal');
        });*/

        /*\
         # <hr>
         # <h4> getName(self, appId) </h4>
         # Get owner of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         > Returns
         - (string) name - application name
        \*/
        /*it('getName', () => {
            assert.strictEqual(resGetName, 'capitalHero', 'application name is not equal');
        });*/

        /* methods below are not tested, only description provided */

        /*\
         # <hr>
         # <h4> save(self, appId, name, owner, url, appAddr, status) </h4>
         # Save new application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         - (string) name - application name
         - (address) owner - application owner
         - (string) url - application url
         - (address) appAddr - application address
         - (uint256) status - application status
        \*/

        /*\
         # <hr>
         # <h4> setUrl(self, appId, url) </h4>
         # Set url of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         - (string) url - application url
        \*/

        /*\
         # <hr>
         # <h4> setAddress(self, appId, appAddr) </h4>
         # Set address of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         - (address) appAddr - application address
        \*/

        /*\
         # <hr>
         # <h4> setStatus(self, appId, status) </h4>
         # Set status of application
         > Arguments
         - (address) self - self database address
         - (uint256) appId - application id
         - (uint256) status - application status
        \*/
    /*})
});*/
