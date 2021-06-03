package project.newsfeed.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.newsfeed.connectors.BreakingNewsConnector;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/")
public class BreakingNewsController {

    @Autowired
    BreakingNewsConnector breakingNewsConnector;

    @GetMapping("/breakingNews")
    public JsonNode getFullResponse() throws IOException{
        return breakingNewsConnector.getFullResponse();
    }

}
