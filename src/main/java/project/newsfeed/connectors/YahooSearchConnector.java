package project.newsfeed.connectors;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang.time.DateUtils.MILLIS_PER_MINUTE;


@Service("yahooSearch")
public class YahooSearchConnector extends RestConnector {

    private final String apiKey;
    private final String apiHost;

    @Autowired
    public YahooSearchConnector(
            @Value("${feedMe.yahooSearch.host}") String host,
            @Value("${feedMe.yahooSearch.apiKey}") String apiKey
    ) {
        super(host);
        this.apiKey = apiKey;
        this.apiHost = host;
    }

    @Cacheable(value = "searchByTicker", unless = "#result == null")
    @CacheEvict(allEntries = true, value = {"searchByTicker"})
    @Scheduled(fixedRate = 60 * MILLIS_PER_MINUTE)
    public JsonNode getFullResponse(
            String ticker
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-key", apiKey);
        headers.add("x-rapidapi-host", apiHost);

        String TICKER_ENDPOINT = "/auto-complete?q=%s";
        return rq()
                .headers(headers)
                .verb(HttpMethod.GET)
                .send(String.format(TICKER_ENDPOINT, ticker), JsonNode.class)
                .getBody();
    }

}
