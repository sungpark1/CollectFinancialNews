package project.collectFinancialNews.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.collectFinancialNews.services.NewsFeedService;
import project.collectFinancialNews.utils.JsonConverter;

import java.io.IOException;

@RestController
public class CombinedNewsController {

    @Autowired
    NewsFeedService newsFeedService;

    @GetMapping("/combinedNews")
    public JsonNode getCombinedNews() throws IOException {
        return newsFeedService.combinedNews();
    }

    @GetMapping("/test")
    public JsonNode testing(){
        return JsonConverter.getInstance().toJsonNode("testisWorking");
    }

}
