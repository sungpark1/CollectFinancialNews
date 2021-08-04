package project.collectFinancialNews.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.collectFinancialNews.connectors.YahooConnector;
import project.collectFinancialNews.connectors.YahooSearchConnector;
import project.collectFinancialNews.models.News;
import project.collectFinancialNews.services.NewsFeedService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class YahooController {

    @Autowired
    YahooConnector yahooConnector;
    @Autowired
    YahooSearchConnector yahooSearchConnector;

    @Autowired
    NewsFeedService newsFeedService;

    @GetMapping("/yahoo")
    public JsonNode getFullResponse() throws IOException {
        return yahooConnector.getFullResponse();
    }

    @GetMapping("/tickers")
    public List<News> getSearchedNews(
            @RequestParam String tickerSymbol
    ) throws IOException {
        return newsFeedService.filterSearchedYahooNews(tickerSymbol);
    }
}
