package com.qlbh;

import com.config.JDBC;
import com.services.EmployessServices;
import com.services.ProductServices;
import com.store.EmployeesStore;

import javafx.beans.value.ObservableValue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.Employess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import project.Product;
import project.orderDetails;
import table.OrderTable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class menuAdminController implements Initializable {
    @FXML
    private Label PriceOut;
    private double priceout = 0;
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
    private TableView<orderDetails> order_detail;
    @FXML
    private TableColumn<Product, Integer> product_id_colum;
    @FXML
    private TableColumn<Product,Integer> product_name_colum;
    @FXML
    private TableColumn<Product,Integer> product_quantity_colum;
    @FXML
    private TableColumn<Product,Integer> unit_price_colum;
    private ObservableValue<orderDetails> orderDetailList;
    @FXML
    private TableView<dt> tbdt;
    @FXML
    private TableView<product> table;
    @FXML
    private TableColumn<product, Integer> numberColumn;
    @FXML
    private TableColumn<product, String > IDcolumn;
    @FXML
    private TableColumn<product, String> nameColumn;
    @FXML
    private TableColumn<product, Integer> amountColumn;
    @FXML
    private TableColumn<product, String> dvColumn;
    @FXML
    private TableColumn<product, Double> priceColumn;
    @FXML
    private TableColumn<product, Double> thanhTienColumn;
    @FXML
    private TableColumn<product, Double> buyColumn;
    @FXML
    private TableColumn<product, Date> Dateh;

    private ObservableList<product> productList;

    @FXML
    private DatePicker Dateta;

    ObservableList<product> proList = FXCollections.observableArrayList();
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    product product = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // tạo mã đơn hàng
        int id = (int) (Math.random()* Math.pow(10,5))+ 9* (int)Math.pow(10,5);
        String idString = String.format("%d",id);
//       id_order_detail.setText(idString);
//       initTableView();
//       contentTextFieldChange(6);
        try {
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sumprice();


        Dateta.setValue(LocalDate.now());

        BillView.Datetah = Dateta;
        BillView.usern = lb_user_name.getText();
        BillView.priceOut = Double.parseDouble(PriceOut.getText());

    }
    // -------------BÁN HÀNG-------------

    public void initTableView(){
        product_id_colum.setCellValueFactory(new PropertyValueFactory<>("productID"));
        product_name_colum.setCellValueFactory(new PropertyValueFactory<>("productName"));
        product_quantity_colum.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
    }

    public void loadTable() throws SQLException {
        connection = JDBC.getCnn();
        refreshtable();

        numberColumn.setCellValueFactory(new PropertyValueFactory<product, Integer>("Number"));
        IDcolumn.setCellValueFactory(new PropertyValueFactory<product, String>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<product, String>("nameProduct"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<product, Integer>("amount"));
        dvColumn.setCellValueFactory(new PropertyValueFactory<product, String>("dv"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<product, Double>("gia"));
        thanhTienColumn.setCellValueFactory(new PropertyValueFactory<product, Double>("thanhTien"));
        Dateh.setCellValueFactory(new PropertyValueFactory<product, Date>("Dateh"));
    }

    @FXML
    private void refreshtable() throws SQLException {
        try {
            proList.clear();
            query = "SELECT * FROM testtable";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                proList.add(new product(resultSet.getInt("STT"), resultSet.getString("Mã Sản Phẩm"), resultSet.getString("Tên Sản Phẩm"),
                        resultSet.getInt("Số lượng"),resultSet.getString("Đơn vị"), resultSet.getDouble("Giá"), resultSet.getDouble("Thành Tiền"),
                        resultSet.getDate("Date")));
                table.setItems(proList);
            }
            BillView.product = proList;
        }catch (SQLException ex)
        {
            Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sumprice()
    {
        for(product c:proList)
        {
            priceout += c.getGia();
        }
        PriceOut.setText(String.valueOf(priceout));
    }
//    public void addOrderIntoTable(ActionEvent event){
//        int orderId = Integer.parseInt(id_order_detail.getText());
//        int productID = Integer.parseInt(id_product.getText());
//        String productName = product_name.getText();
//        double orderPrice = Double.parseDouble(product_price.getText());
//        int productQuantity = Integer.parseInt(product_quantity.getText());
//
//        OrderTable orderTable = new OrderTable(orderId,productID,productName,productQuantity, orderPrice, productQuantity*orderPrice);
//        orderDetailList.add(orderTable);
//    }

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
