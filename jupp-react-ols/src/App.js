import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

var Client = require('node-rest-client').Client;

var client = new Client();

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {terms: []};
    }

    componentDidMount() {

        fetch("http://www.ebi.ac.uk/ols/api/ontologies/efo/terms")
            .then( response => response.json() )
            .then( json => { this.setState({terms: json._embedded.terms} );
            });

    }

    render() {
        return (
            <TermList terms={this.state.terms}/>
        )
    }
}

class TermList extends React.Component {

    render() {
        var terms = this.props.terms.map(term =>
            <Term key={term._links.self.href} term={term}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>URI</th>
                    <th>Label</th>
                </tr>
                {terms}
                </tbody>
            </table>
        )
    }
}

class Term extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.term.iri}</td>
                <td>{this.props.term.label}</td>
            </tr>
        )
    }
}
export default App;
