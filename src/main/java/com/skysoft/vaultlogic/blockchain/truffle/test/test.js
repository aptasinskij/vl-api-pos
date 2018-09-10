/*
var chai = require('chai');
var sinon = require('sinon');
chai.use(require('sinon-chai'));
var spies = require('chai-spies');
chai.use(spies);
const CashChannelsManager = artifacts.require('./services/CashChannelsManager.sol');
const CashInStorage = artifacts.require('./repositories/cash-in/CashInStorage.sol');
const CapitalHero = artifacts.require('./application/CapitalHero.sol');
const ApplicationManager = artifacts.require('./services/ApplicationManager.sol');
const SessionManager = artifacts.require('./services/SessionManager.sol');
require('truffle-test-utils').init();
const convertToNumber = require('./helpers').convertToNumber;


var lol = {
    send: () => {
        lol.method();
    },
    method: () => {
        console.log('method called');
    }
};

let cashChannelsManagerInstance;
let cashInStorageInstance;

contract('CashChannelsManager', () => {

    describe('tests andrii', () => {

        before(async () => {

            cashInStorageInstance = await CashInStorage.deployed();
            const capitalHeroInstance = await CapitalHero.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            /!* registerApplication Capital Hero *!/
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /!* createSession for Capital Hero *!/
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /!* openCashInChannel *!/
            await cashChannelsManagerInstance.openCashInChannel(1);
            /!* confirmOpen  *!/
            await cashChannelsManagerInstance.confirmOpen(0);
            await cashChannelsManagerInstance.updateCashInBalance(0, 500);

            cashInStorageInstance.CashInBalanceUpdated ().watch ( (err, response) => {
                console.log(1111);
                console.log('response', convertToNumber(response.args));
            })



            /!* await cashChannelsManagerInstance.updateCashInBalance(0, 100);

             resGetBalance = await cashInStorageInstance.getBalance(0);
             resGetBalance = Number(resGetBalance);*!/
        });

        it('real', () => {

            /!*var kek = sinon.spy(lol, 'method');
            lol.send();*!/
            /!*const spy = sinon.spy(cashInStorageInstance, 'getBalance');
            cashChannelsManagerInstance.updateCashInBalance(0, 100);

            sinon.assert.called(spy);*!/
            assert.strictEqual(1, 1);

        });
        /!*it('model', () => {
            const spy = sinon.spy(lol, 'method');
            lol.send();
            sinon.assert.called(spy);
        })*!/
    });
});*/
