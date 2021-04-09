//package project.newsfeed.Controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
//public class ApiController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    private static String url = "https://api.nytimes.com/svc/topstories/v2/us.json?api-key=";
//    private static String apiKey = "4gAHiz8Oeeyp7PGNF7fyGyNbbcoRhdKd";
//
//    @GetMapping("/topStories")
//    public String getTopStories() {
//        String topStories = restTemplate.getForObject(url + apiKey, String.class);
//        return topStories;
//    }
//
//}
