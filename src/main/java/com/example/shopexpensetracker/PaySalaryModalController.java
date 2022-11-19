package com.example.shopexpensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PaySalaryModalController implements Initializable {
    public Label employeeName;
    public Label employeeEmail;
    public Label employeeLastPaid;
    public TextField slaryAmountField;
    public Button payBtn;
    public Button closeButton;
    public AnchorPane paySalaryModal;
    private Employee selectedEmployee;

    public void setData(Employee employee){
        this.selectedEmployee = employee;
        employeeName.setText(employee.getEmployeeName());
        employeeEmail.setText(employee.getEmployeeEmail());
        employeeLastPaid.setText(employee.getLastPaidDate());
    }

    public void closeModal(){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Platform.runLater( () -> paySalaryModal.requestFocus() );
    }

    public void onSave() throws IOException {
//        System.out.println(productName.getText());
//        System.out.println(productStock.getText());
//        System.out.println(productPrice.getText());
//        String name = productName.getText();
//        int stock = Integer.parseInt(productStock.getText());
//        double price = Double.parseDouble(productPrice.getText());
//        Admin.editProduct(selectedProduct,name,stock,price);
//        Product_ManageController.getInstance().refreshData();
//        closeModal();
    }

    public void onPay(ActionEvent actionEvent) {
    }
}
