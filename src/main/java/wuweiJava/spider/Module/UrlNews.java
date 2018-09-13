package wuweiJava.spider.Module;


public class UrlNews extends News {//继承
    private  String url;//私有成员变量不能被继承
    public UrlNews(String url, String title, String content){
        super(title, content);
        this.url = url;
    }
    public String getUrl(){
        return url;
    }

    @Override//重写
    public void display(){
        super.display();
        System.out.println("|URL| "+ this.getUrl());
    }
}
