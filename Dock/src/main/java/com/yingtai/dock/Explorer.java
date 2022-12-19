package com.yingtai.dock;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.robot.Robot;
import javafx.stage.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Explorer extends Icon{

    public Explorer() {
        tag="仿达";

    }

//    public Node getNode() {
//        return null;
//    }

    public void run(){//与其他图标不同，可以通过新建仿达窗口打开多个explorer
        try {
//            List<String> list = new ArrayList<String>();
//            list.add("cmd.exe");
//            list.add("/c");
//            list.add("explorer");
//            ProcessBuilder pBuilder = new ProcessBuilder(list);
//            pBuilder.start();
            new ProcessBuilder("explorer").start();
            isOpened=new SimpleBooleanProperty(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openRunner(){//打开“运行”
//        Robot robot=new Robot();
//        robot.keyPress(KeyCode.WINDOWS);//模拟按下Win R
//        robot.keyPress(KeyCode.R);
//        robot.keyRelease(KeyCode.R);//释放Win R
//        robot.keyRelease(KeyCode.WINDOWS);
    }

}
