import {generalConstants} from '../Constants';

export const generalActions = {
    clearRequestType
};

function clearRequestType() {
    return {
        type: generalConstants.CLEAR_REQUEST_TYPE
    }
}