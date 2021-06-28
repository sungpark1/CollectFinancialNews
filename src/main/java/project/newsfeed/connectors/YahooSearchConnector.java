package project.newsfeed.connectors;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service("yahooSearch")
public class YahooSearchConnector extends RestConnector {

    private final String apiKey;
    private final String host;
    private final String TICKER_ENDPOINT = "/auto-complete?q=%s";
    private final String basePath;

    @Autowired
    public YahooSearchConnector(
            @Value("${feedMe.yahooSearch.host}") String host,
            @Value("${feedMe.yahooSearch.apiKey}") String apiKey,
            @Value("${feedMe.yahooSearch.basePath") String basePath
//        this.url = new URL("https://apidojo-yahoo-finance-v1.p.rapidapi.com/auto-complete?q=");
//        this.con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("x-rapidapi-key", "91faf0d288msh6cfed19c98ec77ap19861fjsn7e995aff6067");
//        con.setRequestProperty("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
//        con.setRequestProperty("accept", "application/json");
    ) {
        super(host);
        this.apiKey = apiKey;
        this.host = host;
        this.basePath = basePath;
    }

    public JsonNode getFullResponse(
            String ticker
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-key", apiKey);
        headers.add("x-rapidapi-host", host);

        return rq()
                .headers(headers)
                .verb(HttpMethod.GET)
                .send(basePath + String.format(TICKER_ENDPOINT, ticker), JsonNode.class)
                .getBody();
    }

}
