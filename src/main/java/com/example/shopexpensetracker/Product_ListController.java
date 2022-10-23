package com.example.shopexpensetracker;

import javafx.fxml.Initializable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Product_ListController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            File file = new File("src/main/resources/data/Product.xlsx");
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            System.out.println(sheet);
            System.out.println(workbook);
            String cellvalue = sheet.getRow(0).getCell(0).getStringCellValue();
            System.out.println(cellvalue);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}