import {clientConstants, generalConstants, requestTypes} from '../Constants/index';

const initialState = {
    channels: [],
    account: '',
    contractsInstances: {},

    // request stuff
    requestIsActive: false,
    requestIsActiveMetaMask: false,
    requestType: ''
    // --
};

export function clientReducer(state = initialState, action) {
    switch (action.type) {
        case clientConstants.SET_ACCOUNT:
            return {
                ...state,
                account: action.payload.account
            };
        case clientConstants.SET_INSTANCES:
            return {
                ...state,
                contractsInstances: {
                    ...state.contractsInstances,
                    VaultLogicPlatformInstance: action.payload.data.VaultLogicPlatformInstance,
                    CapitalHeroInstance: action.payload.data.CapitalHeroInstance
                }
            };
        case clientConstants.SAVE_CHANNELS:
            return {
                ...state,
                channels: action.payload.channels
            };
        case clientConstants.OPEN_CASH_CHANNEL_REQUEST:
            return {
                ...state,
                requestIsActive: true,
                requestType: requestTypes.OPEN_CASH_CHANNEL
            };
        case clientConstants.OPEN_CASH_CHANNEL_SUCCESS:
            return {
                ...state,
                requestIsActive: false,
                requestIsActiveMetaMask: true
            };
        case clientConstants.OPEN_CASH_CHANNEL_ERROR:
            return {
                ...state,
                requestIsActive: false
            };
        case clientConstants.CLOSE_CASH_CHANNEL_REQUEST:
            return {
                ...state,
                requestIsActive: true,
                requestType: requestTypes.CLOSE_CASH_CHANNEL
            };
        case clientConstants.CLOSE_CASH_CHANNEL_SUCCESS:
            return {
                ...state,
                requestIsActive: false,
                requestIsActiveMetaMask: true
            };
        case clientConstants.CLOSE_CASH_CHANNEL_ERROR:
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