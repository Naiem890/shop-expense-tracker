package com.example.shopexpensetracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Admin {

    public static void AddProduct(String ProductName,double ProductBuyPrice,double ProductSellPrice,int ProductQuantity) throws IOException {

        File file = new File("src/main/resources/data/Product.xlsx");
        System.out.println(file.getAbsolutePath());

        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        System.out.println(workbook);
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet);

        int lastRow = sheet.getLastRowNum();
        System.out.println(lastRow);
        System.out.println(ProductName);
        System.out.println(ProductBuyPrice);
        System.out.println(ProductSellPrice);
        System.out.println(ProductQuantity);

        XSSFRow productIsPresent =  Common.isPresent(sheet,ProductName);

        if(productIsPresent==null){
            System.out.println("Product Is not present");
            int productID = (int) sheet.getRow(lastRow).getCell(0).getNumericCellValue() + 1;
            XSSFRow row = sheet.createRow(lastRow + 1) ;
            row.createCell(0).setCellValue(productID);
            row.createCell(1).setCellValue(ProductName);
            row.createCell(2).setCellValue(ProductSellPrice);
            row.createCell(3).setCellValue(ProductQuantity);
        }
        else{
            System.out.println("Product Is present");
            int updateProductStock =  ProductQuantity + (int) productIsPresent.getCell(3).getNumericCellValue();
            productIsPresent.getCell(2).setCellValue(ProductSellPrice);
            productIsPresent.getCell(3).setCellValue(updateProductStock);
        }

        fis.close();

        //Creating output stream and writing the updated workbook
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);

        //Close the workbook and output stream
        workbook.close();
        os.close();

        System.out.println("Product excel file has been updated successfully.");

        String reportTitle = "Bought (" + ProductName + ")" + " X " + ProductQuantity;
        double reportAmount = ProductQuantity * ProductBuyPrice * -1;
        Common.addReport(reportTitle,reportAmount);

    }

    public static List<Product> getThirdPartyProduct() throws IOException {
        List <Product> products = new ArrayList<>();

        File file = new File("src/main/resources/data/ThirdPartyProduct.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet);
        System.out.println(workbook);

        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            int ProductID = (int) row.getCell(0).getNumericCellValue();
            String ProductName = row.getCell(1).getStringCellValue();
            double ProductPrice = row.getCell(2).getNumericCellValue();
            int ProductStock = (int) row.getCell(3).getNumericCellValue();
            products.add(new Product(ProductID,ProductName,ProductPrice,ProductStock));
        }

        fis.close();
        return products;
    }

    public static ObservableList<Bill> getBills() throws IOException {
        ObservableList <Bill> billList = FXCollections.observableArrayList();
        File file = new File("src/main/resources/data/Report.xlsx");
        System.out.println(file.getAbsolutePath());
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        System.out.println(workbook);
        XSSFSheet sheet = workbook.getSheet("bill-names");
        System.out.println(sheet);
        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            String billName = row.getCell(0).getStringCellValue();
            billList.add(new Bill(billName));
        }
        return billList;
    }

    public static void addBill(String billName) throws IOException {
        File file = new File("src/main/resources/data/Report.xlsx");
        System.out.println(file.getAbsolutePath());

        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        System.out.println(workbook);
        XSSFSheet sheet = workbook.getSheet("bill-names");
        System.out.println(sheet);

        int lastRow = sheet.getPhysicalNumberOfRows();
        System.out.println(lastRow);
        System.out.println(billName);

        XSSFRow row = sheet.createRow(lastRow);
        row.createCell(0).setCellValue(billName);

        fis.close();

        //Creating output stream and writing the updated workbook
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);

        //Close the workbook and output stream
        workbook.close();
        os.close();

        System.out.println("Bill excel file has been updated successfully.");
    }
    public static void deleteProduct(String ProductName) throws IOException {
        File file = new File("src/main/resources/data/Product.xlsx");
        System.out.println(file.getAbsolutePath());

        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        System.out.println(workbook);
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet);
        XSSFRow productIsPresent =  Common.isPresent(sheet,ProductName);
        int totalNoOfRows = sheet.getLastRowNum();
        System.out.println(totalNoOfRows);
        if(productIsPresent != null){
            int rowIndex = productIsPresent.getRowNum();
            if (rowIndex >= 0 && rowIndex < totalNoOfRows) {
                sheet.shiftRows(rowIndex + 1, totalNoOfRows, -1);
            }
            if (rowIndex == totalNoOfRows) {
                sheet.removeRow(productIsPresent);
            }
        }

        fis.close();

        //Creating output stream and writing the updated workbook
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);

        //Close the workbook and output stream
        workbook.close();
        os.close();
    }

    public static void editProduct(Product product, String productName, int productStock, double productPrice) throws IOException {
        File file = new File("src/main/resources/data/Product.xlsx");
        System.out.println(file.getAbsolutePath());

        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        System.out.println(workbook);
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet);
        XSSFRow productIsPresent =  Common.isPresent(sheet,product.getProductName());

        if(productIsPresent != null){
            productIsPresent.getCell(1).setCellValue(productName);
            productIsPresent.getCell(2).setCellValue(productPrice);
            productIsPresent.getCell(3).setCellValue(productStock);
        }

        fis.close();

        //Creating output stream and writing the updated workbook
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);

        //Close the workbook and output stream
        workbook.close();
        os.close();
    }
}
