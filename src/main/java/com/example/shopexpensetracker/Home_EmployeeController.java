package com.example.shopexpensetracker;

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

public class Home_EmployeeController implements Initializable {
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
    public void toSell () throws IOException {
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("product-sell.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void toRequestProduct () throws IOException {
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("product-request.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void toReport () throws IOException {
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("report.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void toSalary () throws IOException {
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("salary-employee.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    public void toAddProduct () throws IOException {
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("add-product.fxml")));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

}