package project.newsfeed.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.newsfeed.connectors.YahooConnector;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/")
public class YahooController {

    @Autowired
    YahooConnector yahooConnector;

    @GetMapping("/yahoo")
    public JsonNode getFullResponse() throws IOException {
        return yahooConnector.getFullResponse();
    }

}
