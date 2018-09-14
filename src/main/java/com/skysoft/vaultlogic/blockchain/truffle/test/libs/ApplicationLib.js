/*
const assert = require('chai').assert;
const ApplicationLib = artifacts.require('ApplicationLib.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const Database = artifacts.require('Database.sol');
const convertToNumber = require('../helpers').convertToNumber;

contract('ApplicationLib', (accounts) => {

    describe('tests for all methods', () => {

        let resSave;
        let resGet;

        before(async () => {
            console.log('accounts',accounts);

            const appInstance = await CapitalHero.deployed();
            const instance = await ApplicationLib.deployed();
            const databaseInstance = await Database.deployed();
            /!* save *!/
            try {
                resSave = await instance.save(
                    databaseInstance.address,
                    123,
                    'capitalHero',
                    accounts[0],
                    'http://capital-hero',
                    appInstance.address,
                    0);

                console.log('resSave', resSave)
            } catch (e) {
                console.log(e);
            }


            /!* get *!/
            /!*resGet = await instance.get(1);
            resGet = convertToNumber(resGet);*!/


        });

        it('save', () => {
            /!*assert.strictEqual(resSave.appId, 1, 'application Id is not equal');
            assert.strictEqual(resSave.name, 'app name', 'application name is not equal');
            assert.strictEqual(resSave.owner, 235, 'application owner is not equal');
            assert.strictEqual(resSave.url, 'https://my-app-url', 'application url is not equal');
            assert.strictEqual(resSave.appAddr, 456, 'application address is not equal');
            assert.strictEqual(resSave.status, 4, 'application status is not equal');*!/
        });
        /!*it('get', () => {
            assert.strictEqual(resGet[0], 'app name', 'application name is not equal');
            assert.strictEqual(resGet[1], 235, 'application owner is not equal');
            assert.strictEqual(resGet[2], 'https://my-app-url', 'application url is not equal');
            assert.strictEqual(resGet[3], 456, 'application address is not equal');
            assert.strictEqual(resGet[4], 4, 'application status is not equal');
            /!* after all *!/
            assert.strictEqual(resGetAfterAll[0], 'app name', 'application name is not equal (after all)');
            assert.strictEqual(resGetAfterAll[1], 235, 'application owner is not equal (after all)');
            assert.strictEqual(resGetAfterAll[2], 'http://new-url', 'application url is not equal (after all)');
            assert.strictEqual(resGetAfterAll[3], 999, 'application address is not equal (after all)');
            assert.strictEqual(resGetAfterAll[4], 8, 'application status is not equal (after all)');
        });*!/

    })
});*/
