module com.yingtai.dock {
    requires javafx.controls;
    requires javafx.fxml;
    requires AppTools;

    requires java.datatransfer;
    requires java.desktop;

    opens com.yingtai.dock to javafx.fxml;
    exports com.yingtai.dock;
}