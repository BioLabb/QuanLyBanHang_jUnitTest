package com.qlbh;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ManganeBuy extends menuView{

//    public void printBill(ActionEvent event) throws IOException {
//
//        Parent root = FXMLLoader.load(getClass().getResource("bill-view.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root);
//        stage.setTitle("Hóa đơn");
//        stage.setScene(scene);
//        stage.show();
//    }
    public void printBill(ActionEvent e) throws IOException {
        this.OutputBill(e);
    }
}
