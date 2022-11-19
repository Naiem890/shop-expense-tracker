package com.example.shopexpensetracker;

import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class paySalaryController implements Initializable {

    public TableColumn<Employee,Integer> employeeID;
    public TableColumn<Employee,String> employeeName;
    public TableColumn<Employee,String> employeeEmail;
    public TableColumn<Employee, String> employeePaid;
    public TableColumn<Employee,String> employeeAction;
    public TableView<Employee> salaryTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadEmployeeTable();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadEmployeeTable() throws IOException, ParseException {
        employeeID.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        employeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        employeeEmail.setCellValueFactory(new PropertyValueFactory<>("employeeEmail"));
        employeePaid.setCellValueFactory(new PropertyValueFactory<>("employeePaid"));

        //add cell of button edit
        Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellFactory = (TableColumn<Employee, String> param) -> {
            // make cell containing buttons
            final TableCell<Employee, String> cell = new TableCell<Employee, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {
                        Button paySalary = new Button("Pay");
                        paySalary.setStyle("-fx-background-color: #ff9100");
                        paySalary.setCursor(Cursor.HAND);

                        paySalary.setOnMouseClicked((MouseEvent event) -> {

                        });

                        HBox btnWrapper = new HBox(paySalary);
                        btnWrapper.setStyle("-fx-alignment:center");
                        setGraphic(btnWrapper);
                    }
                    setText(null);
                }
            };
            return cell;
        };
        employeeAction.setCellFactory(cellFactory);
        salaryTable.setItems(Admin.getAllEmployee());
    }
}