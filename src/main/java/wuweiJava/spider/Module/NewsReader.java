package wuweiJava.spider.Module;

import java.io.File;

public abstract class NewsReader {//抽象类用来被继承，定义好API
    protected File file;
    public NewsReader(File file){
        this.file = file;
    }
    public abstract News readNews();
}
