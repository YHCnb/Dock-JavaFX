package com.yingtai.dock;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class IconImage {
    public static Image getIconImage(String realPath) throws IOException {
        String imgPath="Dock/src/main/resources/com/yingtai/dock/img/";
        new ProcessBuilder("Dock/src/main/resources/com/yingtai/dock/tool/IconExtractor.exe",realPath,imgPath).start();

        int end = realPath.length() - 1;
        while (realPath.charAt(end) != '.') end--;
        int start = end;
        while(realPath.charAt(start)!='\\'&&realPath.charAt(start)!='/') start--;
        String name = realPath.substring(start + 1, end);

        String imgP=imgPath+name+".png";
//        Image image = new Image(new FileInputStream(imgP));
        Image image = new Image(new FileInputStream(imgP));
        return image;
    }

    public static void main(String[] args) {
        String realPath="D:\\steam\\steam.exe";
        try {
            Image image = getIconImage(realPath);
//            File f = new File("Dock/src/main/resources/com/yingtai/dock/img/test.png");
//            ImageIO.write(image, "png", f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
