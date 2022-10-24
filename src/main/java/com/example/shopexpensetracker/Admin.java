package com.example.shopexpensetracker;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Admin {
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
            XSSFRow row = sheet.createRow(lastRow + 1) ;

            int productID = (int) sheet.getRow(lastRow).getCell(0).getNumericCellValue() + 1;

            row.createCell(0).setCellValue(productID);
            row.createCell(1).setCellValue(ProductName);
            row.createCell(2).setCellValue(ProductPrice);
            row.createCell(3).setCellValue(ProductStock);

            fis.close();

            //Creating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(file);
            workbook.write(os);

            //Close the workbook and output stream
            workbook.close();
            os.close();

            System.out.println("Excel file has been updated successfully.");
            Helper.showModal("Successful","Product has been added successfully.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
