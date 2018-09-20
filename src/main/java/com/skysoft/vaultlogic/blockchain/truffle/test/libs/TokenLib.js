/*const assert = require('chai').assert;
const TokenLib = artifacts.require('TokenLib.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const TokenStorage = artifacts.require('TokenStorage.sol');
const Database = artifacts.require('Database.sol');*/

/*\
* TokenLib
\*/

/*contract('TokenLib', () => {

    describe('tests for "read" methods', () => {

        let resGet;
        let appInstance;

        before(async () => {
            appInstance = await CapitalHero.deployed();
            const tokenLibInstance = await TokenLib.deployed();
            const tokenStorageInstance = await TokenStorage.deployed();
            const databaseInstance = await Database.deployed();

            /!* save by TokenStorage method *!/
            await tokenStorageInstance.set(1, 200);
            /!* get *!/
            resGet = await tokenLibInstance.get(databaseInstance.address, 1);
            resGet = Number(resGet);
        });*/

        /*\
         # <hr>
         # <h4> get(self, customer) </h4>
         # Get tokens balance of consumer
         > Arguments
         - (address) self - self database address
         - (address) customer - customer address
         > Returns
         - (uint256) amount - tokens amount
        \*/
        /*it('get', () => {
            assert.strictEqual(resGet, 200, 'token balance is not equal');
        });*/

        /* methods below are not tested, only description provided */

        /*\
         # <hr>
         # <h4> set(self, customer, amount) </h4>
         # Set tokens on customer balance
         > Arguments
         - (address) self - self database address
         - (address) customer - customer address
         - (uint256) amount - tokens amount
        \*/
    /*})
});*/