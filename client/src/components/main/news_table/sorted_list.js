import React, {Component} from 'react';
import './styles.css';
import InfiniteScroll from "react-infinite-scroll-component";

function dateConverter() {

}

class Sorted_list extends Component {
    constructor(props) {
        super(props);
        this.state = {
            news: [],
        };
        //
        // this.organizeByTitle = this.organizeByTitle.bind(this);
    }

    componentDidMount() {
        fetch('/combinedNews')
            .then(response => response.json())
            .then(data => this.setState({news: data}))
    }

    //methods
    // organizeByTitle(data){
    //     let listData = data.map(article => (
    //         <li>{article}</li>
    //     ))
    //     return listData;
    // }

    render() {
        // let titles = this.organizeByTitle(this.state.news)
        const news = this.state.news
        const titles = news.map(article => (
            // <InfiniteScroll next={()=>setPage(page + 1)} hasMore={true} dataLength={news.length}>
                <div className="container">
                    <li className="news-list">
                        <a href={article.url}>
                            <div className="sourceContainer">
                                <i className="fab fa-hacker-news-square"/>
                                <div className="source">
                                    {/*{setUTCSecond(article.epoch)}*/}
                                    {article.source}
                                </div>
                                <div className="date">
                                    {article.epoch}
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
                </div>
            // </InfiniteScroll>
            // <li><Article props={article}></li>
        ))

        return (
            // <containerName props = {this.state.news}>
            <div className="newsTable-container">
                <div className="searchBar">Search bar</div>
                {titles}
            </div>
        );
    }

}

export default Sorted_list;

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