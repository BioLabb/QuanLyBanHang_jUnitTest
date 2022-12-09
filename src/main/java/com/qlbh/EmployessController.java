package com.qlbh;

import com.project.OrderDetails;
import com.project.Product;
import com.services.OrderDetailsServices;
import com.services.ProductServices;
import com.store.TableOrderDetailStore;
import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EmployessController implements Initializable {
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
    private TableColumn<TableOrderDetail, String> product_name_colum;
    @FXML
    private TableColumn<TableOrderDetail, Integer> product_quantity_colum;
    @FXML
    private TableColumn<TableOrderDetail, Integer> unit_price_colum;
    @FXML
    private TableColumn<TableOrderDetail, Integer> total_colum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int id = (int) (Math.random() * Math.pow(10, 5)) + 9 * (int) Math.pow(10, 5);
        String idString = String.format("%d", id);
        id_order_detail.setText(idString);
        contentTextFieldChange(6);
        initTableView();
        try {
            initTableProduct();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initTableView() {

        stt_colum.setCellValueFactory(new PropertyValueFactory<>("stt"));
        product_id_colum.setCellValueFactory(new PropertyValueFactory<>("productID"));
        product_name_colum.setCellValueFactory(new PropertyValueFactory<>("productName"));
        product_quantity_colum.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        unit_price_colum.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        total_colum.setCellValueFactory(new PropertyValueFactory<>("total"));

        order_detail.setItems(TableOrderDetailStore.getTableOrderDetailsList());
    }

    // thêm item vào table
    public void addOrderIntoTable(ActionEvent event) {
        int orderId = Integer.parseInt(id_order_detail.getText());
        int productID = Integer.parseInt(id_product.getText());
        String productName = product_name.getText();

        int productQuantity = Integer.parseInt(product_quantity.getText());
        double orderPrice = Double.parseDouble(product_price.getText());
        double total = productQuantity * orderPrice;
        TableOrderDetail tableOrderDetail = new TableOrderDetail(orderId, productID, productName, productQuantity, orderPrice, total);
        //tableOrderDetail.setId(orderDetailList.size()+1);  // sô thứ tự
//        orderDetailList.add(tableOrderDetail);
        TableOrderDetailStore.addList(tableOrderDetail);
        order_detail.setItems(TableOrderDetailStore.getTableOrderDetailsList());

        pay_order_detail.setText(String.valueOf(totalPay()));
    }

    public void buy(ActionEvent event) throws SQLException, IOException {
        // đưa đơn hàng vào cơ sở dữ liệu
        for (TableOrderDetail value : TableOrderDetailStore.getTableOrderDetailsList()) {
            TableOrderDetailStore.setId(Integer.parseInt(id_order_detail.getText()));
            Date date = Date.valueOf(LocalDate.now());
            TableOrderDetailStore.setDate(date);

            TableOrderDetailStore.setPay(Double.parseDouble(pay_order_detail.getText()));
            TableOrderDetailStore.setChange(Double.parseDouble(change.getText()));


            OrderDetails orderDetails = new OrderDetails(value.getOrderID(), value.getProductID(), value.getProductQuantity(), value.getTotal(), date);
            OrderDetailsServices.addOrderDetail(orderDetails);
        }
        menuView.nextPage(event, "bill-view.fxml", "Hóa đơn");
    }

    // Giới hạn ký tự trong textFile
    private void contentTextFieldChange(int length) {
        id_product.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (id_product.getText().length() > length) {
                id_product.setText(oldValue);

            } else if (id_product.getText().length() == length) {
                int id = Integer.parseInt(id_product.getText());
                try {
                    Product product = ProductServices.findProductById(id);
                    if (product != null) {
                        product_name.setText(product.getName());
                        product_price.setText(String.valueOf(product.getPrice()));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void pay_change() {
        cus_pay.textProperty().addListener((observable, oldVal, newVal) -> {
            double pay = Double.parseDouble(pay_order_detail.getText());
            double cus_money = Double.parseDouble(cus_pay.getText());
            double moneyOfCus;
            if (cus_money > pay) {
                moneyOfCus = cus_money - pay;
                change.setText(String.format("%.3f", moneyOfCus));
            }
        });
    }

    public double totalPay() {
        double sum = 0;
        for (TableOrderDetail tb : TableOrderDetailStore.getTableOrderDetailsList()) {
            sum += tb.getTotal();
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
    private TableColumn<TableProduct, Integer> product_id_col;
    @FXML
    private TableColumn<TableProduct, Integer> product_name_col;
    @FXML
    private TableColumn<TableProduct, String> supplier_col;
    @FXML
    private TableColumn<TableProduct, Double> product_cost_col;
    @FXML
    private TableColumn<TableProduct, Double> product_price_col;
    @FXML
    private TableColumn<TableProduct, Integer> product_quantity_col;
    private ObservableList<TableProduct> productObservableList = FXCollections.observableArrayList();

    public void initTableProduct() throws SQLException {
        product_id_col.setCellValueFactory(new PropertyValueFactory<>("productID"));
        product_name_col.setCellValueFactory(new PropertyValueFactory<>("productName"));
        supplier_col.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        product_cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));
        product_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        product_quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        ArrayList<Product> productArrayList = ProductServices.findAll();
        for (Product product : productArrayList) {
            TableProduct tableProduct = new TableProduct(product.getId(), product.getName(), product.getSupplierID(), product.getCost(), product.getPrice(), 3);
            productObservableList.add(tableProduct);
        }
        table_product.setItems(productObservableList);

    }

    public boolean isProductId(String s){
        Pattern p = Pattern.compile("^[0-9]{1,6}$");
        if(p.matcher(s).find()){
            return true;
        }
        return  false;
    }

    public boolean isPorductName(String s){
        Pattern p = Pattern.compile("^[a-zA-Z]+$");
        if(p.matcher(s).find()){
            return true;
        }
        return false;
    }

    public  boolean validator(String s){
        if(s.isEmpty())
            return false;
        return true;
    }
    public void searchProductById(ActionEvent event) {
        if (validator(product_id.getText())) {
            if(!isProductId(product_id.getText())){
                ShowAlert.show("Vui lòng nhập lại mã sản phẩm", Alert.AlertType.ERROR);
                return;
            }
            int id = Integer.parseInt(product_id.getText());
            List<TableProduct> tableProducts = productObservableList.stream().filter(pt -> pt.getProductID() == id).collect(Collectors.toList());

            ObservableList<TableProduct> tmp = FXCollections.observableArrayList();
            for (TableProduct tableProduct : tableProducts) {
                tmp.add(tableProduct);
            }
            table_product.setItems(tmp);
        } else {
            ShowAlert.show("Mã đơn hàng trống", Alert.AlertType.WARNING);
            table_product.setItems(productObservableList);
        }
    }

    public void searchProductByProductName(ActionEvent event) {
        if (validator(product_Name.getText().trim())) {
            if(!isPorductName(product_Name.getText().trim())){
                ShowAlert.show("Vui lòng nhập lại sản phẩm", Alert.AlertType.WARNING);
                return;
            }
            String name = product_Name.getText().trim();
            List<TableProduct> tableProducts = productObservableList.stream().filter(pt -> pt.getProductName().equals(name)).collect(Collectors.toList());

            ObservableList<TableProduct> tmp = FXCollections.observableArrayList();
            for (TableProduct tableProduct : tableProducts) {
                tmp.add(tableProduct);
            }
            table_product.setItems(tmp);
        } else {
            ShowAlert.show("Mã đơn hàng trống", Alert.AlertType.WARNING);
            table_product.setItems(productObservableList);
        }
    }


}
