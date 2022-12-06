package com.store;

import com.qlbh.TableOrderDetail;
import javafx.collections.ObservableList;

public class TableOrderDetailListStore {
    public static ObservableList<TableOrderDetail> getTableOrderDetailsList() {
        return tableOrderDetailsList;
    }

    public static void setTableOrderDetailsList(ObservableList<TableOrderDetail> tableOrderDetailsList) {
        TableOrderDetailListStore.tableOrderDetailsList = tableOrderDetailsList;
    }

    private static ObservableList<TableOrderDetail> tableOrderDetailsList;

    private static void add(TableOrderDetail tableOrderDetail){
        tableOrderDetailsList.add(tableOrderDetail);
    }
}
