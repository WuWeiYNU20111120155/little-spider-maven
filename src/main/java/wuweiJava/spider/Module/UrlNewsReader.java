package wuweiJava.spider.Module;


import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class UrlNewsReader {
    public static NewsWithRelated readNews(String url) throws IOException {


        //String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";

        Connection.Response res = Jsoup.connect(url)
                .header("Content-Type", "application/json;charset=UTF-8")
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                .ignoreContentType(true)
                .timeout(12000).execute();
        String jsonString = res.body();
       // if(jsonString.charAt(0)!='{') return new NewsWithRelated(url, "", "");
        JSONObject json = new JSONObject(jsonString);
//       Elements titleElements = doc.select("title");
        String title = json.getString("title");
        String content = json.get("summary").toString();
        NewsWithRelated relatedNews = new NewsWithRelated(url, title, content);
        try {
            JSONObject timeline = (JSONObject) json.get("timeline");
            JSONArray topics = timeline.getJSONArray("topics");
            for (int i = 0; i < topics.length(); i++) {
                JSONObject topic = (JSONObject) topics.get(i);
                String id = topic.getString("id");
                String relatedTitle = topic.getString("title");
                String relatedApi = "https://api.readhub.cn/topic/" + id;
                String relatedUrl = "https://api.readhub.cn/topic/" + id;
                relatedNews.addRelated(relatedTitle, relatedApi, relatedUrl);
            }
//        Elements relatedElements = doc.select(".timeline__item___2eWUN");
//        for (Element element : relatedElements){
//            String relatedTitle = element.select(".content-item___3KfMq").text();
//            Elements children = element.children();
//            String relatedUrl = children.get(3).child(0).absUrl("href");
//            relatedNews.addRelated(relatedTitle, relatedUrl);
//        }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return relatedNews;
    }
}
