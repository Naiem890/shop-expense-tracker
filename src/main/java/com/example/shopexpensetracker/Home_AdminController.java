package com.example.shopexpensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Home_AdminController implements Initializable {
    @FXML
    protected Button addProductBtn;
    @FXML
    protected Button productListBtn;
    @FXML
    protected Button requestProductBtn;
    @FXML
    protected Button sellBtn;
    @FXML
    protected Button mySalaryBtn;
    @FXML
    protected Button reportBtn;
    @FXML
    protected StackPane contentArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("product-list.fxml")));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toProductList () throws IOException {
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("product-list.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void toReport () throws IOException {
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("report.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void toAddProduct () throws IOException {
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-product.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void toPaySalary() throws IOException{
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pay-salary.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void toBuy() throws IOException{
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("buy-product.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public void toPayBills() throws IOException{
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pay-bills.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}