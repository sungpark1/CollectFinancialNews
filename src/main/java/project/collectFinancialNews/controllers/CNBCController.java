package project.collectFinancialNews.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.collectFinancialNews.connectors.CNBCConnector;
import project.collectFinancialNews.services.NewsFeedService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/")
public class CNBCController {

    @Autowired
    NewsFeedService newsFeedService;

    @Autowired
    CNBCConnector cnbcConnector;

    @GetMapping("/cnbc")
    public JsonNode getFullResponse() throws IOException {
        return cnbcConnector.getFullResponse();
    }

}
