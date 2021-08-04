import React, {Component} from 'react';
import './styles.css';
import DefaultNewsFeed from "../news_table/defaultNewsFeed";

class SearchNewsFeed extends Component {

    constructor(props) {
        super(props);
        this.state = {
            news: [],
            searched: false
        };
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit = (event) => {
        event.preventDefault()
        const data = new FormData(event.target);
        const ticker = data.get('ticker');
        this.state.searched = true;
        fetch('/api/v2/tickers?tickerSymbol=' + ticker)
            .then(response => response.json())
            .then(data => this.setState({news: data}))
    }

    render() {
        const news = this.state.news

        function dateConvert(currentTime, postedDate) {
            let currDateEpoch = (new Date(currentTime)).getTime()
            let postedDateEpoch = (new Date(postedDate)).getTime()

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

        const searchedNews = news.map(article => (
            <div className="container">
                <li className="news-list">
                    <a href={article.url}>
                        <div className="sourceContainer">
                            <div className="source">
                                {article.source}
                            </div>
                            <div className="date">
                                {dateConvert(article.currentTime, article.date)}
                            </div>
                        </div>
                        <div className="titleContainer">
                            <div className="title">
                                {article.title}
                            </div>
                            <div className="picture">
                            </div>
                        </div>
                    </a>
                </li>
            </div>
        ))

        const topNews = <DefaultNewsFeed />

        return (
            <div>
                <div>
                    <div className="searchContainer">
                        <form onSubmit={this.handleSubmit}>
                            <input className="placeholderText" name="ticker" type="text" placeholder="Search"/>
                            <button className="submitButton">Search</button>
                        </form>
                    </div>
                    <div>
                        {this.state.searched === true ? searchedNews : topNews}
                    </div>
                </div>
            </div>

        );
    }
}

export default SearchNewsFeed;