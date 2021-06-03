import React, {Component} from "react";
import ReactDOM from "react-dom";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Nav_Bar from "./components/nav_bar/nav_bar";
import Sorted_list from "./components/main/news_table/sorted_list";
import "./styles.css";

//Set everything up here
class Index extends Component {
    render() {
        return (
            <Router>
                <div className="App">
                    <Nav_Bar />
                    <Sorted_list />
                </div>
            </Router>
        )
    }
}

ReactDOM.render(<Index/>, document.getElementById('root'));

