package com.yingtai.dock;

import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import net.sf.image4j.codec.ico.ICODecoder;

import javax.imageio.ImageIO;

public class IconImage extends App {
    public static Image getIconImage(String realPath) {
        String imgPath = "../config/img/";
        File file = new File(imgPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Process obj = null;
        try {
            obj = new ProcessBuilder(IconImage.class.getResource("tool/IconExtractor.exe").getPath(), realPath, imgPath).start();
        } catch (IOException e) {
            System.out.println("IconExtractor异常");
            throw new RuntimeException(e);
        }
        while (obj.isAlive()) ;

        int end = realPath.length() - 1;
        while (realPath.charAt(end) != '.') end--;
        int start = end;
        while (realPath.charAt(start) != '\\' && realPath.charAt(start) != '/') start--;
        String name = realPath.substring(start + 1, end);

        String imgP = imgPath + name + ".png";

        List<BufferedImage> images = null;
        try {
            images = ICODecoder.read(new File(imgPath + name + ".ico"));
            ImageIO.write(images.get(0), "png", new File(imgP));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image image;
        try {
            image = new Image("file:" + new File(imgP).getCanonicalPath());
            System.out.println(new File(imgP).getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return image;
    }

}