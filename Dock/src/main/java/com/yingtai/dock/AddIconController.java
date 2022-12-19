package com.yingtai.dock;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;

public class AddIconController {

    public StackPane root;
    public HBox hBox;
    public VBox leftVBox;
    public ImageView imageView;
    public VBox rightVBox;
    public HBox rightHBox1;
    public Label rightLabel1;
    public TextField rightTextField1;
    public HBox rightHBox2;
    public Label rightLabel2;
    public TextField rightTextField2;
    public Button rightButton2;
    public HBox rightHBox3;
    public Button rightButton31;
    public Button rightButton32;
    public Label leftLabel;
    public Stage stage;
//    private String realPath;
//    private String tag;
    private File iconFile;
    private File pathFile;
    private double offsetX;
    private double offsetY;

    public AddIconController(){
        iconFile=new File("Dock/src/main/resources/com/yingtai/dock/img/启动台.png");
    }

    public void setStyle() throws FileNotFoundException {
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("*************************");
                offsetX= mouseEvent.getScreenX()-stage.getX();
                offsetY= mouseEvent.getScreenY()-stage.getY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getScreenX()-offsetX);
                stage.setY(mouseEvent.getScreenY()-offsetY);
            }
        });
//        Robot robot=new Robot();
//        WritableImage writableImage=robot.getScreenCapture(null,new Rectangle2D(0,0,
//                Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight()));
//        ImageView blur=new ImageView(writableImage);
//        blur.setFitWidth(600);
//        blur.setFitHeight(300);
//        blur.setEffect(new BoxBlur(20,20,3));
//        hBox.setAlignment(Pos.CENTER);
//        leftVBox.setAlignment(Pos.CENTER);
//        rightVBox.setAlignment(Pos.CENTER);
//        hBox.setBackground(new Background(new BackgroundFill(Color.BLUE,null,null)));
//        animationTimer=new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                if(!stage.isFocused()){
//                    var writableImage2=robot.getScreenCapture(null,new Rectangle2D(0,0,
//                            Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight()));
//                    blur.setImage(writableImage2);
//                    System.out.println("**************");
//                }
//                System.out.println(stage.getX()+","+stage.getY());
//
//                blur.setViewport(new Rectangle2D(stage.getX(),stage.getY(),600,300));
//            }
//        };
//        animationTimer.start();

//        root.getChildren().add(0,blur);
//        hBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
//        leftVBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
//        hBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
//        hBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        leftVBox.setPadding(new Insets(50,50,50,50));
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setImage(new Image(new FileInputStream(iconFile)));
        leftLabel.setAlignment(Pos.CENTER);
        leftLabel.setFont(Font.font("微软雅黑", FontWeight.MEDIUM,12));
        rightVBox.setPadding(new Insets(50,10,10,10));
        rightHBox1.setSpacing(10);
        rightHBox2.setSpacing(10);
        rightHBox3.setSpacing(50);
        rightHBox3.setPadding(new Insets(10,50,10,50));
    }

    public void setEvent(){
        imageView.setCursor(Cursor.HAND);
        imageView.setOnMouseEntered(mouseEvent -> {
            Effect effect=new Glow();


            imageView.setEffect(effect);
        });
        imageView.setOnMouseExited(mouseEvent -> {
            imageView.setEffect(null);
        });
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FileChooser fileChooser=new FileChooser();
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("All Images", "*.*"),
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("GIF", "*.gif"),
                        new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                        new FileChooser.ExtensionFilter("PNG", "*.png"),
                        new FileChooser.ExtensionFilter("ICO","*.ico")
                );
                fileChooser.setTitle("选择图片");
                iconFile=fileChooser.showOpenDialog(stage);
                try {
                    if(iconFile!=null) imageView.setImage(new Image(new FileInputStream(iconFile)));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        rightButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser=new FileChooser();
                DirectoryChooser directoryChooser=new DirectoryChooser();
                fileChooser.setTitle("选择文件");
                pathFile= fileChooser.showOpenDialog(stage);
                if(pathFile!=null){
                    rightTextField1.setText(pathFile.getName());
                    rightTextField2.setText(pathFile.getAbsolutePath());
                }
            }
        });



        rightButton31.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        rightButton32.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    String tag=rightTextField1.getText();
                    String realPath=rightTextField2.getText();
                    if(iconFile!=null&&tag!=null&&tag.length()!=0&&realPath!=null&&realPath.length()!=0)
                        Dock.addIcon(new Icon(rightTextField1.getText(),rightTextField2.getText(),new Image(new FileInputStream(iconFile))));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                stage.close();
            }
        });
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }
}
