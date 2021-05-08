package project.newsfeed.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.newsfeed.connectors.CNBCConnector;
import project.newsfeed.connectors.YahooConnector;
import project.newsfeed.services.NewsFeedService;
//import project.newsfeed.services.NewsFeedService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/")
public class CNBCController {

    @Autowired
    NewsFeedService newsFeedService;

//    @GetMapping("/cnbc")
//    public JsonNode getCNBCNews() throws IOException {
//        return newsFeedService.organizeCNBCNews();
//    }

//    @Autowired
//    CNBCConnector cnbcConnector;
//
//    @GetMapping("/cnbc")
//    public JsonNode getFullResponse() throws IOException {
//        return cnbcConnector.getFullResponse();
//    }

}
