package com.qlbh;

import com.config.JDBC;
import com.project.OrderDetails;
import com.services.EmployessServices;
import com.services.ProductServices;
import com.services.OrderDetailsServices;
import com.store.EmployeesStore;
import com.store.TableOrderDetailStore;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.project.Employess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.project.Product;

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
    private Label id_order_detail;
    @FXML
    private TextField id_product;
    @FXML
    private Label product_name;
    @FXML
    private Label product_price;
    @FXML
    private TextField product_quantity;
    @FXML
    private Label pay_order_detail;
    @FXML
    private TextField cus_pay;
    @FXML
    private Label change;

    @FXML
    private TableView<TableOrderDetail> order_detail;
    @FXML
    private TableColumn<TableOrderDetail, Integer> stt_colum;
    @FXML
    private TableColumn<TableOrderDetail, Integer> product_id_colum;
    @FXML
    private TableColumn<TableOrderDetail,String> product_name_colum;
    @FXML
    private TableColumn<TableOrderDetail,Integer> product_quantity_colum;
    @FXML
    private TableColumn<TableOrderDetail,Integer> unit_price_colum;
    @FXML
    private TableColumn<TableOrderDetail,Integer> total_colum;
    private ObservableList<TableOrderDetail> orderDetailList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String Access = "";
        if(EmployeesStore.getEmployess().isMaganer()){
            Access = "Admin";
        }else {
            Access = "Employees";
        }
        lb_maganer.setText(Access);
        lb_user_name.setText(String.format("%s %s",EmployeesStore.getEmployess().getFirstName(),EmployeesStore.getEmployess().getLastName()));


        // tạo mã đơn hàng
       int id = (int) (Math.random()* Math.pow(10,5))+ 9* (int)Math.pow(10,5);
       String idString = String.format("%d",id);
       id_order_detail.setText(idString);
       contentTextFieldChange(6);
       pay_change();
       initTableView();
    }

    // -------------BÁN HÀNG-------------

    public void initTableView(){

        stt_colum.setCellValueFactory(new PropertyValueFactory<>("stt"));
        product_id_colum.setCellValueFactory(new PropertyValueFactory<>("productID"));
        product_name_colum.setCellValueFactory(new PropertyValueFactory<>("productName"));
        product_quantity_colum.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        unit_price_colum.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        total_colum.setCellValueFactory(new PropertyValueFactory<>("total"));

        order_detail.setItems(TableOrderDetailStore.getTableOrderDetailsList());
    }
    // thêm item vào table
    public void addOrderIntoTable(ActionEvent event){
        int orderId = Integer.parseInt(id_order_detail.getText());
        int productID = Integer.parseInt(id_product.getText());
        String productName = product_name.getText();

        int productQuantity = Integer.parseInt(product_quantity.getText());
        double orderPrice = Double.parseDouble(product_price.getText());
        double total = productQuantity* orderPrice;
        TableOrderDetail tableOrderDetail = new TableOrderDetail(orderId,productID,productName,productQuantity,orderPrice,total);
        //tableOrderDetail.setId(orderDetailList.size()+1);  // sô thứ tự
//        orderDetailList.add(tableOrderDetail);
        TableOrderDetailStore.addList(tableOrderDetail);
        order_detail.setItems(TableOrderDetailStore.getTableOrderDetailsList());

        pay_order_detail.setText(String.valueOf(totalPay()));
    }

    public void buy(ActionEvent event) throws SQLException, IOException {
        // đưa đơn hàng vào cơ sở dữ liệu
        for (TableOrderDetail value: TableOrderDetailStore.getTableOrderDetailsList()) {
            TableOrderDetailStore.setId(Integer.parseInt(id_order_detail.getText()));
            Date date = Date.valueOf(LocalDate.now());
            TableOrderDetailStore.setDate(date);

            TableOrderDetailStore.setPay(Double.parseDouble(pay_order_detail.getText()));
            TableOrderDetailStore.setChange(Double.parseDouble(change.getText()));


            OrderDetails orderDetails = new OrderDetails(value.getOrderID(),value.getProductID(), value.getProductQuantity(), value.getTotal(),date);
            OrderDetailsServices.addOrderDetail(orderDetails);
        }
        menuView.nextPage(event,"bill-view.fxml","Hóa đơn");
    }
    // Giới hạn ký tự trong textFile
    private void contentTextFieldChange(int length){
        id_product.textProperty().addListener((observableValue, oldValue, newValue) ->{
            if(id_product.getText().length() > length){
                id_product.setText(oldValue);

            }
            else if(id_product.getText().length() == length){
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

    private void pay_change(){
        cus_pay.textProperty().addListener((observable,oldVal,newVal)->{
            double pay = Double.parseDouble(pay_order_detail.getText());
            double cus_money = Double.parseDouble(cus_pay.getText());
            double moneyOfCus;
            if(cus_money > pay){
                moneyOfCus = cus_money - pay;
                change.setText(String.format("%.3f",moneyOfCus));
            }
        });
    }

    public double totalPay(){
        double sum = 0;
        for(TableOrderDetail tb : TableOrderDetailStore.getTableOrderDetailsList()){
            sum+= tb.getTotal();
        }
        return sum;
    }



    public void bill(ActionEvent e) throws IOException {
       menuView.OutputBill(e);
   }

// ------------ THÊM NHÂN VIÊN-------------
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
       if(!isUserExist("NV"+userName)){
           String lastName= txt_last_name.getText();
           String firstName = txt_first_name.getText();
           LocalDate date = txt_date.getValue();
           String adress = txt_adress.getText();
           String passWord = txt_password.getText();
           String phone = txt_phone.getText();
           String email = txt_email.getText();

           // kiểm tra thông tin dã được nhập đầy đủ chưa
           if(validator(lastName) && validator(firstName) && validator(adress) && validator(userName)
                   && validator(passWord) && validator(email)){
               if(!(isCheckLength(userName,6,16))){
                   ShowAlert.show("user từ 8 đến 16 ký tự", Alert.AlertType.INFORMATION);
               }
               else if(!isCheckLength(passWord,6,16)){
                   ShowAlert.show("password từ 8 đến 16 ký tự", Alert.AlertType.INFORMATION);
               }
               else if(userName.equals(EmployeesStore.getEmployess().getUser())){
                   ShowAlert.show("password không được trùng với username", Alert.AlertType.WARNING);
               }else if(!validator(date)){
                   ShowAlert.show("Ngày tháng không hợp lê", Alert.AlertType.WARNING);
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
