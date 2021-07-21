import React, {Component} from 'react';
import './styles.css';

class DefaultNewsFeed extends Component {
    constructor(props) {
        super(props);
        this.state = {
            news: [],
            page: 1,
            isFetching: false,
            hasError: false,
            hasMore: true
        };

    }

    componentDidMount() {
        this.attach()
        this.fetch()
    }

    handleScroll = e => {
        const scrollPercentage =
            this.element.scrollTop /
            (this.element.scrollHeight - this.element.clientHeight)

        if (scrollPercentage > 0.9) {
            this.fetch()
        }
    }

    attach() {
        this.element.addEventListener('scroll', this.handleScroll)
    }

    detach() {
        this.element.removeEventListener('scroll', this.handleScroll)
    }

    handleRef = el => {
        this.element = el
    }

    fetch() {
        if (this.state.isFetching) {
            return
        }

        this.setState({
            hasError: false,
            isFetching: true,
        })

        fetch('/combinedNews')
            .then(response => response.json())
            .then(data => {
                this.setState(prevState => ({
                    news: prevState.news.concat(data),
                    page: prevState.page + 1,
                }))

                if (data.length < 10) {
                    this.detach()
                }
            })
            .catch(() =>
                this.setState({
                    hasError: true,
                })
            )
            .finally(() => {
                this.setState({
                    isFetching: false,
                })
            })
    }

    render() {
        const STYLE = {height: 1000, overflow: 'scroll'}
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

        const titles = news.map(article => (

            <li className="news-list" >
                <a href={article.url}>
                    <div className="sourceContainer">
                        {/*<i className="fab fa-hacker-news-square"/>*/}
                        <div className="source">
                            {article.source}
                        </div>
                        <div className="date">
                            {/*{article.date}*/}
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
        ))

        return (
            <div className="newsTable-container">
                <div ref={this.handleRef} style={STYLE} className="container">
                    {titles}
                </div>
                {this.state.isFetching ? 'Loading…' : null}
                {this.state.hasError ? 'Something went wrong' : null}
            </div>
        );
    }

}

export default DefaultNewsFeed;


// constructor
// this.state = {
//     title: this.props.article.title,
//     epoch: this.props.article.epoch,
// }
//
// render() {
//     return
//     <h1>{this.state.title}</h1>
//     <h2>{this.state.epoch}</h2>
// }