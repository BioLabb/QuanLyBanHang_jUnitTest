package com.qlbh;

import com.config.JDBC;
import com.services.EmployessServices;
import com.store.EmployeesStore;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import project.Employess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class menuAdminController{
    @FXML
    private Label lb_maganer;
    @FXML
    private Label lb_user_name;
    @FXML
    private TextField txt_last_name;
    @FXML
    private TextField txt_first_name;
    @FXML
    private DatePicker txt_date;
    @FXML
    private TextField txt_adress;
    @FXML
    private Label lb_nv;
    @FXML
    private TextField txt_user_name;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_phone;
    @FXML
    private TextField txt_email;

//    {
//        lb_user_name.setText(EmployeesStore.getEmployess().getUser());
//        if(EmployeesStore.getEmployess().isMaganer()){
//            lb_maganer.setText("Admin");
//        }
//        else {
//            lb_user_name.setText("Employee");
//        }
//    }

   public void bill(ActionEvent e) throws IOException {
       menuView.OutputBill(e);
   }

   // kiểm tra rỗng
   private boolean validator(String val){
       if(val.isEmpty()){
           ShowAlert.show("Chưa điền đầy đủ thông tin", Alert.AlertType.WARNING);
           return false;
       }
       return true;
   }

   // kiểm tra ngày rỗng
   private boolean validator(LocalDate date){
       if(date == null){
           return false;
       }
       return true;
   }

   private boolean isUserNameEmployee(String user){
       if(user.startsWith("NV"))
           return true;
       return false;
   }

   private boolean isCheckLength(String string,int lengthMin,int lengthMax){
       if(string.length() >= lengthMin && string.length() <= lengthMax)
           return true;
       return false;
   }

   // kiểm tra tài khoản tồn tại hay chưa
   public boolean isUserExist(String userName) throws SQLException {

       Connection connection = JDBC.getCnn();
       PreparedStatement stm = connection.prepareStatement("select user from employees where user = ?");
       stm.setString(1, userName);
       ResultSet rs = stm.executeQuery();
       if(rs.next()){
           return true;
       }
       return false;
   }




   public void addEmployee() throws SQLException {
       String userName= txt_user_name.getText();
       // kiểm tra tài khoản đã tạo hay chưa
       if(!isUserExist(userName)){
           String lastName= txt_last_name.getText();
           String firstName = txt_first_name.getText();
           LocalDate date = txt_date.getValue();
           String adress = txt_adress.getText();
           String passWord = txt_password.getText();
           String phone = txt_phone.getText();
           String email = txt_email.getText();

           // kiểm tra thông tin dã được nhập đầy đủ chưa
           if(validator(lastName) && validator(firstName) && validator(adress) && validator(userName)
                   && validator(passWord) && validator(email) && validator(date)){

               if(!(isCheckLength(userName,6,16))){
                   ShowAlert.show("user từ 8 đến 16 ký tự", Alert.AlertType.INFORMATION);
               }
               else if(!isCheckLength(passWord,6,16)){
                   ShowAlert.show("password từ 8 đến 16 ký tự", Alert.AlertType.INFORMATION);
               }
               else if(passWord.equals(userName)){
                   ShowAlert.show("password không được trùng với username", Alert.AlertType.WARNING);
               }
               else {
                   String nv = lb_nv.getText();
                   String userSignUp = nv + txt_user_name.getText();
                   Employess employess = new Employess(lastName,firstName, Date.valueOf(date),email,phone,adress,
                           userSignUp,passWord,false);
                   EmployessServices.addEmployees(employess);
                   ShowAlert.show("Thêm thành công", Alert.AlertType.INFORMATION);
               }
           }else{
               ShowAlert.show("Chưa điền đầy đủ thông tin", Alert.AlertType.WARNING);
           }
       }
       else{
           ShowAlert.show("tài khoản đã tồn tại", Alert.AlertType.INFORMATION);
       }
   }

   public void goBack(ActionEvent e) throws IOException {
       menuView.nextPage(e,"sign-view.fxml","Sign");
   }

   public void removeEmployee(ActionEvent e) throws IOException {
       menuView.nextPage(e,"remove-employee-view.fxml","xóa nhân viên");
   }
}
