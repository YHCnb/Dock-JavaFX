package com.yingtai.dock;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Path;

public class IconImage {
    public static Image getIconImage(String realPath) throws IOException {
        String imgPath="Dock/src/main/resources/com/yingtai/dock/img/";
        var obj=new ProcessBuilder("Dock/src/main/resources/com/yingtai/dock/tool/IconExtractor.exe",realPath,imgPath).start();
        while(obj.isAlive());
        int end = realPath.length() - 1;
        while (realPath.charAt(end) != '.') end--;
        int start = end;
        while(realPath.charAt(start)!='\\'&&realPath.charAt(start)!='/') start--;
        String name = realPath.substring(start + 1, end);

        String imgP=imgPath+name+".ico";
        Image image = new Image(IconImage.class.getResourceAsStream("img/Explorer.png"));

        return image;
    }

    public static void main(String[] args) throws InterruptedException {
        String realPath="D:\\360极速浏览器X下载\\CLion-2022.2.3.exe";
        try {
            Image image = getIconImage(realPath);
//            File f = new File("Dock/src/main/resources/com/yingtai/dock/img/test.png");
//            ImageIO.write(image, "png", f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
