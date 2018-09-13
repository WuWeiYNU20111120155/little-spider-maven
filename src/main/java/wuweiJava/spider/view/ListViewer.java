package wuweiJava.spider.view;

import wuweiJava.spider.Module.Viewable;

import java.util.ArrayList;

public class ListViewer {//展示是实现了接口Viewable的对象
    private ArrayList<Viewable> viewableList;//
    public ListViewer(ArrayList<Viewable> viewableList){
        this.viewableList = viewableList;
    }//构造函数
    public void display(){
        for(Viewable viewableItem:viewableList){
            System.out.println("---------------------------------------------------------------------------------");
            viewableItem.display();
        }
    }
}
