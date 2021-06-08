// import React, {Component} from 'react';
// import './styles.css';
//
// class Sorted_list extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             news: [],
//         };
//         //
//         // this.organizeByTitle = this.organizeByTitle.bind(this);
//     }
//
//     componentDidMount() {
//         fetch('/combinedNews')
//             .then(response => response.json())
//             .then(data => this.setState({news: data}))
//     }
//
//     //methods
//     // organizeByTitle(data){
//     //     let listData = data.map(article => (
//     //         <li>{article}</li>
//     //     ))
//     //     return listData;
//     // }
//
//     render() {
//         // let titles = this.organizeByTitle(this.state.news)
//         const news = this.state.news
//         const titles = news.map(article => (
//             // <InfiniteScroll next={()=>setPage(page + 1)} hasMore={true} dataLength={news.length}>
//                 <div className="container">
//                     <li className="news-list">
//                         <a href={article.url}>
//                             <div className="sourceContainer">
//                                 <i className="fab fa-hacker-news-square"/>
//                                 <div className="source">
//                                     {/*{setUTCSecond(article.epoch)}*/}
//                                     {article.source}
//                                 </div>
//                                 <div className="date">
//                                     {article.epoch}
//                                 </div>
//                             </div>
//                             <div className="titleContainer">
//                                 <div className="title">
//                                     {article.title}
//                                 </div>
//                                 <div className="picture">
//                                     <img src={"https://tinyurl.com/yebvn562"}>
//                                     </img>
//                                 </div>
//                             </div>
//                         </a>
//                     </li>
//                 </div>
//             // </InfiniteScroll>
//             // <li><Article props={article}></li>
//         ))
//
//         return (
//             // <containerName props = {this.state.news}>
//             <div className="newsTable-container">
//                 <div className="searchBar">Search bar</div>
//                 {titles}
//             </div>
//         );
//     }
//
// }
//
// export default Sorted_list;

import React, {Component} from 'react';
import './styles.css';

class Sorted_list extends Component {
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
        const STYLE = { height: 1000, overflow: 'scroll' }
        const news = this.state.news
        const date = new Date();
        console.log(date);
        function dateConvert(postedTime) {
            const difference_In_Time = date.toLocaleTimeString() - postedTime;
            const difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
            const difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
            const difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

            if(difference_In_Days === 0 && difference_In_Hours === 0){
                return (difference_In_Minutes + "m");
            } else if(difference_In_Days === 0){
                return (difference_In_Hours + "h");
            } else {
                return (difference_In_Days + "d");
            }
        }

        const titles = news.map(article => (

            <li className="news-list">
                <a href={article.url}>
                    <div className="sourceContainer">
                        <i className="fab fa-hacker-news-square"/>
                        <div className="source">
                            {article.source}
                        </div>
                        <div className="date">
                            {article.date}
                            <p>Testing</p>
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
            <div className="newsTable-container">
                <div className="searchBar">Search bar</div>
                <div ref={this.handleRef} style={STYLE} className="container">
                    {titles}
                </div>
                {this.state.isFetching ? 'Loading…' : null}
                {this.state.hasError ? 'Something went wrong' : null}
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