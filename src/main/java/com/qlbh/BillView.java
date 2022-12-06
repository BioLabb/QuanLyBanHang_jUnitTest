package com.qlbh;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private DatePicker lbDate;
    @FXML
    private Label moneyRecieve;
    @FXML
    private Label moneyBack;
    @FXML
    private Label Name;
    @FXML
    private Label IDBill;

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

    public void BackToHome(ActionEvent event) throws IOException {
        String fxmlViewName = "menu-admin-view.fxml";
        menuView.nextPage(event,fxmlViewName,"Quản lý bán hàng");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        IDBill.setText(String.valueOf(ID));
//        lbDate.setValue(Datetah.getValue());
//        Name.setText(usern);
//        moneyRecieve.setText(String.valueOf(priceOut));
//        numberColumn.setCellValueFactory(new PropertyValueFactory<product, Integer>("Number"));
//        IDcolumn.setCellValueFactory(new PropertyValueFactory<product, String>("ID"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<product, String>("nameProduct"));
//        amountColumn.setCellValueFactory(new PropertyValueFactory<product, Integer>("amount"));
//        dvColumn.setCellValueFactory(new PropertyValueFactory<product, String>("dv"));
//        priceColumn.setCellValueFactory(new PropertyValueFactory<product, Double>("gia"));;
//        table.setItems(product);


    }
}
