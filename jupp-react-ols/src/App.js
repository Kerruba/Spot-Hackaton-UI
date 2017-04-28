import React, { Component } from 'react';
import {
    BrowserRouter as Router,
    Route,
    Link
} from 'react-router-dom'

import './App.css';


// Define routes for home page and term page
const App = () => (
    <Router>
        <div>
            <Route exact path="/" component={HomeView}/>
            <Route path="/ontologies/:ontology/terms/:id" component={TermView}/>
        </div>
    </Router>
)


// Main homepage component that has a search box and shows search results
class HomeView extends Component {

    constructor(props) {
        super(props);
        this.state = {
            term: undefined};
    }
    render() {
        return (
            <div>
                <Search/>
            </div>
        )
    }
}

// Component for a term page
class TermView extends Component {

    render() {
        return (
            <div>
                <Term ontology={this.props.match.params.ontology} id={this.props.match.params.id}/>
            </div>
        )
    }
}

// Component for rendering a term
class Term extends Component {

    constructor(props) {
        super(props);
        this.state = {term: ''};
    }

    componentDidMount() {
        const ontology = this.props.ontology;
        const termId = this.props.id;

        console.log("getting " + ontology + " - " + termId);
        fetch("http://www.ebi.ac.uk/ols/api/ontologies/"+ontology+"/terms?short_form="+encodeURIComponent(termId))
            .then( response => response.json() )
            .then( json => { this.setState({term: json._embedded.terms[0]} );
            });
    }

    render() {
        return (
            <div>
                <h2>{this.state.term.label}</h2>
                <span>{this.state.term.iri}</span>
                <p>{this.state.term.description}</p>
            </div>
        )
    }
}

class TermAnnotation extends Component {

    render() {
        return <div>{this.props.key} : {this.props.value}</div>
    }
}

class Search extends Component{

    constructor(props) {
        super(props);
        this.state = {
            query: '',
            searchResults: []
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleClick = this.handleClick.bind(this);
    }

    handleChange(e) {
        this.setState({query : e.target.value})
    }

    handleClick(e) {
        e.preventDefault()
        this.doOlsSearch(this.state.query)
    }

    doOlsSearch(query) {
        fetch("http://www.ebi.ac.uk/ols/api/search?q="+query)
            .then( response => response.json() )
            .then( json => { this.setState({searchResults: json.response.docs} );
            });
    }

    render() {
        return (
            <div>
                <h2>Search OLS</h2>
                <form>
                    <input onChange={ this.handleChange } placeholder="Enter a search term"/>
                    <input type="submit" value="GO" onClick={this.handleClick}/>
                </form>
                <SearchResults searchResults={this.state.searchResults}/>
            </div>
        )
    }
}

class SearchResults extends Component{
    render() {
        var terms = this.props.searchResults.map(term =>
            <SearchTermSummary key={term.iri+term.ontology_name} term={term}/>
        );
        return (
            <table className="table">
                <thead>
                <tr>
                    <th>URI</th>
                    <th>Label</th>
                </tr>
                </thead>
                <tbody>
                {terms}
                </tbody>
            </table>
        )
    }
}

class SearchTermSummary extends Component{
    render() {
        return (
            <tr>
                <td><Link to={`/ontologies/${this.props.term.ontology_name}/terms/${this.props.term.short_form}`}>{this.props.term.iri}</Link></td>
                <td>{this.props.term.label}</td>
            </tr>
        )
    }
}



export default App;
