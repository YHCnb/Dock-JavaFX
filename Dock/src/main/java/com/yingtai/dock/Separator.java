package com.yingtai.dock;

//import com.kieferlam.javafxblur.Blur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Separator extends DockItem{
    private Line line;

    public Separator(){
        line=new Line();
        root=new StackPane();
        root.setMaxSize(Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(line);
        root.setPadding(new Insets(0,20,0,20));
        root.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,null,null)));

        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(0);
        line.endYProperty().bind(Parament.iconWidth);
        line.setStrokeWidth(1.5);

        //设置右键菜单
        root.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent contextMenuEvent) {
                ContextMenu contextMenu=new ContextMenu();
                MenuItem menuItem1 = new MenuItem("添加图标");
                menuItem1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            showAddIconView();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                MenuItem menuItem2 = new MenuItem("添加分隔符");
                menuItem2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Dock.addIcon(-2,new Separator());
                    }
                });
                MenuItem menuItemSep=new SeparatorMenuItem();
                MenuItem menuItem3 = new MenuItem("隐藏显示任务栏");
                MenuItem menuItem4 = new MenuItem("程序设置");
                menuItem4.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            menuItem4.setDisable(true);
                            showSettingView();
//                            menuItem4.setDisable(false);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                MenuItem menuItem5 = new MenuItem("结束程序");
                menuItem5.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.exit(0);
                    }
                });

                contextMenu.getItems().addAll(menuItem1,menuItem2,menuItemSep,menuItem3,menuItem4,menuItem5);
                contextMenu.setAutoFix(true);
                contextMenu.setStyle("-fx-background-radius: 0.5em;");

                Node node = contextMenuEvent.getPickResult().getIntersectedNode();

                contextMenu.show(node, javafx.geometry.Side.BOTTOM, 0, 0);
            }
        });



    }

    private void showSettingView() throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("setting-view.fxml"));
        Pane root=fxmlLoader.load();
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,null,null)));
        SettingController settingController=fxmlLoader.getController();

        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage=new Stage();
        settingController.setStage(stage);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("程序设置");
        stage.setScene(scene);

        stage.show();
        settingController.setStyle();
        Parament.isHidable=false;

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Parament.isHidable=true;
            }
        });
    }

    private void showAddIconView() throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(this.getClass().getResource("addIcon-view.fxml"));
        Pane root=fxmlLoader.load();
        AddIconController addIconController=fxmlLoader.getController();
        Stage stage=new Stage();
        addIconController.setStage(stage);
        addIconController.setStyle();
        addIconController.setEvent();

        Scene scene=new Scene(root);
        scene.setFill(Color.rgb(255,255,255,0.1));
        stage.setTitle("添加图标");
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setResizable(false);
        stage.show();
        Parament.isHidable=false;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Parament.isHidable=true;
            }
        });
    }
}
