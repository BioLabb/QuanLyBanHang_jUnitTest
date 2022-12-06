package com.store;

import com.qlbh.TableOrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TableOrderDetailListStore {
    private static ObservableList<TableOrderDetail> tableOrderDetailsList = FXCollections.observableArrayList();
    public static ObservableList<TableOrderDetail> getTableOrderDetailsList() {
        return tableOrderDetailsList;
    }

    public static void setTableOrderDetailsList(ObservableList<TableOrderDetail> tableOrderDetailsList) {
        TableOrderDetailListStore.tableOrderDetailsList = tableOrderDetailsList;
    }


    public static void add(TableOrderDetail tableOrderDetail){

        tableOrderDetailsList.add(tableOrderDetail);
    }
}
