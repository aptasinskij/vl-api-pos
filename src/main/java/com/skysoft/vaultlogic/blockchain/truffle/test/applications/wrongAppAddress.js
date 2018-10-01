const assert = require('chai').assert;
const ApplicationManager = artifacts.require('ApplicationManager');
const ParameterStorage = artifacts.require('ParameterStorage');
const SessionManager = artifacts.require('SessionManager');
const CapitalHero = artifacts.require('CapitalHero');

contract('CapitalHero', accounts => {

    describe('wrong application address', () => {
        const ownerAddress = accounts[1];
        let resOpen;
        let resCreateSession1;

        before(async () => {
            /* get instances */
            const applicationManagerInstance = await ApplicationManager.deployed();
            const sessionManagerInstance = await SessionManager.deployed();
            const capitalHeroInstance = await CapitalHero.deployed();
            const parameterStorageInstance = await ParameterStorage.deployed();

            /* setVLFee of ParameterStorage */
            await parameterStorageInstance.setVLFee(1000);

            /* registerApplication */
            await applicationManagerInstance.registerApplication(
                123,
                'CapitalHero',
                ownerAddress,
                'http://capital-hero',
                456
            );
            /* enableApplication */
            await applicationManagerInstance.enableApplication(123);
            /* try to createSession */
            try {
                await sessionManagerInstance.createSession(321, 123, 'SessionXToken');
                resCreateSession1 = 'Method Allowed';
            } catch (e) {
                resCreateSession1 = e.message;
                /*console.log('createSession error', e);*/
            }
            /* activate */
            await sessionManagerInstance.activate(321);
            /* try to openCashInChannel */
            try {
                await capitalHeroInstance.openCashInChannel(321);
                resOpen = 'Method Allowed';
            } catch (e) {
                resOpen = e.message;
            }
        });

        it('restrict to openCashInChannel', () => {
            assert.notEqual(resCreateSession1, 'Method Allowed', 'allow to call createSession');
            assert.notEqual(resOpen, 'Method Allowed', 'allow to call openCashInChannel');
        });
    });
});
