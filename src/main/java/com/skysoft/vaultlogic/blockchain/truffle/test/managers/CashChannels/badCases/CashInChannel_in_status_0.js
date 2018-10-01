/*
const assert = require('chai').assert;
const CashChannelsManager = artifacts.require('CashChannelsManager.sol');
const SessionManager = artifacts.require('SessionManager.sol');
const ApplicationManager = artifacts.require('ApplicationManager.sol');
const CapitalHero = artifacts.require('CapitalHero.sol');
const CashInStorage = artifacts.require('CashInStorage.sol');

contract('CashChannelsManager', () => {

    describe('CashInChannel in "request-open" status (0)', () => {
        let capitalHeroInstance;
        let resConfirmOpen;
        let resGetStatus;
        let resCloseCashInChannel;
        let resConfirmClose;
        let resOpenCashInChannel;

        before(async () => {
            /!* get instances *!/
            const cashInStorageInstance = await CashInStorage.deployed();
            const cashChannelsManagerInstance = await CashChannelsManager.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const applicationManagerInstance = await ApplicationManager.deployed();
            capitalHeroInstance = await CapitalHero.deployed();

            /!* registerApplication Capital Hero *!/
            await applicationManagerInstance.registerApplication(2, 'capital-hero', 235, 'http://capital-hero', capitalHeroInstance.address);
            /!* createSession for Capital Hero *!/
            await sessionManagerInstance.createSession(1, 2, '1a2b3c');
            /!* activate session *!/
            await sessionManagerInstance.activate(1);
            /!* openCashInChannel *!/
            await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
            resGetStatus = await cashInStorageInstance.getStatus(0);
            resGetStatus = Number(resGetStatus);
            /!* try to closeCashInChannel *!/
            try {
                await cashChannelsManagerInstance.closeCashInChannel(capitalHeroInstance.address, 1, 0, [0, 1], [0, 1]);
                resCloseCashInChannel = 'Method Allowed';
            } catch (e) {
                resCloseCashInChannel = e.message;
            }
            /!* try to confirmClose *!/
            try {
                await cashChannelsManagerInstance.confirmClose(0);
                resConfirmClose = 'Method Allowed';
            } catch (e) {
                resConfirmOpen = e.message;
            }
            /!* try to confirmClose *!/
            try {
                await cashChannelsManagerInstance.openCashInChannel(capitalHeroInstance.address, 1);
                resOpenCashInChannel = 'Method Allowed';
            } catch (e) {
                resOpenCashInChannel = e.message;
            }
        });


        it('restrict to closeCashInChannel', () => {
            assert.strictEqual(resGetStatus, 0, 'channel status is not equal');
            /!* restrict to confirmOpen channel *!/
            assert.notEqual(resCloseCashInChannel, 'Method Allowed', 'allowed to call closeCashInChannel method');
        });
        it('restrict to confirmClose', () => {
            /!* restrict to confirmOpen channel *!/
            assert.notEqual(resConfirmClose, 'Method Allowed', 'allowed to call confirmClose method');
        });
        it('restrict to openCashInChannel ones again', () => {
            /!* restrict to confirmOpen channel *!/
            assert.notEqual(resOpenCashInChannel, 'Method Allowed', 'allowed to call openCashInChannel method');
        });
});*/
