package project.collectFinancialNews.connectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NewsAPI {

    private final RestTemplate restTemplate;

    private static final String url = "https://newsapi.org/v2/everything?q=keyword&apiKey=";
    private static final String apiKey = "3bfd1ab1c76943038bfe5abe1794dac9";

    public NewsAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/NewsApi")
    public String getNewsApi() {
        return restTemplate.getForObject(url + apiKey, String.class);
    }
}
