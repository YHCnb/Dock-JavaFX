package com.yingtai.dock;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Config {
    private static final String configIconPath="../config/icon.ini";

    public static List<DockItem> readDockItemConfig() {
        List<DockItem> dockItemList=new ArrayList<>();
        String fileName=configIconPath;
        File file=new File(fileName);
        if(!file.exists()||file.length()==0){
            //默认图标文件
            fileName="Dock/src/main/resources/com/yingtai/dock/config/icon.ini";
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String tag=null;
            String realPath=null;
            int i=0;
            //添加dockItem
            while((line=bufferedReader.readLine())!=null){
                if(line.startsWith("tag=")){
                    tag=line.substring(4);
                }
                else if(line.startsWith("realpath=")){
                    realPath=line.substring(9);
                }
                else if((line.startsWith("[icon"))&&tag!=null&&realPath!=null){
                    dockItemList.add(new Icon(tag,realPath,new Image(new FileInputStream("Dock/src/main/resources/com/yingtai/dock/img/"+tag+".png"))));
                    i++;
                    System.out.println(i);
                }
                else if(line.startsWith("[separator")){
                    dockItemList.add(new Separator());
                }

            }
            if(tag!=null&&realPath!=null){
                dockItemList.add(new Icon(tag,realPath,new Image(new FileInputStream("Dock/src/main/resources/com/yingtai/dock/img/"+tag+".png"))));
            }
            dockItemList.add(new Clock());
            return dockItemList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void renewIconConfig(List<Icon> iconList) {
        File file=new File(configIconPath);
        if(!file.exists()){
            //目录不存在时无法直接创建文件
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(configIconPath))) {
            String line1="[icon";
            String line2="tag=";
            String line3="realpath=";
            int counter=1;
            for(Icon icon:iconList){
                bufferedWriter.write(line1+counter+"]");
                bufferedWriter.newLine();
                bufferedWriter.write(line2+icon.getTag());
                bufferedWriter.newLine();
                bufferedWriter.write(line3+icon.getRealPath());
                bufferedWriter.newLine();
                counter++;
            }
            System.out.println("配置文件已更新");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
