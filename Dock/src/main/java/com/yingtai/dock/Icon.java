package com.yingtai.dock;

import AppTools.TaskControler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Icon extends DockItem{
    private String realPath;
    protected String tag;
    protected StackPane root;
    protected BooleanProperty isOpened;
    protected Group group;

    public Icon(){
        group=new Group();
        root=new StackPane();
        root.setMaxSize(Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
        root.setAlignment(Pos.BOTTOM_CENTER);
        root.setCursor(Cursor.HAND);

        root.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(2))));

        root.setPadding(new Insets(15,5,10,5));

        //region 程序打开圆点标志
        isOpened=new SimpleBooleanProperty(false);
        Circle dot=new Circle(Parament.iconOpenedDot);
        dot.setFill(Color.rgb(0,0,0,0.8));
        dot.setTranslateY(5);
//        dot.setStroke(Color.rgb(255,255,255,0.4));
        dot.visibleProperty().bind(isOpened);
        //endregion

        group.setScaleX(1/Parament.iconEnlargeScale);
        group.setScaleY(1/Parament.iconEnlargeScale);

        //嵌套两层group即可实现动画效果
        root.getChildren().addAll(new Group(group),dot);

    }
    public Icon(String tag, String realPath, Image image){
        this();
        this.tag=tag;
        this.realPath=realPath;
        ImageView imageView=new ImageView(image);
        group.getChildren().add(imageView);
        imageView.fitWidthProperty().bind(Parament.iconEnlargedWidth);
        imageView.fitHeightProperty().bind(Parament.iconEnlargedWidth);


        //region 设置图标圆角
//        Rectangle rectangle1=new Rectangle((int)Parament.iconWidth.get(),(int)Parament.iconWidth.get());
//        rectangle1.setArcHeight(40);
//        rectangle1.setArcWidth(40);
//        imageView.setClip(rectangle1);
        //endregion

        //region 设置图标按压时变暗特性
        int width=(int)image.getWidth();
        int height=(int)image.getHeight();
        WritableImage writableImageImage=new WritableImage(width, height);
        PixelReader pixelReader=image.getPixelReader();
        PixelWriter pixelWriter=writableImageImage.getPixelWriter();
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++) {
                Color color=pixelReader.getColor(x,y);
                pixelWriter.setColor(x,y,color.darker());
            }
        }
        imageView.setOnMousePressed(mouseEvent -> {
            imageView.setImage(writableImageImage);
        });
        imageView.setOnMouseReleased(mouseEvent -> {
            imageView.setImage(image);
        });
        //endregion

        //region 设置图标点击事件
        imageView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getButton()== MouseButton.PRIMARY){
                if(isOpened.getValue()){
                    show();
                }
                else{
                    //程序未打开，打开程序
                    new Thread(()->{
                        run();
                        System.out.println("程序已打开");
                    }).run();
                    isOpened.setValue(true);
                }
            }
        });
        //endregion

        setRightButtonMenu();

    }

    private void setRightButtonMenu(){
        root.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent contextMenuEvent) {
                ContextMenu contextMenu=new ContextMenu();

                MenuItem menuItem1 = new MenuItem("设置此图标");
                MenuItem menuItem2 = new MenuItem("从Dock栏中移除");
                MenuItem menuItemSep=new SeparatorMenuItem();
                MenuItem menuItem3 = new MenuItem("打开文件所在位置");
                MenuItem menuItem4 = new MenuItem("再次运行此程序");
                MenuItem menuItem5 = new MenuItem("以管理员模式运行");
                MenuItem menuItem6 = new MenuItem("退出");
                MenuItem menuItem7 = new MenuItem("打开");

                menuItem1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //要做的事情
                    }
                });
                menuItem2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //要做的事情
                    }
                });
                menuItem3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        new Thread(()->{
                            openFolder();
                            System.out.println("所在文件夹已打开");
                        }).run();
                    }
                });
                menuItem4.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        new Thread(()->{
                            reload();
                            System.out.println("程序再次运行");
                        }).run();
                    }
                });
                menuItem5.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        new Thread(()->{
                            runInManagerMode();
                            System.out.println("程序已以管理员模式打开");
                        }).run();
                        isOpened.setValue(true);
                    }
                });
                menuItem6.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        new Thread(()->{
                            quit();
                            System.out.println("程序退出");
                        }).run();
                        isOpened.setValue(false);
                    }
                });
                menuItem7.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        new Thread(()->{
                            run();
                            System.out.println("程序已打开");
                        }).run();
                        isOpened.setValue(true);
                    }
                });

                contextMenu.getItems().addAll(menuItem1,menuItem2,menuItemSep,menuItem3,menuItem4,menuItem5,menuItem6,menuItem7);
                contextMenu.setAutoFix(true);
                contextMenu.setStyle("-fx-background-radius: 0.5em;");

                Node node = contextMenuEvent.getPickResult().getIntersectedNode();

                contextMenu.show(node, javafx.geometry.Side.BOTTOM, 0, 0);
            }
        });
    }

    public void update(double percent) {
        if (percent < 0) {
            reset();
            return;
        } else if (percent > 1) {
            percent = 1;
        }
        double scale = 0.3 * percent + 1/Parament.iconEnlargeScale;
        group.setScaleX(scale);
        group.setScaleY(scale);
    }

    public void reset(){
        group.setScaleX(1/Parament.iconEnlargeScale);
        group.setScaleY(1/Parament.iconEnlargeScale);
    }

    public Node getNode(){
        return root;
    }

    public String getTag(){
        return tag;
    }

    public String getRealPath(){
        return realPath;
    }

    public void runInManagerMode(){//以管理者模式运行
        if(isOpened.getValue()) return;
        File file=new File(realPath);
        if(!file.isFile()){
            System.out.println("这不是一个有效的文件！！！！");//可以加提醒框
            return;
        }
        try {
            String programName = file.getName();
            List<String> list = new ArrayList<String>();
            list.add("cmd.exe");
            list.add("/c");
            list.add("start");
            list.add("\"" + programName + "\"");
            list.add("\"" + file.getPath() + "\"");
            ProcessBuilder pBuilder = new ProcessBuilder(list);
            pBuilder.start();
            isOpened=new SimpleBooleanProperty(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run(){//一般形式运行
        if(isOpened.getValue()) return;
        try {
            new ProcessBuilder(realPath).start();
            isOpened=new SimpleBooleanProperty(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFolder(){//打开所在文件夹
        int index=realPath.length()-1;
        while (index>=0){
            if(realPath.charAt(index)==File.separatorChar)
                break;
            index--;
        }
        String target =realPath.substring(0,index);
//        try {
//            java.awt.Desktop.getDesktop().open(new File(target));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try {
//            new ProcessBuilder(target).start();
            Desktop.getDesktop().open(new File(target));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quit(){//退出已经打开的程序
        if(!isOpened.getValue()) return;
        TaskControler.killTask(tag);
        isOpened=new SimpleBooleanProperty(false);
    }

    public void reload(){//重启程序
        if (isOpened.getValue()){
            this.quit();
            this.run();
        }else {
            isOpened=new SimpleBooleanProperty(true);
            this.run();
        }
    }
    public void hide(){//
        if(isOpened.getValue()){
//            try {
//                String s=tag+".exe";
//                String path="src/main/java/com/yingtai/tool/hide.exe";
//                List<String> list = new ArrayList<String>();
//                list.add("cmd.exe");
//                list.add(path);
//                list.add(s);
//                ProcessBuilder pBuilder = new ProcessBuilder(list);
//                pBuilder.start();
//                isOpened=new SimpleBooleanProperty(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            try {
                String s=tag+".exe";
                new ProcessBuilder("Dock/src/main/resources/com/yingtai/dock/tool/hide.exe",s).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void show(){//
        if(isOpened.getValue()){
            run();
        }
    }
}
