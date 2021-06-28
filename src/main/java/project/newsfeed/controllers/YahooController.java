package project.newsfeed.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.newsfeed.connectors.YahooConnector;
import project.newsfeed.connectors.YahooSearchConnector;
import project.newsfeed.models.News;
import project.newsfeed.services.NewsFeedService;

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

//    @GetMapping("/yahooSearched")
//    public JsonNode getSearchFullResponse() throws IOException {
//        return yahooSearchConnector.getFullResponse();
//    }

    @GetMapping("/yahooSearch")
    public List<News> getSearchedNews(
            @RequestParam String ticker
    ) throws IOException {
        return newsFeedService.filterSearchedYahooNews(ticker);
    }
}
