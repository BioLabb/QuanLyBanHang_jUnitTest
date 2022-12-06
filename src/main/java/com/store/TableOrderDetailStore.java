package com.store;

import com.qlbh.TableOrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

public class TableOrderDetailStore {
    private static Date date;
    private static int Id;
    private static double pay;
    private static double change;
    private static ObservableList<TableOrderDetail> tableOrderDetailsList = FXCollections.observableArrayList();

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        TableOrderDetailStore.date = date;
    }

    public static int getId() {
        return Id;
    }

    public static void setId(int id) {
        Id = id;
    }

    public static double getPay() {
        return pay;
    }

    public static void setPay(double pay) {
        TableOrderDetailStore.pay = pay;
    }

    public static double getChange() {
        return change;
    }

    public static void setChange(double change) {
        TableOrderDetailStore.change = change;
    }

    public static ObservableList<TableOrderDetail> getTableOrderDetailsList() {
        return tableOrderDetailsList;
    }

    public static void setTableOrderDetailsList(ObservableList<TableOrderDetail> tableOrderDetailsList) {
        TableOrderDetailStore.tableOrderDetailsList = tableOrderDetailsList;
    }


    public static void addList(TableOrderDetail tableOrderDetail){

        tableOrderDetailsList.add(tableOrderDetail);
    }

    public static void clearList(){
        tableOrderDetailsList.clear();
    }
}
