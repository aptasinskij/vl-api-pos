const assert = require('chai').assert;
const TokenStorage = artifacts.require('TokenStorage.sol');

/*\
* TokenStorage
\*/

contract('TokenStorage', () => {

    describe('tests for all methods', () => {

        let resSave;
        let resGet;

        before(async () => {
            const instance = await TokenStorage.deployed();
            /* save */
            resSave = await instance.set(1, 150);
            /* get */
            resGet = await instance.get(1);
            resGet = Number(resGet);
        });

        /*\
         # <hr>
         # <h4> set(customer, amount) </h4>
         # Set tokens on customer balance
         > Arguments
         - (address) customer - customer address
         - (uint256) amount - token amount
        \*/
        it('set', () => {
            /* from set method */
            assert.notEqual(resSave.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resSave.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from get method */
            assert.strictEqual(resGet, 150, 'token amount is not equal');
        });

        /*\
         # <hr>
         # <h4> get(consumer) </h4>
         # Get tokens balance of consumer
         > Arguments
         - (address) consumer - consumer address
        \*/
        it('get', () => {
            assert.strictEqual(resGet, 150, 'token amount is not equal');
        });
    })
});