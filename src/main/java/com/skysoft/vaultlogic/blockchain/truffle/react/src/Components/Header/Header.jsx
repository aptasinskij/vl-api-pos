import React from 'react';
import {history} from '../../Utils';

import './Header.css';
import {Header as SemanticHeader, Button, Icon, Container} from 'semantic-ui-react';

export const Header = () => {

    let headerContent;
    switch (window.location.pathname) {
        case '/client':
            headerContent = 'CapitalHero';
            break;
        case '/admin':
            headerContent = 'VaultLogic';
            break;
        case '/registry-sequence':
            headerContent = 'Registry Sequence';
            break;
        default:
            headerContent = 'unknownLocation';
    }

    return (
        <SemanticHeader
            as='h1'
            className="header-component">
            <Container className="header-content">
                <span className="role-name">{headerContent}</span>
                <Button
                    compact
                    color='teal'
                    animated='vertical'
                    onClick={history.goBack}>
                    <Button.Content visible>Go Back</Button.Content>
                    <Button.Content hidden>
                        <Icon name='arrow left'/>
                    </Button.Content>
                </Button>
            </Container>
        </SemanticHeader>
    )
};