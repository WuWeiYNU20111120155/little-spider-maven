package wuweiJava.spider.Module;

public class News implements Viewable {//实现接口
    private String title;
    private String content;

    public News(String title, String content){
        this.title = title;
        this.content = content;
    }
    public String getTitle(){
        return title;
    };
    public String getContent(){
        return  content;
    }

    @Override
    public void display() {//实现接口的display方法
        System.out.println("|Title| "+ this.getTitle());
        System.out.println("|Content| "+ this.getContent());
    }
}
