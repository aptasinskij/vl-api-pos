import {CapitalHeroService} from '../Services';
import {clientConstants} from '../Constants';

export const clientActions = {
    openCashChannel,
    closeCashChannel,
    setAccountAddress,
    setContractsInstances,
    saveChannels
};

function openCashChannel(data) {
    return dispatch => {
        main(dispatch);
    };
    function main(dispatch) {
        dispatch(request());
        CapitalHeroService
            .openCashChannel(data)
            .then(() => {
                dispatch(success());
            })
            .catch(() => {
                dispatch(failure())
            });
    }

    function request() {
        return {
            type: clientConstants.OPEN_CASH_CHANNEL_REQUEST,
        }
    }
    function success() {
        return {
            type: clientConstants.OPEN_CASH_CHANNEL_SUCCESS,
        }
    }
    function failure() {
        return {
            type: clientConstants.OPEN_CASH_CHANNEL_ERROR,
        }
    }
}

function closeCashChannel(data) {
    return dispatch => {
        main(dispatch);
    };
    function main(dispatch) {
        dispatch(request());
        CapitalHeroService
            .closeCashChannel(data)
            .then(() => {
                dispatch(success());
            })
            .catch(() => {
                dispatch(failure())
            });
    }

    function request() {
        return {
            type: clientConstants.CLOSE_CASH_CHANNEL_REQUEST,
        }
    }
    function success() {
        return {
            type: clientConstants.CLOSE_CASH_CHANNEL_SUCCESS,
        }
    }
    function failure() {
        return {
            type: clientConstants.CLOSE_CASH_CHANNEL_ERROR,
        }
    }
}

function setAccountAddress(account) {
    return {
        type: clientConstants.SET_ACCOUNT,
        payload: {
            account
        }
    }
}

function setContractsInstances(data) {
    return {
        type: clientConstants.SET_INSTANCES,
        payload: {
            data
        }
    }
}

function saveChannels(channels) {
    return {
        type: clientConstants.SAVE_CHANNELS,
        payload: {
            channels
        }
    }
}