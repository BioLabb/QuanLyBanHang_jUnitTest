package com.qlbh;

import com.config.JDBC;
import com.services.EmployessServices;
import com.services.ProductServices;
import com.store.EmployeesStore;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.Employess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import project.Product;
import project.orderDetails;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class menuAdminController implements Initializable {
    private int id;
    @FXML
    private Label id_other;
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
    @FXML
    private TextField id_product;
    @FXML
    private Label product_name;
    @FXML
    private Label product_price;

    @FXML
    private TableView<orderDetails> order_detail;
    @FXML
    private TableColumn<Product, Integer> id_colum;
    @FXML
    private TableColumn<Product,Integer> name_colum;
    @FXML
    private TableColumn<Product,Integer> quantity_colum;
    @FXML
    private TableColumn<Product,Integer> unit_price_colum;
    private ObservableValue<orderDetails> orderDetailList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int id = (int) (Math.random()* Math.pow(10,5))+ 9* (int)Math.pow(10,5);
        String idString = String.format("%d",id);
       id_other.setText(idString);

       contentTextFieldChange();

    }

    public void initTableView(){
       // id_colum.cellValueFactoryProperty(new PropertyValueFactory<orderDetails,int>())
    }
    void addProductIntoTable(ActionEvent event){

    }
    private void contentTextFieldChange(){
        id_product.textProperty().addListener((observableValue, oldValue, newValue) ->{
            if(id_product.getText().length() > 6){
                id_product.setText(oldValue);

            }
            else if(id_product.getText().length() == 6){
                int id = Integer.parseInt(id_product.getText());
                try {
                    Product product = ProductServices.findProductById(id);
                    if(product != null){
                        product_name.setText(product.getName());
                        product_price.setText(String.valueOf(product.getPrice()));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // -------------BÁN HÀNG-------------


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
               else if(userName.equals(EmployeesStore.getEmployess().getUser())){
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
