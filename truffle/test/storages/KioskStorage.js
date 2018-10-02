const assert = require('chai').assert;
const KioskStorage = artifacts.require('KioskStorage');

/*\
* KioskStorage
\*/

contract('KioskStorage', () => {

    describe('tests for all methods', () => {

        let resSaveKiosk;
        let resRetrieveKiosk;

        before(async () => {
            const instance = await KioskStorage.deployed();
            /* createKiosk */
            resSaveKiosk = await instance.createKiosk(2, 'Kyiv', 'KyivStar', 'timezone0');
            /* get */
            resRetrieveKiosk = await instance.retrieveKiosk(1);
        });

        /*\
         # <hr>
         # <h4> createKiosk(shortId, locationAddress, name, timeZone) </h4>
         # Create new Kiosk
         > Arguments
         - (string) shortId - kiosk short id
         - (string) locationAddress - kiosk location address
         - (string) name - kiosk name
         - (string) timeZone - kiosk timeZone
        \*/
        it('createKiosk', () => {
            /* from createKiosk method */
            assert.notEqual(resSaveKiosk.receipt.transactionHash, '', 'transaction hash is empty');
            assert.isAbove(resSaveKiosk.receipt.gasUsed, 0, 'gasUsed is 0');
            /* from retrieveKiosk method */
            assert.strictEqual(resRetrieveKiosk[0], 'Kyiv', 'kiosk location address is not equal');
            assert.strictEqual(resRetrieveKiosk[1], 'KyivStar', 'kiosk name is not equal');
            assert.strictEqual(resRetrieveKiosk[2], 'timezone0', 'kiosk name is not equal');
        });

        /*\
         # <hr>
         # <h4> retrieveKiosk(shortId) </h4>
         # Get all info of kiosk
         > Arguments
         - (string) shortId - kiosk short id
         > Returns
         - (string) locationAddress - kiosk location address
         - (string) name - kiosk name
         - (string) timeZone - kiosk timeZone
        \*/
        it('retrieveKiosk', () => {
            assert.strictEqual(resRetrieveKiosk[0], 'Kyiv', 'kiosk location address is not equal');
            assert.strictEqual(resRetrieveKiosk[1], 'KyivStar', 'kiosk name is not equal');
            assert.strictEqual(resRetrieveKiosk[2], 'timezone0', 'kiosk name is not equal');
        });
    })
});
