import React, {Component} from 'react';
import './styles.css';

class QueryBar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            news: [],
            ticker: '',
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
        const titles = news.map(article => (
            <li>
                {article.title}
                {article.date}
            </li>
        ))

        return (
            <div>
                <div className="searchBar">
                    <form onSubmit={this.handleSubmit}>
                        <input className="placeholder-text" name="ticker" type="text" placeholder="  Search"/>
                        <button>Search</button>
                    </form>
                    <div>
                        {titles}
                    </div>
                </div>
            </div>

        );
    }
}

export default QueryBar;
