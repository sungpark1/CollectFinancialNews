package project.newsfeed.connectors;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public class ExampleConnector extends RestConnector {
    private final String apiKey;
    private final String basePath;
    private final String TICKER_ENDPOINT = "/auto-complete?q=%s";


    @Autowired
    public ExampleConnector(
            @Value("${example.host}") String host,
            @Value("${example.apiKey}") String apiKey,
            @Value("${example.apiKey}") String basePath

    ) {
        super(host);
        this.apiKey = apiKey;
        this.basePath = basePath;
    }

    public JsonNode getExample(
            String ticker,
            HttpServletRequest request
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("example", "example");
        headers.add("anotherExample", "anotherExample");


        return rq()
                .headers(headers)
                .send(basePath + String.format(TICKER_ENDPOINT, ticker), JsonNode.class)
                .getBody();

    }
}