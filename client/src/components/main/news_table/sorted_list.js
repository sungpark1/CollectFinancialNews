import React, {Component} from 'react';

class sorted_list extends Component {
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
        // do call methods here
        // let titles = this.organizeByTitle(this.state.news)
        const news = this.state.news
        const titles = news.map(article => (
            <li>{article.epoch}</li>
            // <li><Article props={article}></li>
        ))

        return (
            // <containerName props = {this.state.news}>
            <div className='div1'>
                {/*{console.log(news)}*/}
                {/*<div>this.state.news</div>*/}
                {titles}
            </div>
        );
    }

}

export default sorted_list;

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