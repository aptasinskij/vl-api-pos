/*
const assert = require('chai').assert;
const ParameterStorage = artifacts.require('ParameterStorage.sol');

/!*\
* ParameterStorage
\*!/

contract('ParameterStorage', () => {

    describe('tests for all methods', () => {

        let resSetVLFee1;
        let resSetVLFee2;
        let resGetVLFee1;
        let resGetVLFee2;

        before(async () => {
            const instance = await ParameterStorage.deployed();
            /!* setVLFee *!/
            resSetVLFee1 = await instance.setVLFee(50);
            /!* getVLFee *!/
            resGetVLFee1 = await instance.getVLFee();
            resGetVLFee1 = Number(resGetVLFee1);
            /!* setVLFee ones again *!/
            resSetVLFee2 = await instance.setVLFee(10);
            /!* getVLFee ones again *!/
            resGetVLFee2 = await instance.getVLFee();
            resGetVLFee2 = Number(resGetVLFee2);
        });

        /!*\
         # <hr>
         # <h4> setVLFee(percent) </h4>
         # Set value of VaultLogic fee
         > Arguments
         - (uint256) percent - value of VaultLogic fee
        \*!/
        it('setVLFee', () => {
            assert.notEqual(resSetVLFee1.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resSetVLFee1.receipt.gasUsed, 0, 'gasUsed is 0');
            assert.notEqual(resSetVLFee2.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resSetVLFee2.receipt.gasUsed, 0, 'gasUsed is 0');
        });

        /!*\
         # <hr>
         # <h4> getVLFee() </h4>
         # Get value of VaultLogic fee
         > Returns
         - (uint256) percent - value of VaultLogic fee
        \*!/
        it('getVLFee', () => {
            assert.strictEqual(resGetVLFee1, 50, 'percent value is not equal');
            assert.strictEqual(resGetVLFee2, 10, 'percent value is not equal after redefine');
        });
    })
});
*/
