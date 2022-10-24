package com.example.shopexpensetracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home_Admin extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home_Admin.class.getResource("home-admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Shop Expense Tracker");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}