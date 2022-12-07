package com.qlbh;
import com.config.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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

    //table view thong ke theo ngay
    @FXML
    LineChart<String, Number> lineChart;
    @FXML
    private TableView<ProfitList> table2;
    @FXML
    private TableColumn<ProfitList, java.sql.Date> oDay;
    @FXML
    private TableColumn<ProfitList, Double> profit;

    ObservableList<ProfitList> profitList = FXCollections.observableArrayList();

    ObservableList<ProductList> productLists = FXCollections.observableArrayList();

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
    ProductList productList = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadTable();
            loadTable2();
            loadTable3();
            loadTable4();
            setChart();
            setChartMonth();
            setChartQuarter();
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
            query = "SELECT * FROM testtable";
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

    //loadTable cua tab thong ke theo ngay
    public void loadTable2() throws SQLException {
        connection = JDBC.getCnn();
        refreshtable2();

        oDay.setCellValueFactory(new PropertyValueFactory<>("oneDay"));
        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }

    //refreshtable cua tab thong ke theo ngay
    @FXML
    private void refreshtable2() throws SQLException {
        try {
            profitList.clear();
            query = "SELECT * FROM testthongke1";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                profitList.add(new ProfitList(resultSet.getDate("Date"), resultSet.getDouble("Price In")));
                table2.setItems(profitList);
            }
        }catch (SQLException ex)
        {
            Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Chart cua tab thong ke theo ngay
    private void setChart()
    {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(ProfitList c : profitList)
        {
            series.getData().add(new XYChart.Data<>(c.getOneDay().toString(), c.getProfit()));
        }
        series.setName("Profit per day");
        lineChart.getData().add(series);
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
            Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(table.class.getName()).log(Level.SEVERE, null, ex);
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
}
