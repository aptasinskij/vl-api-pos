const assert = require('chai').assert;
const TokenManager = artifacts.require('TokenManager.sol');
const {convertToNumber} = require('../helpers');

contract('TokenManager', () => {

    describe('tests for all methods', () => {
        let resTransfer;
        let resBalanceOf1;
        let resBalanceOf2;
        let resBalanceOf3;
        let resBalanceOf4;
        let resBalanceOf5;
        let resTransferFrom1;
        let resTransferFrom2;
        let resTransferFrom3;

        before(async () => {
            /* get instances */
            const tokenManagerInstance = await TokenManager.deployed();

            /* transfer */
            resTransfer = await tokenManagerInstance.transfer(1, 1000);
            resTransfer = convertToNumber(resTransfer.logs[0].args);
            /* balanceOf */
            resBalanceOf1 = await tokenManagerInstance.balanceOf(1);
            resBalanceOf1 = Number(resBalanceOf1);

            /* --- transferFrom --- */
            /* try to transferFrom more then have */
            try {
                await tokenManagerInstance.transferFrom(1,2, 1001);
                resTransferFrom1 = 'Method Allowed'
            } catch (e) {
                resTransferFrom1 = e.message;
            }
            /* transferFrom less then have */
            resTransferFrom2 = await tokenManagerInstance.transferFrom(1, 2, 400);
            resTransferFrom2 = convertToNumber(resTransferFrom2.logs[0].args);
            resBalanceOf2 = await tokenManagerInstance.balanceOf(1);
            resBalanceOf2 = Number(resBalanceOf2);
            resBalanceOf3 = await tokenManagerInstance.balanceOf(2);
            resBalanceOf3 = Number(resBalanceOf3);
            /* transferFrom all amount */
            resTransferFrom3 = await tokenManagerInstance.transferFrom(1, 2, 600);
            resTransferFrom3 = convertToNumber(resTransferFrom3.logs[0].args);
            resBalanceOf4 = await tokenManagerInstance.balanceOf(1);
            resBalanceOf4 = Number(resBalanceOf4);
            resBalanceOf5 = await tokenManagerInstance.balanceOf(2);
            resBalanceOf5 = Number(resBalanceOf5);
        });

        it('transfer', () => {
            assert.strictEqual(resTransfer.to, 1, 'application receiver address is not equal');
            assert.strictEqual(resTransfer.value, 1000, 'tokens amount is not equal');
        });
        it('balanceOf', () => {
            assert.strictEqual(resBalanceOf1, 1000, 'application balance is not equal');
        });
        it('transferFrom', () => {
            /* restrict to transferFrom more then have */
            assert.notEqual(resTransferFrom1, 'Method Allowed', 'allow to transferFrom more then have');
            /* --- transferFrom less then have --- */
            /* from logs */
            assert.strictEqual(resTransferFrom2.from, 1, 'application sender address is not equal');
            assert.strictEqual(resTransferFrom2.to, 2, 'application receiver address is not equal');
            assert.strictEqual(resTransferFrom2.value, 400, 'tokens amount is not equal');
            /* balanceOf */
            assert.strictEqual(resBalanceOf2, 600, 'application sender balance is not equal');
            assert.strictEqual(resBalanceOf3, 400, 'application receiver balance is not equal');
            /* --- transferFrom all amount --- */
            /* from logs */
            assert.strictEqual(resTransferFrom3.from, 1, 'application sender address is not equal');
            assert.strictEqual(resTransferFrom3.to, 2, 'application receiver address is not equal');
            assert.strictEqual(resTransferFrom3.value, 600, 'tokens amount is not equal');
            /* balanceOf */
            assert.strictEqual(resBalanceOf4, 0, 'application sender balance is not equal');
            assert.strictEqual(resBalanceOf5, 1000, 'application receiver balance is not equal');
        });
    });
});