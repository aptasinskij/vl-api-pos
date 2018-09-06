import {adminConstants, generalConstants, requestTypes} from '../Constants';

const initialState = {
    channels: [],
    account: '',
    contractsInstances: {},
    moneyValue: 0,
    disabledCashChannel: true,
    channelID: '',

    // request stuff
    requestIsActive: false,
    requestIsActiveMetaMask: false,
    requestType: ''
    // --
};

export function adminReducer(state = initialState, action) {
    switch (action.type) {
        case adminConstants.SET_ACCOUNT:
            return {
                ...state,
                account: action.payload.account
            };
        case adminConstants.SET_INSTANCES:
            return {
                ...state,
                contractsInstances: {
                    ...state.contractsInstances,
                    VaultLogicPlatformInstance: action.payload.data.VaultLogicPlatformInstance,
                    CapitalHeroInstance: action.payload.data.CapitalHeroInstance
                }
            };
        case adminConstants.SET_CHANNEL_ID:
            return {
                ...state,
                disabledCashChannel: false,
                channelID: action.payload.channel
            };
        case adminConstants.CLEAR_CHANNEL_ID:
            return {
                ...state,
                disabledCashChannel: true,
                channelID: ''
            };
        case adminConstants.CHANGE_MONEY:
            return {
                ...state,
                moneyValue: action.payload.money
            };
        case adminConstants.INCREASE_MONEY_TO_TEN:
            return {
                ...state,
                moneyValue: state.moneyValue + 10
            };
        case adminConstants.INSERT_MONEY_REQUEST:
            return {
                ...state,
                requestIsActive: true,
                requestType: requestTypes.INSERT_MONEY
            };
        case adminConstants.INSERT_MONEY_SUCCESS:
            return {
                ...state,
                requestIsActive: false,
                requestIsActiveMetaMask: true
            };
        case adminConstants.INSERT_MONEY_ERROR:
            return {
                ...state,
                requestIsActive: false
            };
        case generalConstants.CLEAR_REQUEST_TYPE:
            return {
                ...state,
                requestIsActiveMetaMask: false,
                requestType: ''
            };
        default:
            return state
    }
}