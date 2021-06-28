package project.newsfeed.connectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("yahoo")
public class YahooConnector {
    URL url;
    HttpURLConnection con;

    @Autowired
    public YahooConnector() throws IOException {
        this.url = new URL("https://apidojo-yahoo-finance-v1.p.rapidapi.com/news/list");
        this.con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("x-rapidapi-key", "91faf0d288msh6cfed19c98ec77ap19861fjsn7e995aff6067");
        con.setRequestProperty("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
        con.setRequestProperty("accept", "application/json");
    }



//    @Cacheable(cacheNames={"yahooResponse"}, cacheManager = "cacheWithTTL")
    public JsonNode getFullResponse() throws IOException {
        InputStream responseStream = con.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(responseStream, JsonNode.class);
    }

//    @Cacheable(value = "stockInfoByTicker", key = "#sungmin")
//    public JsonNode getStockInfoByTicker(String sungmin) {
//
////        api call
//        return
//    }

    /** SPRING BOOT IN-MEMORY CACHE
    yahooResponse = {

     },

    stockInfoByTicker = {
        "AAPL" : {
//            DETAILED INFO ABOUT APPLE
         },
        "BMW": {

        }
    }


     */
}

