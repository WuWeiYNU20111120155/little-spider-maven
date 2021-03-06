package wuweiJava.spider;

import wuweiJava.spider.Module.NewsWithRelated;
import wuweiJava.spider.Module.SearchState;
import wuweiJava.spider.Module.SpiderThread;
import wuweiJava.spider.Module.UrlNewsReader;
import wuweiJava.spider.view.ListViewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MultiThreadMain {

        // 本地存储新闻内容，如何在终端显示

        // 1. 抽象出 对象
        // 2. 设计 对象应该具有的属性，状态和行为
        // 3. 思考 对象之间交互
        // 4. 开始写代码

         static void main(String[] args) throws Exception {
            long startTime = System.currentTimeMillis();

            // 广度优先搜索
            SearchState searchSate = new SearchState(1000);



            String startUrl = "https://api.readhub.cn/topic/5bMmlAm75lD";
            NewsWithRelated startNews = UrlNewsReader.readNews(startUrl);

            searchSate.visit(startNews);
            while (searchSate.hasTarget()) {
                NewsWithRelated current = searchSate.poll();
                searchSate.addResult(current);
                List<SpiderThread> spiders = new ArrayList<>();
                for (Map.Entry<String, String> entry : current.getRelateds().entrySet()) {
                    String url = entry.getValue();
                    spiders.add(new SpiderThread(searchSate, url));
                }

                //等待所有线程停止
                for (SpiderThread spider : spiders) {
                    spider.join();
                }
            }
            long endTime=System.currentTimeMillis();

            new ListViewer(searchSate.getResults()).display();

            System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
        }
    }

