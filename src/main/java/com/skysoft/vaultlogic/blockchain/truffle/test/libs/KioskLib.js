/*const assert = require('chai').assert;
const KioskLib = artifacts.require('KioskLib');
const KioskStorage = artifacts.require('KioskStorage');
const Database = artifacts.require('Database');*/

/*\
* KioskLib
\*/

/*contract('KioskLib', () => {

    describe('tests for "read" methods', () => {

        let resRetrieve;

        before(async () => {
            /!* get instances *!/
            const kioskStorageInstance = await KioskStorage.deployed();
            const kioskLibInstance = await KioskLib.deployed();
            const databaseInstance = await Database.deployed();

            /!* saveKiosk by KioskStorage method *!/
            await kioskStorageInstance.saveKiosk(1, 'Vinnytsia', 'Privat', 'timezone1');
            /!* retrieve *!/
            resRetrieve = await kioskLibInstance.retrieve(databaseInstance.address, 1);
        });*/

        /*\
         # <hr>
         # <h4> retrieve(self, shortId) </h4>
         # Get all info of kiosk
         > Arguments
         - (address) self - self database address
         - (string) shortId - kiosk short id
         > Returns
         - (string) locationAddress - kiosk location address
         - (string) name - kiosk name
         - (string) timeZone - kiosk timeZone
        \*/
        /*it('retrieve', () => {
            assert.strictEqual(resRetrieve[0], 'Vinnytsia', 'kiosk location address is not equal');
            assert.strictEqual(resRetrieve[1], 'Privat', 'kiosk name is not equal');
            assert.strictEqual(resRetrieve[2], 'timezone1', 'kiosk name is not equal');
        });*/

        /* methods below are not tested, only description provided */

        /*\
         # <hr>
         # <h4> save(self, shortId, locationAddress, name, timeZone) </h4>
         # Save new kiosk
         > Arguments
         - (address) self - self database address
         - (string) shortId - kiosk short id
         - (string) locationAddress - kiosk location address
         - (string) name - kiosk name
         - (string) timeZone - kiosk timeZone
        \*/
    /*});
});*/