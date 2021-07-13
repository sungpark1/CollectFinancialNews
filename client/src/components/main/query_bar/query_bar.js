import React, {Component} from 'react';
import {BrowserRouter as Router, Link, Route, Switch} from 'react-router-dom';
import {withRouter} from "react-router";
import './styles.css';

class QueryBar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            news: [],
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (event) => {
        event.preventDefault()
        const data = new FormData(event.target);
        const ticker = data.get('ticker');
        fetch('/api/v2/yahooSearch?ticker=' + ticker)
            .then(response => response.json())
            .then(data => this.setState({news: data}))
    }

    render() {
        const news = this.state.news
        function dateConvert(postedDate) {
            let currDate = new Date()
            let currDateEpoch = Math.round(((new Date(currDate)).getTime()))
            let postedDateEpoch = ((new Date(postedDate)).getTime())

            let diff = currDateEpoch - postedDateEpoch

            let diffInDays = Math.round((diff / (1000 * 60 * 60 * 24)) % 365)
            let diffInHours = Math.round((diff / (1000 * 60 * 60)) % 24)
            let diffInMins = Math.round((diff / (1000 * 60)) % 60)

            if (diffInDays === 0 && diffInHours === 0) {
                return diffInMins + "m"
            } else if (diffInDays === 0) {
                return diffInHours + "h"
            } else {
                return diffInDays + "d"
            }
        }

        const titles = news.map(article => (
            <li className="news-list">
                <a href={article.url}>
                    <div className="sourceContainer">
                        <div className="source">
                            {article.source}
                        </div>
                        <div className="date">
                            {dateConvert(article.date)}
                        </div>
                    </div>
                    <div className="titleContainer">
                        <div className="title">
                            {article.title}
                        </div>
                        <div className="picture">
                            <img src={"https://tinyurl.com/yebvn562"}>
                            </img>
                        </div>
                    </div>
                </a>
            </li>
        ))

        return (
            <div>
                <div>
                    <div className="searchContainer">
                        <form onSubmit={this.handleSubmit}>
                            <input className="placeholderText" name="ticker" type="text" placeholder="Search"/>
                            <button className="submitButton">Search</button>
                        </form>
                    </div>
                    <div className="container">
                        {/*<Link to={"/searched"}>*/}
                        {titles}
                        {/*</Link>*/}
                    </div>
                </div>
            </div>

        );
    }
}

export default QueryBar;
