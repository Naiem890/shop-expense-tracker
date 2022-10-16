package com.example.shopexpensetracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    protected void onLoginButtonClick(ActionEvent event) throws IOError, IOException {
        String email = emailField.getText();
        String password = passwordField.getText();
        String userType = userCheckBox.getValue();
        System.out.print(email+password+userType+"\n");
        if(Objects.equals(userType, "Employee")){
            if(Objects.equals(email, "employee@shop.com") && Objects.equals(password, "password")){
                changeScreen(event,"home_employee.fxml");
            }
        }
        else {
            showModal(event,"Login - Error","Wrong Credential!!!\nEnter Again");
        }
    }

    private void showModal(ActionEvent event,String ModalType, String modalMessage) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(ModalType);
        dialog.setContentText(modalMessage);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
        System.out.print(modalMessage);
    }

    private void changeScreen(ActionEvent event, String PageFXML) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PageFXML));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> names = FXCollections.observableArrayList("Admin", "Employee");
        userCheckBox.setValue(names.get(0));
        userCheckBox.setItems(names);
    }
}