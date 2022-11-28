package com.qlbh;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignController {
    private static String userName = "admin";
    private static String pass = "1234";
    private  static boolean Manager = false;
    private String fxmlViewName;
    static void setAdmin(String user)
    {
        userName = user;
    }
    static void setManager(boolean accessManager){
        Manager = accessManager;
    }
    static void setPass(String pass){
        pass = pass;
    }

    @FXML
        private TextField user;
        @FXML
        private PasswordField password;
        @FXML
        private CheckBox showPassword;
        @FXML
        private Button signIn;
        @FXML
        private Label signUp;

    public void AlertShow(String content, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setContentText(content);
        alert.show();
    }


    public boolean validate()
    {
        if(user.getText().isEmpty()){
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//
//            alert.setContentText("User trống");
//            alert.show();
            AlertShow("username Trống", Alert.AlertType.WARNING);
            return false;
        }
        else if(password.getText().isEmpty()){
            AlertShow("password Trống", Alert.AlertType.WARNING);
            return  false;
        }
        return true;
    }
    public void SignIn(ActionEvent event) throws IOException {
        if(validate()){
            String userName = user.getText().toString();
            String passW = password.getText().toString();

            // sign admin
            if(userName.equals(userName) && pass.equals(passW)){
                if(Manager){
                    fxmlViewName = "menu-admin-view.fxml";
                }
                else {
                    fxmlViewName = "employee-view.fxml";
                }
                Parent root = FXMLLoader.load(getClass().getResource(fxmlViewName));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Quản lý bán hàng");
                stage.setScene(scene);
                stage.show();
            }
            else
                AlertShow("username hoặc password không đúng",Alert.AlertType.WARNING);
        }
    }

    public void showPass(ActionEvent e) {
//        System.out.println("showpass");
//        if(showPassword.isSelected()) {
//            password.accessibleTextProperty();
//        }
    }

    public void cancel(ActionEvent e){
        user.setText("");
        password.setText("");
        //stop
    }
}
