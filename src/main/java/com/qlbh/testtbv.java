package com.qlbh;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class testtbv implements Initializable {
    @FXML
    private TableView<dt> tbdt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        testdt s = new testdt();
        this.tbdt.setItems(FXCollections.observableList(s.getdt()));
    }
}
