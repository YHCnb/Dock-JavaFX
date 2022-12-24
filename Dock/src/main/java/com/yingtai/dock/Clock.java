package com.yingtai.dock;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock extends Icon {
    public Clock() {
        super();

        Rectangle rectangle = new Rectangle();
        rectangle.setArcHeight(25);
        rectangle.setArcWidth(25);
        rectangle.widthProperty().bind(Parament.iconEnlargedWidth);
        rectangle.heightProperty().bind(Parament.iconEnlargedWidth);
        rectangle.setFill(Color.GRAY);

        Circle circle = new Circle();
        circle.centerXProperty().bind(Parament.iconEnlargedWidth.divide(2));
        circle.centerYProperty().bind(Parament.iconEnlargedWidth.divide(2));
        circle.radiusProperty().bind(Parament.iconEnlargedWidth.multiply(0.4));
        circle.setFill(Color.WHITE);

        Line lineSecond = new Line();
        lineSecond.setStroke(Color.RED);
        lineSecond.setStrokeWidth(2);
        lineSecond.startXProperty().bind(Parament.iconEnlargedWidth.divide(2));
        lineSecond.startYProperty().bind(Parament.iconEnlargedWidth.divide(2));
        Line lineMinute = new Line();
        lineMinute.setStrokeWidth(2.5);
        lineMinute.setStroke(Color.GREEN);
        lineMinute.startXProperty().bind(Parament.iconEnlargedWidth.divide(2));
        lineMinute.startYProperty().bind(Parament.iconEnlargedWidth.divide(2));
        Line lineHour = new Line();
        lineHour.setStroke(Color.GREY);
        lineHour.setStrokeWidth(3);
        lineHour.startXProperty().bind(Parament.iconEnlargedWidth.divide(2));
        lineHour.startYProperty().bind(Parament.iconEnlargedWidth.divide(2));

        new Thread(() -> {
            while (true) {
                switch (Parament.dateModel.get()){
                    case 0:
                        tag = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日EEEE"));
                        break;
                    case 1:
                        tag = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
                        break;
                    case 2:
                        tag = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
                        break;
                    case 3:
                        tag = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                        break;
                }
                LocalTime localTime = LocalTime.now();
                double secondLength = Parament.iconEnlargedWidth.get() * 0.35;
                double minuteLength = Parament.iconEnlargedWidth.get() * 0.3;
                double hourLength = Parament.iconEnlargedWidth.get() * 0.25;
                lineSecond.setEndX(Parament.iconEnlargedWidth.get() / 2 + secondLength * Math.sin(6 * localTime.getSecond() / 180.0 * Math.PI));
                lineSecond.setEndY(Parament.iconEnlargedWidth.get() / 2 - secondLength * Math.cos(6 * localTime.getSecond() / 180.0 * Math.PI));
                lineMinute.setEndX(Parament.iconEnlargedWidth.get() / 2 + minuteLength * Math.sin(6 * localTime.getMinute() / 180.0 * Math.PI));
                lineMinute.setEndY(Parament.iconEnlargedWidth.get() / 2 - minuteLength * Math.cos(6 * localTime.getMinute() / 180.0 * Math.PI));
                lineHour.setEndX(Parament.iconEnlargedWidth.get() / 2 + hourLength * Math.sin(30 * localTime.getHour() / 180.0 * Math.PI));
                lineHour.setEndY(Parament.iconEnlargedWidth.get() / 2 - hourLength * Math.cos(30 * localTime.getHour() / 180.0 * Math.PI));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        group.getChildren().addAll(rectangle, circle, lineSecond, lineMinute, lineHour);
    }
}
