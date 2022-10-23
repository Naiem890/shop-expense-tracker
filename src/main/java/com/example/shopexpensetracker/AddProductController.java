package com.example.shopexpensetracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.math.NumberUtils;


public class AddProductController {

    @FXML
    private Button addProduct;
    @FXML
    private TextField addProductName;
    @FXML
    private TextField addProductPrice;
    @FXML
    private TextField addProductStock;
    @FXML
    protected void onAddProduct() {

        if(addProductPrice.getText().isEmpty() || addProductName.getText().isEmpty() || addProductStock.getText().isEmpty()){
            Helper.showModal("Wrong Input","Empty Input \nEnter Again");
        }
        else if(!NumberUtils.isCreatable(addProductPrice.getText()) || !NumberUtils.isCreatable(addProductStock.getText())){
            Helper.showModal("Wrong Input","Invalid Number \nEnter Again");
        }
        else {
            String ProductName = addProductName.getText();
            double ProductPrice = Double.parseDouble(addProductPrice.getText());
            int ProductStock = Integer.parseInt(addProductStock.getText());
            System.out.println(ProductName);
            System.out.println(ProductPrice);
            System.out.println(ProductStock);

            Admin.AddProduct(ProductName,ProductPrice,ProductStock);


        }
        //Clearing the input boxes
        addProductName.setText("");
        addProductPrice.setText("");
        addProductStock.setText("");
    }
}