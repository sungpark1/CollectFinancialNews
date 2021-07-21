package project.collectFinancialNews.connectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NYTimesConnector {

    private final RestTemplate restTemplate;

    private static final String url = "https://api.nytimes.com/svc/topstories/v2/home.json?api-key=";
    private static final String apiKey = "4gAHiz8Oeeyp7PGNF7fyGyNbbcoRhdKd";

    public NYTimesConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/topStories")
    public String getTopStories() {
        return restTemplate.getForObject(url + apiKey, String.class);
    }
}
