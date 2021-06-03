package project.newsfeed.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.newsfeed.connectors.CNBCConnector;
import project.newsfeed.connectors.YahooConnector;
import project.newsfeed.models.News;
import project.newsfeed.utils.JsonConverter;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

@Service
public class NewsFeedService {

    @Autowired
    YahooConnector yahooConnector;

    @Autowired
    CNBCConnector cnbcConnector;

    public JsonNode combinedNews() throws IOException {
        PriorityQueue<News> heap = new PriorityQueue<>((a, b) -> b.getDate().compareTo(a.getDate()));

//        System.out.println(JsonConverter.getInstance().toJsonNode(organizeCNBCNews()));
        heap.addAll(organizeCNBCNews());
        heap.addAll(organizeYahooNews());

        List<News> combinedNewsList = new ArrayList<>(heap.size());

        while (!heap.isEmpty()) {
            combinedNewsList.add(heap.poll());
        }

//        CombinedNews combinedNews = new CombinedNews();
//        combinedNews.setCombinedNews(combinedNewsList);

//        ObjectMapper mapper = new ObjectMapper();
//        JsonFactory factory = mapper.getFactory();
//        JSONObject obj = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
        return JsonConverter.getInstance().toJsonNode(combinedNewsList);
    }
    public List<News> organizeCNBCNews() throws IOException {
        String cnbc = new String(Files.readAllBytes(Paths.get("src/main/resources/static/cnbc.json")));
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();

        JsonNode json = mapper.readTree(factory.createParser(cnbc));
//        JsonNode json = cnbcConnector.getFullResponse();

        List<News> list = new ArrayList<>();

        json.get("data").get("mostPopular").get("assets").forEach(news -> {
                    News n = new News();
                    if (news.has("dateLastPublished")) {
                        String gmt = news.get("dateLastPublished").asText();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
                        try {
                            n.setDate(formatter.parse(gmt));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if (news.has("id")) {
                            n.setId(news.get("id").asText());
                        }
                        if (news.has("headline")) {
                            n.setTitle(news.get("headline").asText());
                        }
                        if (news.has("url")) {
                            n.setUrl(news.get("url").asText());
                        }
                        n.setSource("CNBC");
                        list.add(n);
                    }
                }
        );

        return list;
    }

    public List<News> organizeYahooNews() throws IOException, NullPointerException {
        String yahoo = new String(Files.readAllBytes(Paths.get("src/main/resources/static/yahoo.json")));
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonNode json = mapper.readTree(factory.createParser(yahoo));
//        JsonNode json = yahooConnector.getFullResponse();

        List<News> list = new ArrayList<>();
        json.get("items").get("result").forEach(news -> {
                    News n = new News();
                    if (news.has("published_at")) {
                        n.setDate(new Date(news.get("published_at").asLong() * 1000));
                        if (news.has("uuid")) {
                            n.setId(news.get("uuid").asText());
                        }
                        if (news.has("title")) {
                            n.setTitle(news.get("title").asText());
                        }
                        if (news.has("link")) {
                            n.setUrl(news.get("link").asText());
                        }
                        n.setSource("Yahoo Finance");
                        list.add(n);
                    }
                }
        );

        return list;
    }


}
