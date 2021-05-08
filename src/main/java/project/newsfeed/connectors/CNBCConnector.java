package project.newsfeed.connectors;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service("cnbc")
public class CNBCConnector {

    URL url;
    HttpURLConnection connection;

    @Autowired
    public CNBCConnector() throws IOException {
        this.url = new URL("https://cnbc.p.rapidapi.com/news/list-trending");
        this.connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("x-rapidapi-key", "91faf0d288msh6cfed19c98ec77ap19861fjsn7e995aff6067");
        connection.setRequestProperty("x-rapidapi-host", "cnbc.p.rapidapi.com");
        connection.setRequestProperty("accept", "application/json");
    }

    public JsonNode getFullResponse() throws IOException {
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(responseStream, JsonNode.class);
    }
}
