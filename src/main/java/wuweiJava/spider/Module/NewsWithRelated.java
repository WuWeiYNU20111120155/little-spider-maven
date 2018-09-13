package wuweiJava.spider.Module;

import java.util.HashMap;
import java.util.Map;

public class NewsWithRelated extends UrlNews {//继承

    private HashMap<String, String> relateds = new HashMap<>();//HashMap保存k-v，自己的私有成员变量
    public NewsWithRelated(String url, String title, String content){
        super(url,title,content);//调用父类构造方法
    }

    public void addRelated(String title, String url){
        this.relateds.put(title, url);
    }//添加相关新闻的title和url

    public  HashMap<String, String> getRelateds(){
        return this.relateds;
    }

    @Override
    public void display(){
        super.display();//调用父类display方法
        System.out.println("|Related|");
        for (Map.Entry<String, String> entry :this.relateds.entrySet()){//遍历相关新闻
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

}
