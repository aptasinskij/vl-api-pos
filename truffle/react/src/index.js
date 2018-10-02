import React from 'react';
import ReactDOM from 'react-dom';
import {Provider} from 'react-redux';
import App from './Components/App/App';
import {store} from './Utils/index';

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);