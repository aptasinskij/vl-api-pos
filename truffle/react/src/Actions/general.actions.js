import {generalConstants} from '../Constants/index';

export const generalActions = {
    clearRequestType
};

function clearRequestType() {
    return {
        type: generalConstants.CLEAR_REQUEST_TYPE
    }
}