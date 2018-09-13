package wuweiJava.spider.Module;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import wuweiJava.spider.Module.NewsWithRelated;

import java.io.IOException;

public class UrlNewsReader {
    public static NewsWithRelated readNews(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements titleElements = doc.select("title");
        String title = titleElements.first().text();
        String content = doc.select("meta[name=description]").attr("content");
        NewsWithRelated relatedNews = new NewsWithRelated(url, title, content);
        Elements relatedElements = doc.select(".timeline__item___2eWUN");
        for (Element element : relatedElements){
            String relatedTitle = element.select(".content-item___3KfMq").text();
            Elements children = element.children();
            String relatedUrl = children.get(3).child(0).absUrl("href");
            relatedNews.addRelated(relatedTitle, relatedUrl);
        }
        return relatedNews;

    }
}
