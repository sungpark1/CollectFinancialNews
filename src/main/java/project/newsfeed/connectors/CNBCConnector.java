package project.newsfeed.connectors;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service("cnbc")
public class CNBCConnector extends RestConnector {

    private final String apiKey;
    private final String apiHost;

    @Autowired
    public CNBCConnector(
            @Value("${feedMe.cnbc.host}") String host,
            @Value("${feedMe.cnbc.apiKey}") String apiKey
    ) {
        super(host);
        this.apiKey = apiKey;
        this.apiHost = host;
    }

    public JsonNode getFullResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-key", apiKey);
        headers.add("x-rapidapi-host", apiHost);

        String TRENDING_ENDPOINT = "/news/list-trending";
        return rq()
                .headers(headers)
                .verb(HttpMethod.GET)
                .send(String.format(TRENDING_ENDPOINT), JsonNode.class)
                .getBody();
    }
}
