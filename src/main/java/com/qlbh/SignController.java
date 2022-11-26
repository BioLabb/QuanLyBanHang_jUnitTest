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
    private String admin = "admin";
    private String pass = "1234";
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



    public void SignIn(ActionEvent event) throws IOException {
        String userName = null;
        String passW = null;

        userName = user.getText().toString();
        passW = password.getText().toString();

        // sign admin
        if(admin.equals(userName) && pass.equals(passW)){
            Parent root = FXMLLoader.load(getClass().getResource("menu-admin-view.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Quản lý bán hàng");
            stage.setScene(scene);
            stage.show();
        }
        else
            System.out.println("sign in fail");
    }

    public void showPass()
    {

    }

    public void cancel(ActionEvent e){
        user.setText("");
        password.setText("");
        //stop
    }
}
