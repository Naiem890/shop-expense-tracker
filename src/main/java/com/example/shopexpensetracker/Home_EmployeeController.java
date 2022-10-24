package com.example.shopexpensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home_EmployeeController implements Initializable {
    public Button logoutBtn;
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
            Helper.changeScreenAt(contentArea, "product-list.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toProductList () throws IOException {
        Helper.changeScreenAt(contentArea, "product-list.fxml");
    }
    public void toSell () throws IOException {
        Helper.changeScreenAt(contentArea, "product-sell.fxml");
    }
    public void toRequestProduct () throws IOException {
        Helper.changeScreenAt(contentArea, "product-request.fxml");
    }
    public void toReport () throws IOException {
        Helper.changeScreenAt(contentArea, "report.fxml");
    }
    public void toSalary () throws IOException {
        Helper.changeScreenAt(contentArea, "salary-employee.fxml");
    }
    public void toHome(ActionEvent event) throws IOException {
        Helper.changeCurrentScreen(event, "login.fxml");
    }

}