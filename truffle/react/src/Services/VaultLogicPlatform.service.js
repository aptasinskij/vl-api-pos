export const VaultLogicPlatformService = {
    insertMoney
};

async function insertMoney(data) {
    const {VaultLogicPlatformInstance, account, channelID, moneyValue} = data;

    try {
        await VaultLogicPlatformInstance.addMoneyToChannel(channelID, moneyValue, {from: account});
        console.log('money inserted, waiting for event...');
    } catch (error) {
        console.error(error);
    }
}