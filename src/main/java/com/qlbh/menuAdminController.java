package com.qlbh;

import com.config.JDBC;
import com.project.OrderDetails;
import com.services.EmployessServices;
import com.services.ProductServices;
import com.services.OrderDetailsServices;
import com.store.EmployeesStore;
import com.store.TableOrderDetailStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
    private Label pay_order_detail;
    @FXML
    private TextField cus_pay;
    @FXML
    private Label change;

    @FXML
    private TableView<TableOrderDetail> order_detail;
//    @FXML
//    private TableColumn<OrderTable,Integer> unit_price_colum;
//    private ObservableList<OrderTable> orderDetailList = FXCollections.observableArrayList();

    //tableview tab 1
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

    //table view thong ke theo ngay
    @FXML
    public ComboBox<Integer> comboBox;
    ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    @FXML
    public ComboBox<Integer> comboBoxQuarter;

    ObservableList<Integer> listQuarter = FXCollections.observableArrayList(1, 2, 3 ,4);

    @FXML
    private Button btnMonth;

    @FXML
    private Button btnQuarter;
    @FXML
    private Button btnQuarterEn;
    @FXML
    private Button btnMonthEn;
    @FXML
    private Button btnAddMonth;
    @FXML
    private Button btnAddQuarter;

    private double profitDay;
    @FXML
    LineChart<String, Number> lineChart;
    @FXML
    private TableView<ProfitList> table2;
    @FXML
    private TableColumn<ProfitList, Date> oDay;
    @FXML
    private TableColumn<ProfitList, Double> profit;

    ObservableList<ProfitList> profitList = FXCollections.observableArrayList();

    //table view cua tab thong ke theo thang
    @FXML
    LineChart<String, Number> lineChartMonth;
    @FXML
    private TableView<ProfitListMonth> table3;
    @FXML
    private TableColumn<ProfitListMonth, Integer> aMonth;
    @FXML
    private TableColumn<ProfitListMonth, Double> profitMonth;

    ObservableList<ProfitListMonth> profitListMonths = FXCollections.observableArrayList();

    //table view cua tab thong ke theo quy
    @FXML
    LineChart<String, Number> lineChartQuarter;
    @FXML
    private TableView<ProfitQuarter> table4;
    @FXML
    private TableColumn<ProfitQuarter, Integer> aQuarter;
    @FXML
    private TableColumn<ProfitQuarter, Double> profitQuarter;

    ObservableList<ProfitQuarter> profitQuarters = FXCollections.observableArrayList();

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
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
//    private ObservableList<TableOrderDetail> orderDetailList;
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
       comboBox.setItems(list);
       comboBoxQuarter.setItems(listQuarter);
        try {
//            loadTable();
            loadTable3();
            loadTable4();
            setChartMonth();
            setChartQuarter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        sumprice(); //Tong bien Price trong table view Thanh Tien hien thi Thanh Tien Bill.priceOut


        Dateta.setValue(LocalDate.now());

        BillView.Datetah = Dateta;
        BillView.usern = lb_user_name.getText();
//        BillView.priceOut = Double.parseDouble(PriceOut.getText()); //Hien Thi Label Thanh Tien


       pay_change();
       initTableView();

//        try {
//            initTableProduct();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
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



   // -----------QUẢN LÝ KHO---------------
    @FXML
    private TextField product_id;
    @FXML
    private TextField product_Name;
    @FXML
    private TableView<TableProduct> table_product;
    @FXML
    private TableColumn<TableProduct,Integer> product_id_col;
    @FXML
    private TableColumn<TableProduct,Integer> product_name_col;
    @FXML
    private TableColumn<TableProduct,String> supplier_col;
    @FXML
    private TableColumn<TableProduct,Double> product_cost_col;
    @FXML
    private TableColumn<TableProduct,Double> product_price_col;
    @FXML
    private TableColumn<TableProduct,Integer> product_quantity_col;
    private ObservableList<TableProduct> productObservableList = FXCollections.observableArrayList();
    public void initTableProduct() throws SQLException {
        product_id_col.setCellValueFactory(new PropertyValueFactory<>("productID"));
        product_name_col.setCellValueFactory(new PropertyValueFactory<>("productName"));
        supplier_col.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        product_cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));
        product_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        product_quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ArrayList<Product> productArrayList = ProductServices.findAll();
        for (Product product:productArrayList) {
            TableProduct tableProduct = new TableProduct(product.getId(),product.getName(),product.getSupplierID(),product.getCost(),product.getPrice(),3);
            productObservableList.add(tableProduct);
        }
        table_product.setItems(productObservableList);

    }

    public void searchProductById(ActionEvent event){
        if(validator(product_id.getText())){
            int id = Integer.parseInt(product_id.getText());
            List<TableProduct> tableProducts = productObservableList.stream().filter(pt-> pt.getProductID() == id).collect(Collectors.toList());

            ObservableList<TableProduct> tmp = FXCollections.observableArrayList();
            for (TableProduct tableProduct:tableProducts) {
                tmp.add(tableProduct);
            }
            table_product.setItems(tmp);
        }
        else{
            ShowAlert.show("Mã đơn hàng trống", Alert.AlertType.WARNING);
            table_product.setItems(productObservableList);
        }
    }

    public void searchProductByProductName(ActionEvent event){
        if(validator(product_Name.getText().trim())){
        String name = product_Name.getText().trim();
        List<TableProduct> tableProducts = productObservableList.stream().filter(pt-> pt.getProductName().equals(name)).collect(Collectors.toList());

        ObservableList<TableProduct> tmp = FXCollections.observableArrayList();
        for (TableProduct tableProduct:tableProducts) {
            tmp.add(tableProduct);
        }
        table_product.setItems(tmp);
    }
    else{
        ShowAlert.show("Mã đơn hàng trống", Alert.AlertType.WARNING);
        table_product.setItems(productObservableList);
    }
}



// ------------ THÊM NHÂN VIÊN-------------
   // kiểm tra rỗng
   private boolean validator(String val){
       if(val.isEmpty()){
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

   //Day la du lieu rieng cua Hieu neu cai kia load khong duoc cu doi table view roi xai khong anh huong den
    //database trong sql
    //loadTable cua tab 1
//    public void loadTable() throws SQLException {
//        connection = JDBC.getCnn();
//        refreshtable();
//
//        numberColumn.setCellValueFactory(new PropertyValueFactory<product, Integer>("Number"));
//        IDcolumn.setCellValueFactory(new PropertyValueFactory<product, String>("ID"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<product, String>("nameProduct"));
//        amountColumn.setCellValueFactory(new PropertyValueFactory<product, Integer>("amount"));
//        dvColumn.setCellValueFactory(new PropertyValueFactory<product, String>("dv"));
//        priceColumn.setCellValueFactory(new PropertyValueFactory<product, Double>("gia"));
//        thanhTienColumn.setCellValueFactory(new PropertyValueFactory<product, Double>("thanhTien"));
//        Dateh.setCellValueFactory(new PropertyValueFactory<product, Date>("Dateh"));
//    }

    //Day la du lieu rieng cua Hieu neu cai kia load khong duoc cu doi table view roi xai khong anh huong den
    //database trong sql (co san trong menu-admin-view.fxml).
    //refreshtable cua tab 1
//    @FXML
//    private void refreshtable() throws SQLException {
//        try {
//            proList.clear();
//            query = "SELECT * FROM testtable";
//            preparedStatement = connection.prepareStatement(query);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next())
//            {
//                proList.add(new product(resultSet.getInt("STT"), resultSet.getString("Mã Sản Phẩm"), resultSet.getString("Tên Sản Phẩm"),
//                        resultSet.getInt("Số lượng"),resultSet.getString("Đơn vị"), resultSet.getDouble("Giá"), resultSet.getDouble("Thành Tiền"),
//                        resultSet.getDate("Date")));
//                table.setItems(proList);
//            }
//            BillView.product = proList;
//        }catch (SQLException ex)
//        {
//            Logger.getLogger(menuAdminController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }


    //Tinh tong thanh tien cua tab 1
//    public void sumprice()
//    {
//        for(product c:proList)
//        {
//            priceout += c.getGia();
//        }
//        PriceOut.setText(String.valueOf(priceout));
//    }

    //loadTable cua tab thong ke theo ngay
    public void loadTable2() throws SQLException {
        connection = JDBC.getCnn();
        refreshtable2();

        oDay.setCellValueFactory(new PropertyValueFactory<>("oneDay"));
        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }

    public void loadTableQuarter() throws SQLException {
        connection = JDBC.getCnn();
        refreshtableQuarter();

        oDay.setCellValueFactory(new PropertyValueFactory<>("oneDay"));
        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }

    //refreshtable cua tab thong ke theo ngay
    @FXML
    private void refreshtable2() throws SQLException {
        try {
            profitList.clear();
            if(comboBox.getValue() == 1) {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 1";
            } else if(comboBox.getValue() == 2)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 2";
            }else if(comboBox.getValue() == 3)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 3";
            }else if(comboBox.getValue() == 4)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 4";
            }else if(comboBox.getValue() == 5)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 5";
            }else if(comboBox.getValue() == 6)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 6";
            }else if(comboBox.getValue() == 7)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 7";
            }else if(comboBox.getValue() == 8)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 8";
            }else if(comboBox.getValue() == 9)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 9";
            }else if(comboBox.getValue() == 10)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 10";
            }else if(comboBox.getValue() == 11)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 11";
            }else if(comboBox.getValue() == 12)
            {
                query = "SELECT * FROM order_details WHERE MONTH(date_allocated) = 12";
            }

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                profitList.add(new ProfitList(resultSet.getDate("date_allocated"), resultSet.getDouble("unit_price")));
                table2.setItems(profitList);
            }
        }catch (SQLException ex)
        {
            Logger.getLogger(menuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Chọn theo comboBox theo ngay


    //Chart cua tab thong ke theo ngay
    private void setChart()
    {
        lineChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for(ProfitList c : profitList)
        {
            series.getData().add(new XYChart.Data<>(c.getOneDay().toString(), c.getProfit()));
        }
        series.setName("Profit per day");

        lineChart.getData().add(series);
    }

    @FXML
    public void comboBoxChanged(ActionEvent e) throws SQLException {
        loadTable2();
        setChart();
    }

    public void refreshtableQuarter()
    {
        try {
            profitList.clear();
            switch (comboBoxQuarter.getValue()) {
                case 1:
                    query = "SELECT * FROM order_details WHERE QUARTER(date_allocated) = 1";
//                    ShowAlert.show(comboBoxQuarter.getValue().toString(), Alert.AlertType.WARNING);
                    break;
                case 2:
                    query = "SELECT * FROM order_details WHERE QUARTER(date_allocated) = 2";
//                    ShowAlert.show(comboBoxQuarter.getValue().toString(), Alert.AlertType.WARNING);
                    break;
                case 3:
                    query = "SELECT * FROM order_details WHERE QUARTER(date_allocated) = 3";
//                    ShowAlert.show(comboBoxQuarter.getValue().toString(), Alert.AlertType.WARNING);
                    break;
                case 4:
                    query = "SELECT * FROM order_details WHERE QUARTER(date_allocated) = 4";
//                    ShowAlert.show(comboBoxQuarter.getValue().toString(), Alert.AlertType.WARNING);
                    break;
//            }
            }
//            query = "SELECT * FROM order_details WHERE quarter(date_allocated) = ?";
//            preparedStatement.setInt(7, comboBoxQuarter.getValue());
//                preparedStatement = connection.prepareStatement(query);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                profitList.add(new ProfitList(resultSet.getDate("date_allocated"), resultSet.getDouble("unit_price")));
                table2.setItems(profitList);
            }
        }catch (SQLException ex)
        {
            Logger.getLogger(menuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void comboBoxQuarterChange(ActionEvent e) throws SQLException {
        loadTableQuarter();
        setChart();
    }

    //ham hien thi comboBox Quarter
    @FXML
    public void comboBoxEnable(ActionEvent e)
    {
        comboBoxQuarter.setDisable(true);
        comboBox.setDisable(false);
        btnQuarter.setDisable(true);
        btnAddQuarter.setDisable(true);
        btnMonth.setVisible(false);
        btnQuarterEn.setDisable(true);
    }

    @FXML
    public void comboBoxEnable1(ActionEvent e)
    {
        btnQuarterEn.setDisable(false);
        btnMonth.setVisible(true);
        comboBoxQuarter.setDisable(false);
        btnQuarter.setDisable(false);
        btnAddQuarter.setDisable(false);
    }

    //ham hien thi comboBox Quarter
    @FXML
    public void comboBoxQuarterEnable(ActionEvent e)
    {
        btnQuarter.setVisible(false);
        btnMonthEn.setDisable(true);
        comboBox.setDisable(true);
        comboBoxQuarter.setDisable(false);
        btnMonth.setDisable(true);
        btnAddMonth.setDisable(true);
    }

    public void comboBoxQuarterEnable1(ActionEvent e)
    {
        btnMonthEn.setDisable(false);
        btnQuarter.setVisible(true);
        comboBox.setDisable(false);
        btnMonth.setDisable(false);
        btnAddMonth.setDisable(false);
    }
    //loadTable cua tab thong ke theo thang
    public void loadTable3() throws SQLException {
        connection = JDBC.getCnn();
        refreshtable3();

        aMonth.setCellValueFactory(new PropertyValueFactory<>("Month"));
        profitMonth.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }

    //refreshtable cua tab thong ke theo thang
    @FXML
    private void refreshtable3() throws SQLException {
        try {
            profitListMonths.clear();
            query = "SELECT * FROM testthongke2";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                profitListMonths.add(new ProfitListMonth(resultSet.getInt("Month"), resultSet.getDouble("Profit")));
                table3.setItems(profitListMonths);
            }
        }catch (SQLException ex)
        {
            Logger.getLogger(menuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Chart cua tab thong ke theo thang
    private void setChartMonth()
    {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(ProfitListMonth c : profitListMonths)
        {
            series.getData().add(new XYChart.Data<>(String.valueOf(c.getMonth()), c.getProfit()));
        }
        series.setName("Profit per Month");
        lineChartMonth.getData().add(series);
    }

    //loadTable cua tab thong ke theo quy
    public void loadTable4() throws SQLException {
        connection = JDBC.getCnn();
        refreshtable4();

        aQuarter.setCellValueFactory(new PropertyValueFactory<>("quarter"));
        profitQuarter.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }

    //refreshtable cua tab thong ke theo quy
    @FXML
    private void refreshtable4() throws SQLException {
        try {
            profitQuarters.clear();
            query = "SELECT * FROM testthongke3";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                profitQuarters.add(new ProfitQuarter(resultSet.getInt("Quarter"), resultSet.getDouble("Profit")));
                table4.setItems(profitQuarters);
            }
        }catch (SQLException ex)
        {
            Logger.getLogger(menuAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Chart cua tab thong ke theo quy
    private void setChartQuarter()
    {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(ProfitQuarter c : profitQuarters)
        {
            series.getData().add(new XYChart.Data<>(String.valueOf(c.getQuarter()), c.getProfit()));
        }
        series.setName("Profit per Quarter");
        lineChartQuarter.getData().add(series);
    }

    @FXML
    public void addToMonth(ActionEvent e) throws SQLException {
        ProfitListMonth profitM = new ProfitListMonth();
        for(ProfitList c : profitList)
        {
            profitM.setMonth(c.getOneDay().getMonth()+1);
            profitDay += c.getProfit();
        }

        profitM.setProfit(profitDay);
        profitListMonths.add(profitM);
        try{
            addToTableMonth(profitM);
            ShowAlert.show("Add successful !", Alert.AlertType.INFORMATION);
        } catch (SQLException ex) {
            ShowAlert.show("Add failed !", Alert.AlertType.INFORMATION);
        }
        btnQuarter.setDisable(false);
        btnAddQuarter.setDisable(false);
        refreshtable3();
        setChartMonth();
    }

    @FXML
    public void addToQuarter(ActionEvent e) throws SQLException {
        ProfitQuarter profitQ = new ProfitQuarter();

        for(ProfitList c : profitList)
        {
//            profitQ.setQuarter(c.getOneDay().getMonth()+1);
            switch (c.getOneDay().getMonth()+1)
            {
                case 1:
                case 2:
                case 3:
                        profitQ.setQuarter(1);
                    break;
                case 4:
                case 5:
                case 6:
                        profitQ.setQuarter(2);
                    break;
                case 7:
                case 8:
                case 9:
                        profitQ.setQuarter(3);
                    break;
                case 10:
                case 11:
                case 12:
                        profitQ.setQuarter(4);
                    break;
            }
            profitDay += c.getProfit();
        }

        profitQ.setProfit(profitDay);
        profitQuarters.add(profitQ);
        try{
            addToTableQuarter(profitQ);
            ShowAlert.show("Add successful !", Alert.AlertType.INFORMATION);
        } catch (SQLException ex) {
            ShowAlert.show("Add failed !", Alert.AlertType.INFORMATION);
        }
        btnMonth.setDisable(false);
        btnAddMonth.setDisable(false);
        refreshtable4();
        setChartQuarter();
    }


    @FXML

   public void addToTableMonth(ProfitListMonth m) throws SQLException {
       connection = JDBC.getCnn();
       connection.setAutoCommit(false);
       preparedStatement = connection.prepareStatement("INSERT INTO testthongke2(Month, Profit) VALUES(?, ?)");
       preparedStatement.setInt(1, m.getMonth());
       preparedStatement.setDouble(2, m.getProfit());
       preparedStatement.executeUpdate();
       connection.commit();
   }

   public void addToTableQuarter(ProfitQuarter q) throws SQLException {
       connection = JDBC.getCnn();
       connection.setAutoCommit(false);
       preparedStatement = connection.prepareStatement("INSERT INTO testthongke3(Quarter, Profit) VALUES(?, ?)");
       preparedStatement.setInt(1, q.getQuarter());
       preparedStatement.setDouble(2, q.getProfit());
       preparedStatement.executeUpdate();
       connection.commit();
   }
}
