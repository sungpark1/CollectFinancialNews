package project.newsfeed;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.newsfeed.models.News;
import project.newsfeed.utils.JsonConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertingTest {

    public static void main(String[] args) throws IOException, JSONException {
        List<News> list = new ArrayList<>();

        News n = new News();
        n.setTitle("TestingTitle");
        n.setId("IDD");
        list.add(n);
        News n1 = new News();
        n1.setTitle("Test");
        n1.setId("IDDD");
        list.add(n1);

        System.out.println(JsonConverter.getInstance().toJsonNode(n));
        System.out.println(JsonConverter.getInstance().toJsonNode(n1));


        List<News> newsss = new ArrayList<>();
        newsss.add(n);
        newsss.add(n1);
        System.out.println(JsonConverter.getInstance().toJsonNode(newsss));

//        ObjectMapper mapper = new ObjectMapper();
//        JsonFactory factory = mapper.getFactory();
//        JSONObject obj = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//
//        for (News ne : list) {
//            jsonArray.put(ne);
//            System.out.println(mapper.writeValueAsString(ne)); //SAVE
//        }
//
//        obj.put("data", jsonArray);
//        System.out.println(obj);
//
//        JsonNode jsonNode = mapper.readTree(factory.createParser(obj.toString()));
//
//        System.out.println(obj.length());
//        jsonNode.get("data").forEach(news ->{
//            System.out.println(news);
//        });
    }

}
