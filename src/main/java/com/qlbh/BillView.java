package com.qlbh;

import com.store.EmployeesStore;
import com.store.TableOrderDetailStore;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class BillView implements Initializable {
    public static ObservableList<product> product;
    public static double priceOut;
    public static DatePicker Datetah;
    public static String usern;
    public static int num;
    private static int count = 0;
    private int ID = ++count;
    @FXML
    private Label lbDate;
    @FXML
    private Label moneyRecieve;
    @FXML
    private Label moneyBack;
    @FXML
    private Label Name;
    @FXML
    private Label IDBill;

//    @FXML
//    private TableView<product> table;
//    @FXML
//    private TableColumn<product, Integer> numberColumn;
//    @FXML
//    private TableColumn<product, String > IDcolumn;
//    @FXML
//    private TableColumn<product, String> nameColumn;
//    @FXML
//    private TableColumn<product, Integer> amountColumn;
//    @FXML
//    private TableColumn<product, String> dvColumn;
//    @FXML
//    private TableColumn<product, Double> priceColumn;
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

    public void BackToHome(ActionEvent event) throws IOException {
        String fxmlViewName = "menu-admin-view.fxml";
        menuView.nextPage(event,fxmlViewName,"Quản lý bán hàng");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbDate.setText(String.valueOf(TableOrderDetailStore.getDate()));
        IDBill.setText(String.valueOf(TableOrderDetailStore.getId()));
        moneyRecieve.setText(String.valueOf(TableOrderDetailStore.getPay()));
        moneyBack.setText(String.valueOf(TableOrderDetailStore.getChange()));
        Name.setText(String.format("%s %s",EmployeesStore.getEmployess().getFirstName(), EmployeesStore.getEmployess().getLastName()));


        stt_colum.setCellValueFactory(new PropertyValueFactory<>("stt"));
        product_id_colum.setCellValueFactory(new PropertyValueFactory<>("productID"));
        product_name_colum.setCellValueFactory(new PropertyValueFactory<>("productName"));
        product_quantity_colum.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));
        unit_price_colum.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        total_colum.setCellValueFactory(new PropertyValueFactory<>("total"));

        order_detail.setItems(TableOrderDetailStore.getTableOrderDetailsList());


    }

    public void print(ActionEvent event){
       TableOrderDetailStore.clearList();
       order_detail.setItems(TableOrderDetailStore.getTableOrderDetailsList());
    }
}
