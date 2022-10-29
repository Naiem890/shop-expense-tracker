package com.example.shopexpensetracker;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SellProductController implements Initializable {

    public ChoiceBox<String> productList;
    public Label productName;
    public Label productPrice;
    public Label productStock;
    public TextField productSellQuantity;
    public Label totalPrice;
    public ObservableList<Product> products;
    public Product product;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            products = Common.getAllProduct();
            ObservableList<String> list = FXCollections.observableArrayList();
            for (Product product:products) {
                list.add("ID: "+product.getProductID()+" - "+product.getProductName());
            }
            productList.setValue("ID: "+products.get(0).getProductID()+" - "+products.get(0).getProductName());
            setData(products.get(0));
            this.product = products.get(0);
            productList.setItems(list);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void calclulatePrice(KeyEvent keyEvent) {
        if(!NumberUtils.isCreatable(keyEvent.getText())){
            Helper.showModal("Wrong Input","Input is not a number \nEnter Again");
            productSellQuantity.setText("");
        }
        else{
            int productOrder = Integer.parseInt(productSellQuantity.getText());
            double finalPrice = productOrder * product.getProductPrice();
            totalPrice.setText("$ "+ finalPrice);
        }
    }

    public void sellProduct(ActionEvent actionEvent) {
        if(productSellQuantity.getText().isEmpty()){
            Helper.showModal("Empty Input","Input is empty \nEnter Again");
            productSellQuantity.setText("");
        }
        else if(!NumberUtils.isCreatable(productSellQuantity.getText())){
            Helper.showModal("Wrong Input","Input is not a number \nEnter Again");
            productSellQuantity.setText("");
        }
        else {
            if(Integer.parseInt(productSellQuantity.getText())>product.getProductStock()){
                Helper.showModal("Wrong Input","Order amount should be minimum than stock amount \nEnter Again");
            }
            else{
                Admin.AddProduct(product.getProductName(),product.getProductPrice(),product.getProductStock());
                Helper.showModal("Selling Successful",
                        "Product Name: " +product.getProductName()+"\n"
                                +"Sell Amount: " +productSellQuantity.getText()+"\n"
                                +"Sell Price Per Piece: $" +product.getProductPrice()+"\n"
                                +"Total Price: " +totalPrice.getText()+"\n");
            }
            productSellQuantity.setText("");
        }
    }
    public Product findProduct(int ID) {
        System.out.println(ID);
        for (Product product: products) {
            if(product.getProductID()==ID){
                return product;
            }
        }
        return null;
    }
    public void onSelectProduct(ActionEvent actionEvent) {
        int productSelectID = Integer.parseInt(productList.getValue().split(" ")[1]); // for selecting ID
        Product p = findProduct(productSelectID);

        if(p!=null){
            setData(p);
            this.product = p;
        }
    }

    private void setData(Product p) {
        productName.setText(p.getProductName());
        productPrice.setText("$"+ p.getProductPrice());
        productStock.setText(String.valueOf(p.getProductStock()));
    }
}
