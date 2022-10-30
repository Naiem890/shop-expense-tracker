package com.example.shopexpensetracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class Helper {
    public static void showModal(String ModalTitle, String modalMessage) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(ModalTitle);
        dialog.setContentText(modalMessage);
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
        System.out.println(modalMessage);
    }

    public static void changeCurrentScreen(ActionEvent event, String PageFXML) throws IOException {
        FXMLLoader loader = new FXMLLoader(Helper.class.getResource(PageFXML));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void changeScreenAt(StackPane contentArea, String FXMLFileName) throws IOException{
        Parent fxml =  FXMLLoader.load(Objects.requireNonNull(Helper.class.getResource(FXMLFileName)));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    public static void addReport( String reportTitle, double reportAmount) throws IOException {
        Date now = new Date();
        File file = new File("src/main/resources/data/Report.xlsx");
        System.out.println(file.getAbsolutePath());

        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        System.out.println(workbook);
        XSSFSheet sheet = workbook.getSheet("report-sheet");
        System.out.println(sheet);

        int lastRow = sheet.getLastRowNum();
        System.out.println(lastRow);
        System.out.println(now);
        System.out.println(reportTitle);
        System.out.println(reportAmount);

        XSSFRow row = sheet.createRow(lastRow + 1) ;
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat((short)14);
        row.createCell(0).setCellStyle(cellStyle);
        row.getCell(0).setCellValue(now);
        row.createCell(1).setCellValue(reportTitle);
        row.createCell(2).setCellValue(reportAmount);

        fis.close();

        //Creating output stream and writing the updated workbook
        FileOutputStream os = new FileOutputStream(file);
        workbook.write(os);

        //Close the workbook and output stream
        workbook.close();
        os.close();

    }
}
