/*
const assert = require('chai').assert;
const KioskLib = artifacts.require('KioskLib');
const KioskStorage = artifacts.require('KioskStorage');
const Database = artifacts.require('Database');

/!*\
* KioskLib
\*!/

contract('KioskLib', () => {

    describe('tests for "read" methods', () => {

        let resRetrieve;
        let resKioskExists1;
        let resKioskExists2;

        before(async () => {
            /!* get instances *!/
            const kioskStorageInstance = await KioskStorage.deployed();
            const kioskLibInstance = await KioskLib.deployed();
            const databaseInstance = await Database.deployed();

            /!* kioskExists *!/
            resKioskExists1 = await kioskLibInstance.kioskExists(databaseInstance.address, 1);
            /!* createKiosk by KioskStorage method *!/
            await kioskStorageInstance.createKiosk(1, 'Vinnytsia', 'Privat', 'timezone1');
            /!* kioskExists *!/
            resKioskExists2 = await kioskLibInstance.kioskExists(databaseInstance.address, 1);
        /!*    /!* retrieve *!/
            resRetrieve = await kioskLibInstance.retrieveKiosk(databaseInstance.address, 1);*!/
        });

        /!*\
         # <hr>
         # <h4> kioskExists(self, string memory kioskId) </h4>
         # Get kiosk existing state
         > Arguments
         - (address) self - self database address
         - (string memory) kioskId - kiosk short id
         > Returns
         - (bool) isExist - kiosk existing state
        \*!/
        it('kioskExists', () => {
            assert.strictEqual(resKioskExists1, false, 'kiosk exist before creation');
            assert.strictEqual(resKioskExists2, true, 'kiosk do not exist after creation');

        });

        /!*\
         # <hr>
         # <h4> retrieveKiosk(self, shortId) </h4>
         # Get all info of kiosk
         > Arguments
         - (address) self - self database address
         - (string) shortId - kiosk short id
         > Returns
         - (string) locationAddress - kiosk location address
         - (string) name - kiosk name
         - (string) timeZone - kiosk timeZone
        \*!/
        /!*it('retrieve', () => {
            assert.strictEqual(resRetrieve[0], 'Vinnytsia', 'kiosk location address is not equal');
            assert.strictEqual(resRetrieve[1], 'Privat', 'kiosk name is not equal');
            assert.strictEqual(resRetrieve[2], 'timezone1', 'kiosk name is not equal');
        });*!/

        /!* methods below are not tested, only description provided *!/

        /!*\
         # <hr>
         # <h4> createKiosk(self, shortId, locationAddress, name, timeZone) </h4>
         # Create new kiosk
         > Arguments
         - (address) self - self database address
         - (string) shortId - kiosk short id
         - (string) locationAddress - kiosk location address
         - (string) name - kiosk name
         - (string) timeZone - kiosk timeZone
        \*!/
    });
});*/
