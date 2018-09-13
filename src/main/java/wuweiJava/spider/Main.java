package wuweiJava.spider;


import wuweiJava.spider.Module.NewsWithRelated;
import wuweiJava.spider.Module.UrlNewsReader;
import wuweiJava.spider.Module.Viewable;
import wuweiJava.spider.view.ListViewer;

import java.io.IOException;
import java.util.*;

public class Main {
    static final int maxURL =8;
    public static void main(String[] args) throws IOException {

        //广度优先搜索使用queue
        Queue<NewsWithRelated> newsQueue = new LinkedList<>();//LinkedList实现了Queue接口。
        //如果需要深度优先搜索需要改为栈
        String startUrl = "https://readhub.me/topic/5bMmlAm75lD";
        NewsWithRelated startRelatedNews = UrlNewsReader.readNews(startUrl);
        System.out.println(startRelatedNews.getUrl());
        //读取startUrl自己以及相关新闻返回NewsWithRelated对象

        int count = 0;
        Set<String> visited = new HashSet<>();//HashSet实现了set接口，数据不重复，用来存已经访问过的URL
        visited.add(startUrl);//startUrl已经读取所以天添加到已访问列表中
        newsQueue.add(startRelatedNews);//然后把相关新闻添加到队列中。
        ArrayList<Viewable> results = new ArrayList<>();//多态，可以保存实现了Viewable的对象
        //创建一个Viewable数组列表来展示队列里面的新闻以及相关新闻
        while(!newsQueue.isEmpty()&&count<maxURL){//当新闻队列不为空，初始为startRelatedNews对象，并在maxURL以下
            NewsWithRelated current = newsQueue.poll();//取出队首元素
            results.add(current);//多态，添加到Viewable数组列表中来
            count++;//数量加1
            for(Map.Entry<String,String> entry : current.getRelateds().entrySet()){//遍历取出来的对象
                String url = entry.getValue();//得到第一个相关新闻的URl
                if(!visited.contains(url)){//若这个相关新闻没有访问过
                    NewsWithRelated next = UrlNewsReader.readNews(url);//读取自己以及相关新闻
                    newsQueue.add(next);//添加到新闻队列里面
                    visited.add(url);//添加到已访问中
                }
            }
        }
        new ListViewer(results).display();//战展示相关新闻
    }
}
