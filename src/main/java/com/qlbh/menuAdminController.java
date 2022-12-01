package com.qlbh;

import com.services.EmployessServices;
import javafx.scene.control.Alert;
import project.Employess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class menuAdminController extends menuView {
    @FXML
    private TextField txt_last_name;
    @FXML
    private TextField txt_first_name;
    @FXML
    private DatePicker txt_date;
    @FXML
    private TextField txt_adress;
    @FXML
    private TextField txt_user_name;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_phone;
    @FXML
    private TextField txt_email;

   public void bill(ActionEvent e) throws IOException {
       this.OutputBill(e);
   }

   public void addEmployee() throws SQLException {
       String lastName= txt_last_name.getText();
       String firstName = txt_first_name.getText();
       LocalDate date = txt_date.getValue();
       String adress = txt_adress.getText();
       String userName= txt_user_name.getText();
       String passWord = txt_password.getText();
       String phone = txt_phone.getText();
       String email = txt_email.getText();

       Employess employess = new Employess(lastName,firstName, Date.valueOf(date),email,phone,adress,userName,passWord,false);
       EmployessServices.addEmployees(employess);

//       ShowAlert.show("Thêm thành công", Alert.AlertType.INFORMATION);
   }

   public void goBack(ActionEvent e) throws IOException {
       this.nextPage(e,"sign-view.fxml","Sign");
   }

   public void removeEmployee(ActionEvent e) throws IOException {
       this.nextPage(e,"remove-employee-view.fxml","xóa nhân viên");
   }
}
