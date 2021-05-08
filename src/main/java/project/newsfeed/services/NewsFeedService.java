package project.newsfeed.services;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.newsfeed.connectors.CNBCConnector;
import project.newsfeed.connectors.YahooConnector;
import project.newsfeed.models.CombinedNews;
import project.newsfeed.models.News;
import project.newsfeed.utils.JsonConverter;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class NewsFeedService {

    @Autowired
    YahooConnector yahooConnector;

    @Autowired
    CNBCConnector cnbcConnector;

    public JsonNode combinedNews() throws IOException {
        PriorityQueue<News> heap = new PriorityQueue<>((a, b) -> b.getEpoch() - a.getEpoch());

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
                        gmt = gmt.substring(0, gmt.length() - 5);
                        LocalDateTime localDate = LocalDateTime.parse(gmt);
                        Integer gmtToEpoch = (int) localDate.toEpochSecond(ZoneOffset.UTC);
                        n.setEpoch(gmtToEpoch);
                        if (news.has("__typename")) {
                            n.set__typename(news.get("__typename").asText());
                        }
                        if (news.has("id")) {
                            n.setId(news.get("id").asText());
                        }
                        if (news.has("url")) {
                            n.setUrl(news.get("url").asText());
                        }
                        list.add(n);
                    }
                }
        );

        return list;
    }

    public List<News> organizeYahooNews() throws IOException {
        String yahoo = new String(Files.readAllBytes(Paths.get("src/main/resources/static/yahoo.json")));
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonNode json = mapper.readTree(factory.createParser(yahoo));
//        JsonNode json = yahooConnector.getFullResponse();

        List<News> list = new ArrayList<>();
        json.get("items").get("result").forEach(news -> {
                    News n = new News();
                    if (news.has("published_at")) {
                        n.setEpoch(news.get("published_at").asInt());
                        if (news.has("uuid")) {
                            n.setUuid(news.get("uuid").asText());
                        }
                        if (news.has("title")) {
                            n.setTitle(news.get("title").asText());
                        }
                        if (news.has("link")) {
                            n.setUrl(news.get("link").asText());
                        }
                        list.add(n);
                    }
                }
        );

        return list;
    }


}
