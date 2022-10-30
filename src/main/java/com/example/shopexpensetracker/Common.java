package com.example.shopexpensetracker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public static ObservableList<Product> getAllProduct() throws IOException {
        File file = new File("src/main/resources/data/Product.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet);
        System.out.println(workbook);

        ObservableList<Product> products = FXCollections.observableArrayList();
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
    public static ObservableList<Report> getReport() throws IOException, ParseException {
        String date = formatter.format(new Date(0L));
        return getReport(date);
    }
    public static ObservableList<Report> getReport(String dateLimit) throws IOException, ParseException {
        System.out.println("dateLimit");
        System.out.println(dateLimit);
        File file = new File("src/main/resources/data/Report.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("report-sheet");
        System.out.println(sheet);
        System.out.println(workbook);

        ObservableList<Report> reports = FXCollections.observableArrayList();
        int rowCount = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowCount; i++) {
            XSSFRow row = sheet.getRow(i);
            String date = new CellDateFormatter("MM/dd/yyyy").format(row.getCell(0).getDateCellValue());
            String reportTitle = row.getCell(1).getStringCellValue();
            double reportPrice = row.getCell(2).getNumericCellValue();
            Date recordDate = formatter.parse(date);
            Date limitDate = formatter.parse(dateLimit);
            if(!recordDate.before(limitDate)){
                reports.add(new Report(date,reportTitle,reportPrice));
            }
        }

        fis.close();
        return reports;
    }
}
