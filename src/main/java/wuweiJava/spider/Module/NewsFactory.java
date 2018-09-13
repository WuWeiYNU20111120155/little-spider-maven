package wuweiJava.spider.Module;

import java.io.File;
import java.util.ArrayList;

public class NewsFactory {//新闻工厂生成新闻列表，使用了工厂设计模式
    private File newsDir;

    public NewsFactory(String dir) throws Exception {
        newsDir = new File(dir);
        if (!newsDir.exists()) {
            throw new Exception("路径不存在");
        }
        if (!newsDir.isDirectory()) {
            throw new Exception("输入路径不是一个合法目录");
        }
    }

    public ArrayList<News> fetch() {
        ArrayList<News> newsList = new ArrayList<>();
        File[] files = newsDir.listFiles();
        if (files != null) {
            for (File file : files) {
                NewsReader newsReader = null;
                if (file.getName().endsWith(".txt")) {
                    newsReader = new TextNewsReader(file);//继承
                } else if (file.getName().endsWith(".json")) {
                    newsReader = new JsonNewsReader(file);//继承

                    newsList.add(newsReader.readNews());//多态，使用各自的readNews方法
                }
            }


        }
        return newsList;
    }
}

