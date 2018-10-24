package wuweiJava.spider.Module;

import com.sun.javafx.logging.PulseLogger;

import java.io.IOException;

public class SpiderThread extends Thread {
    private String url;
    private SearchState searchState;

    public SpiderThread(SearchState searchState,String url){
        this.url = url;
        this.searchState = searchState;
        start();
    }

    @Override
    public void run() {
        try {
            NewsWithRelated next = UrlNewsReader.readNews(url);//读取自己以及相关新闻
            searchState.visit(next);//添加到新闻队列里面//添加到已访问中
        }catch (IOException e){
            System.out.println("Ignored an error page: " + url);
                    }

                    }
                    }
