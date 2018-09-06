export const CapitalHeroService = {
    openCashChannel,
    closeCashChannel
};

async function openCashChannel(data) {
    const {CapitalHeroInstance, account} = data;

    try {
        await CapitalHeroInstance.opendCashInChannel({from: account});
        console.log('Channel opened, waiting for event...');
    } catch (error) {
        console.error(error);
    }
}

async function closeCashChannel(data) {
    const {CapitalHeroInstance, account, channelID} = data;

    try {
        await CapitalHeroInstance.closeCashInChannel(channelID, {gas: 200000, from: account});
        console.log('Channel closed, waiting for event...');
    } catch (error) {
        console.error(error);
    }
}

