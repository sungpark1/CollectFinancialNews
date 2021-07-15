import React, {Component} from "react";
import ReactDOM from "react-dom";
import {BrowserRouter as Router} from "react-router-dom";
import NavBar from "./components/nav_bar/nav_bar";

import "./styles.css";
import SearchNewsFeed from "./components/main/query_bar/searchNewsFeed";

class Index extends Component {
    render() {
        return (
            <Router>
                <div className="App">
                    <NavBar/>
                    <SearchNewsFeed/>
                </div>
            </Router>
        )
    }
}

ReactDOM.render(<Index/>, document.getElementById('root'));

