package project.newsfeed.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.newsfeed.connectors.CNBCConnector;
import project.newsfeed.connectors.YahooConnector;
import project.newsfeed.connectors.YahooSearchConnector;
import project.newsfeed.models.News;
import project.newsfeed.utils.JsonConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.*;

@Service
public class NewsFeedService {

    @Autowired
    YahooConnector yahooConnector;

    @Autowired
    CNBCConnector cnbcConnector;

    @Autowired
    YahooSearchConnector yahooSearchConnector;

    public JsonNode combinedNews() throws IOException {
        PriorityQueue<News> heap = new PriorityQueue<>((a, b) -> b.getDate().compareTo(a.getDate()));

        heap.addAll(organizeCNBCNews());
        heap.addAll(organizeYahooNews());

        List<News> combinedNewsList = new ArrayList<>(heap.size());

        while (!heap.isEmpty()) {
            combinedNewsList.add(heap.poll());
        }

        return JsonConverter.getInstance().toJsonNode(combinedNewsList);
    }

    public List<News> organizeCNBCNews() throws IOException {
        JsonNode json = cnbcConnector.getFullResponse();

        List<News> list = new ArrayList<>();

        json.get("data").get("mostPopularEntries").get("assets").forEach(news -> {
                    News n = new News();
                    if (news.has("dateLastPublished")) {
                        String gmt = news.get("dateLastPublished").asText();
                        n.setDate(gmt.substring(0, gmt.length() - 5));
                        //Current Time
                        long currEpoch = new Date().getTime();
                        Date date = new Date(currEpoch);
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                        n.setCurrentTime(formatter.format(date));
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

    public List<News> organizeYahooNews() throws IOException {
        JsonNode json = yahooConnector.getFullResponse();

        List<News> list = new ArrayList<>();
        json.get("items").get("result").forEach(news -> {
                    News n = new News();
                    if (news.has("published_at")) {
                        Date date = new Date(news.get("published_at").asLong() * 1000);
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                        n.setDate(formatter.format(date));
                        //Current Time
                        long currEpoch = new Date().getTime();
                        Date epochToDate = new Date(currEpoch);
                        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                        n.setCurrentTime(formatter.format(epochToDate));
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

    public List<News> filterSearchedYahooNews(String ticker) throws IOException {
        JsonNode json = yahooSearchConnector.getFullResponse(ticker);

        List<News> list = new ArrayList<>();
        json.get("news").forEach(news -> {
                    News n = new News();
                    if (news.has("providerPublishTime")) {
                        Date date = new Date(news.get("providerPublishTime").asLong() * 1000);
                        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                        n.setDate(formatter.format(date));
                        //Current Time
                        long currEpoch = new Date().getTime();
                        Date epochToDate = new Date(currEpoch);
                        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                        n.setCurrentTime(formatter.format(epochToDate));
                        if (news.has("uuid")) {
                            n.setId(news.get("uuid").asText());
                        }
                        if (news.has("title")) {
                            n.setTitle(news.get("title").asText());
                        }
                        if (news.has("link")) {
                            n.setUrl(news.get("link").asText());
                        }
                        if (news.has("publisher")) {
                            n.setSource(news.get("publisher").asText());
                        }
                        list.add(n);
                    }
                }
        );

        return list;
    }

}
