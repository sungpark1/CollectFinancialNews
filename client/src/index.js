import React, {Component} from "react";
import ReactDOM from "react-dom";
import {BrowserRouter as Router} from "react-router-dom";
import NavBar from "./components/nav_bar/nav_bar";
import SortedList from "./components/main/news_table/sorted_list";

import "./styles.css";
import QueryBar from "./components/main/query_bar/query_bar";

//Set everything up here
class Index extends Component {
    render() {
        return (
            <Router>
                <div className="App">
                    <NavBar />
                    <QueryBar />
                    <SortedList />
                </div>
            </Router>
        )
    }
}

ReactDOM.render(<Index/>, document.getElementById('root'));

