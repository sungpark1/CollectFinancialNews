//package project.newsfeed;
//
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import project.newsfeed.models.News;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.util.*;
//
//class NewsfeedApplicationTests {
//
//    public static void main(String[] args) throws IOException, ParseException {
//
//        NewsfeedApplicationTests yahooNews = new NewsfeedApplicationTests();
//        NewsfeedApplicationTests cnbcNews = new NewsfeedApplicationTests();
//        NewsfeedApplicationTests combineNews = new NewsfeedApplicationTests();
//
//
//        String cnbc = new String(Files.readAllBytes(Paths.get("src/main/resources/static/cnbc.json")));
//        String yahoo = new String(Files.readAllBytes(Paths.get("src/main/resources/static/yahoo.json")));
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonFactory factory = mapper.getFactory();
//
//        JsonNode cnbcJson = mapper.readTree(factory.createParser(cnbc));
//        JsonNode yahooJson = mapper.readTree(factory.createParser(yahoo));
//
////        System.out.println(organizeCNBCNews(json));
////        System.out.println(organizeYahooNews(json));
//
//        for (News n : yahooNews.organizeYahooNews(yahooJson)) {
//            System.out.println(mapper.writeValueAsString(n)); //SAVE
//        }
//        System.out.println("woo hoo");
////        System.out.println(yahooNewsList);
////
////        for (News n : cnbcNews.organizeCNBCNews(cnbcJson)) {
//////            System.out.println(mapper.writeValueAsString(n)); //SAVE
////        }
////        List<News> yahooNewsList = new ArrayList<>(yahooNews.organizeYahooNews(yahooJson));
////        List<News> cnbcNewsList = new ArrayList<>(cnbcNews.organizeCNBCNews(cnbcJson));
////        List<News> combinedNewsList = new ArrayList<>();
////
////        combinedNewsList.addAll(yahooNewsList);
////        combinedNewsList.addAll(cnbcNewsList);
//
////        System.out.println(mapper.writeValueAsString(yahooNewsList));
////        System.out.println(mapper.writeValueAsString(cnbcNewsList));
////        System.out.println(mapper.writeValueAsString(combinedNewsList));
//
////        System.out.println(combinedNewsList);
//
//        PriorityQueue<News> heap = new PriorityQueue<>((a,b) -> b.getEpoch() - a.getEpoch());
//
//        heap.addAll(yahooNews.organizeYahooNews(yahooJson));
//        heap.addAll(cnbcNews.organizeCNBCNews(cnbcJson));
//
//        List<News> combinedNewsList = new ArrayList<>(heap.size());
//
//        while (!heap.isEmpty()) {
//            combinedNewsList.add(heap.poll());
//        }
//
//        for (News n : combinedNewsList) {
//            System.out.println(mapper.writeValueAsString(n));
//        }
//    }
//
////    public List<News> combinedNews(JsonNode yahooNews, JsonNode cnbcNews) throws IOException { // THIS IS TO COMBINE THE TWO NEWS LATER!!
////        PriorityQueue<News> heap = new PriorityQueue<>((a, b) -> b.getEpoch() - a.getEpoch());
////
//////        News n = new News();
//////        n.setEpoch(1234);
////
//////        for (News j  : organizeYahooNews(yahooNews)) {
////////            System.out.println(j.getEpoch());
//////            heap.add(j);
//////        }
////
//////        heap.add(n);
////        heap.addAll(organizeYahooNews(yahooNews));
////        heap.addAll(organizeCNBCNews(cnbcNews));
////
//////        System.out.println(heap.peek().getEpoch());
////        List<News> list = new ArrayList<>(heap);
//////        list.addAll()
////        return list;
////    }
//
//    public List<News> organizeYahooNews(JsonNode json) throws IOException {
//        List<News> list = new ArrayList<>();
//
//        json.get("items").get("result").forEach(news -> {
//                    News n = new News();
//                    if (news.has("published_at")) {
//                        n.setEpoch(news.get("published_at").asInt());
//                        if (news.has("uuid")) { n.setId(news.get("uuid").asText()); }
//                        if (news.has("title")) { n.setTitle(news.get("title").asText()); }
//                        if (news.has("link")) { n.setUrl(news.get("link").asText()); }
//                        list.add(n);
//                    }
//                }
//        );
//
//        return list;
//    }
//
//    public List<News> organizeCNBCNews(JsonNode json) throws IOException {
//        List<News> list = new ArrayList<>();
//
//        json.get("data").get("mostPopular").get("assets").forEach(news -> {
////                    System.out.println(news);
//                    News n = new News();
//
//                    if (news.has("dateLastPublished")) {
//                        String gmt = news.get("dateLastPublished").asText();
//                        gmt = gmt.substring(0, gmt.length() - 5);
//                        LocalDateTime localDate = LocalDateTime.parse(gmt);
//                        Integer gmtToEpoch = (int) localDate.toEpochSecond(ZoneOffset.UTC);
//                        n.setEpoch(gmtToEpoch);
//                        if(news.has("headline")) { n.setTitle(news.get("headline").asText()); }
//                        if (news.has("id")) { n.setId(news.get("id").asText()); }
//                        if (news.has("url")) { n.setUrl(news.get("url").asText()); }
//                        list.add(n);
//                    }
//
//
//                }
//        );
//        System.out.println(" ");
//
//        return list;
//    }
//
//}
