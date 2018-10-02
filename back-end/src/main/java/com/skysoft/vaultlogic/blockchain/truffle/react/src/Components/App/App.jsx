import React, {Component} from 'react';
import {Router} from 'react-router';
import {Route, Redirect, Switch} from 'react-router-dom';
import {history} from '../../Utils/index';
import Admin from '../Admin/Admin';
import Client from '../Client/Client';
import RegistrySequence from '../RegistrySequence/RegistrySequence';
import Home from '../Home/Home';

import './App.css';

class App extends Component {

    render() {
        return (
            <div>
                <Router history={history}>
                    <Switch>
                        <Route exact path='/' render={() => <Home/>}/>
                        <Route path='/client' render={() => <Client/>}/>
                        <Route path='/admin' render={() => <Admin/>}/>
                        <Route path='/registry-sequence' render={() => <RegistrySequence/>}/>
                        <Route path='*'>
                            <Redirect to='/'/>
                        </Route>
                    </Switch>
                </Router>
            </div>
        )
    }
}

export default App;