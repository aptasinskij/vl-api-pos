const assert = require('chai').assert;
const ParameterLib = artifacts.require('ParameterLib.sol');
const ParameterStorage = artifacts.require('ParameterStorage.sol');
const Database = artifacts.require('Database.sol');

/*\
* ParameterLib
\*/

contract('ParameterLib', () => {

    describe('tests for "read" methods', () => {

        let resGetVLFee1;
        let resGetVLFee2;

        before(async () => {
            /* get instances */
            const parameterLibInstance = await ParameterLib.deployed();
            const parameterStorageInstance = await ParameterStorage.deployed();
            const databaseInstance = await Database.deployed();

            /* setVLFee by ParameterStorage method */
            await parameterStorageInstance.setVLFee(15);
            /* get */
            resGetVLFee1 = await parameterLibInstance.getVLFee(databaseInstance.address);
            resGetVLFee1 = Number(resGetVLFee1);

            /* setVLFee by ParameterStorage method ones again */
            await parameterStorageInstance.setVLFee(10);
            resGetVLFee2 = await parameterLibInstance.getVLFee(databaseInstance.address);
            resGetVLFee2 = Number(resGetVLFee2);
        });

        /*\
         # <hr>
         # <h4> getVLFee(self) </h4>
         # Get value of VaultLogic fee
         > Arguments
         - (address) self - self database address
         > Returns
         - (uint256) percent - value of VaultLogic fee
        \*/
        it('getVLFee', () => {
            assert.strictEqual(resGetVLFee1, 15, 'percent value is not equal');
            assert.strictEqual(resGetVLFee2, 10, 'percent value is not equal after redefine');
        });

        /* methods below are not tested, only description provided */

        /*\
         # <hr>
         # <h4> setVLFee(self, percent) </h4>
         # Set value of VaultLogic fee
         > Arguments
         - (address) self - self database address
         - (uint256) percent - value of VaultLogic fee
        \*/
    });
});