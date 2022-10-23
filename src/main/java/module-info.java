module com.example.shopexpensetracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;
    requires org.apache.logging.log4j;


    opens com.example.shopexpensetracker to javafx.fxml, org.apache.poi.poi, org.apache.poi.ooxml;
    exports com.example.shopexpensetracker;
}