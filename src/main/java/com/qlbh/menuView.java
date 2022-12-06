package com.qlbh;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.project.Employess;

import java.io.IOException;

public class menuView {
    public static Employess employee;
    public static void OutputBill(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(menuView.class.getResource("bill-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Hóa đơn");
        stage.setScene(scene);
        stage.show();
    }

    public static void nextPage(ActionEvent e, String fxmlView, String title) throws IOException {
        Parent page = FXMLLoader.load(menuView.class.getResource(fxmlView));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(page);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
