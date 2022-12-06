package com.qlbh;
import com.config.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class table implements Initializable {
    @FXML
    private TextField txt_ID;
    @FXML
    private TextField txt_Name;
    @FXML
    private TextField txt_Amout;
    @FXML
    private TextField txt_Quantity;
    @FXML
    private TextField txt_Price;
    @FXML
    private DatePicker dp_Date;

    @FXML
    private TableView<ProductList> table;
    @FXML
    private TableColumn<ProductList, Integer> columnNum;
    @FXML
    private TableColumn<ProductList, String> columnID;
    @FXML
    private TableColumn<ProductList, String> columnName;
    @FXML
    private TableColumn<ProductList, Integer> columnAmout;
    @FXML
    private TableColumn<ProductList, String> columnQuantity;
    @FXML
    private TableColumn<ProductList, Double> columnPrice;
    @FXML
    private TableColumn<ProductList, Date> columnDate;

    ObservableList<ProductList> productLists = FXCollections.observableArrayList();
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ProductList productList = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadTable();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadTable() throws SQLException {
        connection = JDBC.getCnn();
        refreshtable();

        columnNum.setCellValueFactory(new PropertyValueFactory<>("STT"));
        columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnAmout.setCellValueFactory(new PropertyValueFactory<>("Amout"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("Datet"));
    }

    @FXML
    private void refreshtable() throws SQLException {
        try {
            productLists.clear();
            query = "SELECT * FROM new_table";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                productLists.add(new ProductList(resultSet.getInt("STT"),resultSet.getString("Mã Sản Phẩm"),
                        resultSet.getString("Tên Sản Phẩm"), resultSet.getInt("Số lượng"),
                        resultSet.getString("Đơn Vị"), resultSet.getDouble("Giá"), resultSet.getDate("Date")));
                table.setItems(productLists);
            }
        }catch (SQLException ex)
        {
            Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
