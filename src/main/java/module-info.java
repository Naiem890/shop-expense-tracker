module com.example.shopexpensetracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shopexpensetracker to javafx.fxml;
    exports com.example.shopexpensetracker;
}