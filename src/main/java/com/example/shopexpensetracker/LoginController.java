package com.example.shopexpensetracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOError;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    protected ChoiceBox<String> userCheckBox;
    @FXML
    protected PasswordField passwordField;
    @FXML
    protected TextField emailField;
    @FXML
    protected Button LoginButton;
    @FXML
    protected void onLoginButtonClick() throws IOError {
        String email = emailField.getText();
        String password = passwordField.getText();
        String userType = userCheckBox.getValue();
        System.out.print(email+password+userType+"\n");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> names = FXCollections.observableArrayList("Admin", "Employee");
        userCheckBox.setValue(names.get(0));
        userCheckBox.setItems(names);
    }
}