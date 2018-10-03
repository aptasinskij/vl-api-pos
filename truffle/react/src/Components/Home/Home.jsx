import React, {Component} from 'react';
import {history} from '../../Utils/index';

import {Segment, Button} from 'semantic-ui-react';
import './Home.css';

class Home extends Component {
    render() {
        return (
            <div className="home-container">
                <Segment.Group
                    compact>
                    <Segment
                        className="app-name-segment"
                        compact
                        padded='very'>
                        <span className="app-name">VaultLogic proof of concept Demo</span>
                    </Segment>
                    <Segment
                        textAlign='center'
                        compact
                        padded='very'>
                        <Button.Group
                            size='large'>
                            <Button
                                disabled
                                className="nav-button"
                                color='violet'
                                onClick={() => history.push('/admin')}
                                animated='vertical'>
                                <Button.Content visible content="Admin"/>
                                <Button.Content hidden content="VaultLogic"/>
                            </Button>
                            <Button.Or/>
                            <Button
                                disabled
                                className="nav-button"
                                color='red'
                                onClick={() => history.push('/client')}
                                animated='vertical'>
                                <Button.Content visible content="Client"/>
                                <Button.Content hidden content="CapitalHero"/>
                            </Button>
                        </Button.Group>
                    </Segment>
                    <Segment
                        textAlign='center'
                        compact
                        padded='very'>
                        <Button
                            size='large'
                            onClick={() => history.push('/registry-sequence')}
                            content='Registry sequence'
                            color='teal'/>
                    </Segment>
                </Segment.Group>
            </div>
        )
    }
}

export default Home;