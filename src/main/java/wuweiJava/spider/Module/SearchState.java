package wuweiJava.spider.Module;

import java.util.*;

public class SearchState {
    private final int Maxurls;
    private Queue<NewsWithRelated> newsQueue = new LinkedList<>();//LinkedList实现了Queue接口。
    //如果需要深度优先搜索需要改为栈
    private int count = 0;
    private Set<String> visited = new HashSet<>();//HashSet实现了set接口，数据不重复，用来存已经访问过的URL
    private ArrayList<Viewable> results = new ArrayList<>();//多态，可以保存实现了Viewable的对象
    //创建一个Viewable数组列表来展示队列里面的新闻以及相关新闻
    public SearchState(int Maxurls){
        this.Maxurls = Maxurls;
    }
    public boolean hasTarget(){
        return !newsQueue.isEmpty()&&count<Maxurls;
    }
     synchronized public void visit(NewsWithRelated news){
        if(!visited.contains(news.getUrl())){
            newsQueue.add(news);
            visited.add(news.getUrl());
        }
    }
    synchronized public NewsWithRelated poll(){
        return newsQueue.poll();
    }
    public void addResult(NewsWithRelated result){
        results.add(result);
        count++;
    }
    public ArrayList<Viewable> getResults(){
        return  results;
    }
}
