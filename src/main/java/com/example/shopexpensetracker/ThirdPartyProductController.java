package com.example.shopexpensetracker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class ThirdPartyProductController {
    public Button buyProductBtn;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label stockLabel;
    @FXML
    private Button buProductBtn;

    private Product product;

    public void setData(Product product) {
        this.product = product;
//        this.myListener = myListener;
        productNameLabel.setText(product.getProductName());
        priceLabel.setText("Product Price:" + product.getProductPrice());
        stockLabel.setText("Available:" + product.getProductStock());
    }

    public void toBuyProductFinal() throws IOException {
        Home_AdminController.getInstance().toBuyProductFinal(this.product);
    }
}