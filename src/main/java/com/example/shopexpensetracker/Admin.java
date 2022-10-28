package com.example.shopexpensetracker;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin {
    public static XSSFRow isPresent(XSSFSheet sheet, String ProductName, double ProductPrice){
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            String ProductNameFind = row.getCell(1).getStringCellValue();
            double ProductPriceFind = row.getCell(2).getNumericCellValue();
            if (Objects.equals(ProductName.toLowerCase().trim(), ProductNameFind.toLowerCase().trim())){
                System.out.println("Name Matching");
                System.out.println(ProductPrice);
                System.out.println(ProductPriceFind);
                System.out.println(ProductPrice == ProductPriceFind);
                if (ProductPrice == ProductPriceFind) {
                    System.out.println("Returning from");
                    System.out.println(i);
                    return row;
                }
            }
        }
        return null;
    }
    public static void AddProduct(String ProductName,double ProductPrice,int ProductStock){
        try {
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
            System.out.println(ProductPrice);
            System.out.println(ProductStock);

            XSSFRow productIsPresent =  isPresent(sheet,ProductName,ProductPrice);

            if(productIsPresent==null){
                System.out.println("Product Is not present");
                int productID = (int) sheet.getRow(lastRow).getCell(0).getNumericCellValue() + 1;
                XSSFRow row = sheet.createRow(lastRow + 1) ;
                row.createCell(0).setCellValue(productID);
                row.createCell(1).setCellValue(ProductName);
                row.createCell(2).setCellValue(ProductPrice);
                row.createCell(3).setCellValue(ProductStock);
            }
            else{
                System.out.println("Product Is present");
                int updateProductStock =  ProductStock + (int) productIsPresent.getCell(3).getNumericCellValue();
                productIsPresent.getCell(3).setCellValue(updateProductStock);
            }

            fis.close();

            //Creating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(file);
            workbook.write(os);

            //Close the workbook and output stream
            workbook.close();
            os.close();

            System.out.println("Excel file has been updated successfully.");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}
