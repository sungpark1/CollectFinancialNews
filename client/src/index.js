import React, { Component } from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import './components/main/news_table/sorted_list';
import Nav_bar from "./components/nav_bar/nav_bar";

import "./styles.css";

//Set everything up here
class Index extends Component {
  render(){
        return (
            <Router>
                <div className="App">
                    <Nav_bar />
                    <div>
                        <p>hello</p>
                    </div>
                </div>
            </Router>
        )
  }
}

ReactDOM.render(<Index />, document.getElementById('root'));

