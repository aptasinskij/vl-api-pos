import {adminConstants} from '../Constants/index';
import {VaultLogicPlatformService} from '../Services/index';

export const adminActions = {
    increaseMoneyToTen,
    setAccountAddress,
    setContractsInstances,
    setChannelID,
    clearChannelID,
    changeMoney,
    insertMoney
};

function setAccountAddress(account) {
    return {
        type: adminConstants.SET_ACCOUNT,
        payload: {
            account
        }
    }
}

function setContractsInstances(data) {
    return {
        type: adminConstants.SET_INSTANCES,
        payload: {
            data
        }
    }
}

function setChannelID(channel) {
    return {
        type: adminConstants.SET_CHANNEL_ID,
        payload: {
            channel
        }
    }
}

function clearChannelID() {
    return {
        type: adminConstants.CLEAR_CHANNEL_ID,
    }
}

function changeMoney(money) {
    return {
        type: adminConstants.CHANGE_MONEY,
        payload: {
            money
        }
    }
}

function increaseMoneyToTen(){
    return {
        type: adminConstants.INCREASE_MONEY_TO_TEN
    }
}

function insertMoney(data) {
    return dispatch => {
        main(dispatch);
    };

    function main(dispatch) {
        dispatch(request());
        VaultLogicPlatformService
            .insertMoney(data)
            .then(() => {
                dispatch(success());
            })
            .catch(() => {
                dispatch(failure())
            });
    }

    function request() {
        return {
            type: adminConstants.INSERT_MONEY_REQUEST,
        }
    }
    function success() {
        return {
            type: adminConstants.INSERT_MONEY_SUCCESS,
        }
    }
    function failure() {
        return {
            type: adminConstants.INSERT_MONEY_ERROR,
        }
    }
}

