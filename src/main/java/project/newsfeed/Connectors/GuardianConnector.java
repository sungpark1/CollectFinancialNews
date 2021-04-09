package project.newsfeed.Connectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GuardianConnector {

    private final RestTemplate restTemplate;

    private static final String url = "https://content.guardianapis.com/search?api-key=";
    private static final String apiKey = "ea381b4e-5d12-466f-8a08-dc8f79a92c47";

    public GuardianConnector(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/GuardianNews")
    public String getGuardianNews() {
        return restTemplate.getForObject(url + apiKey, String.class);
    }


}
