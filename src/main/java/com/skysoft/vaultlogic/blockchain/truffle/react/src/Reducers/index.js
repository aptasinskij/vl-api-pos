import {combineReducers} from 'redux';
import {adminReducer} from './admin.reducer';
import {clientReducer} from './client.reducer';

export const rootReducer = combineReducers({
    adminReducer,
    clientReducer
});