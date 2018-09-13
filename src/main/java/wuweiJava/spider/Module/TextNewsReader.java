package wuweiJava.spider.Module;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextNewsReader extends NewsReader{//继承抽象类
    public TextNewsReader(File file){
        super(file);
    }//调用父类构造函数
    @Override
    public News readNews() {//实现readNews方法
        News news = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));//读取文件内容到缓存中
            String title = reader.readLine();
            reader.readLine();
            String content = reader.readLine();
            news = new News(title,content);
        }catch (IOException e){
            System.out.println("新闻读取出错");
        }
        return news;
    }
}

