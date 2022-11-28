package com.qlbh;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseAccessController {
private  String viewFxml = "sign-view.fxml";
public void buttonManageClick(ActionEvent e) throws IOException {
    SignController.setManager(true);
    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
    OpenScene(stage,viewFxml);
}

public void buttonEmployeeClick(ActionEvent e) throws IOException {
    SignController.setManager(false);
    Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
    OpenScene(stage,viewFxml);
}
private void OpenScene(Stage stage,String nameViewFxml) throws IOException {
    Parent roof = FXMLLoader.load(getClass().getResource(nameViewFxml));
    Scene scene = new Scene(roof);
    stage.setTitle("Chọn quyền truy cập");
    stage.setScene(scene);
    stage.show();
}

//public void buttonEmployeeClick(ActionEvent e){
//    Parent root = FXMLLoader.load(getClass().getResource("Sign-view.fxml"));
//}

}
