package com.qlbh;

import com.config.JDBC;
import com.services.EmployessServices;
import com.store.EmployeesStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import project.Employess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignController{
    private  static boolean Manager = false;
    private String fxmlViewName;
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
        private TextField passwordText;
        @FXML
        private CheckBox showPassword;
        @FXML
        private Button signIn;
        @FXML
        private Label signUp;

    public void showPass(ActionEvent event) {
        if (showPassword.isSelected()) {
            passwordText.setText(password.getText());
            passwordText.setVisible(true);
            password.setVisible(false);
            return;
        }
        password.setText(passwordText.getText());
        password.setVisible(true);
        passwordText.setVisible(false);
    }

    public boolean validate()
    {
        if(user.getText().isEmpty()){
            ShowAlert.show("username Trống", Alert.AlertType.WARNING);
            return false;
        }
        else if(password.getText().isEmpty()){
            ShowAlert.show("password Trống", Alert.AlertType.WARNING);
            return  false;
        }
        return true;
    }

    public boolean isEmployee(String userName, String pass, boolean manager) throws SQLException {
        // kết nối
        Connection connection = JDBC.getCnn();
        // tạo query
        PreparedStatement pstm = connection.prepareStatement("select password, maganer from employees where user = ?");
        pstm.setString(1,userName);

        ResultSet resultSet = pstm.executeQuery();


        if(resultSet.next()){
            String passAccess = resultSet.getString("password");
            boolean managerAccess = resultSet.getBoolean("maganer");
            if(passAccess.equals(pass) && managerAccess == manager){
                return true;
            }
        }
        return false;
    }
    public void SignIn(ActionEvent event) throws IOException, SQLException {
        // kiểm tra đầu vào có rỗng hay không
        if(validate()){
            String userName = user.getText().toString();
            String passW = password.getText().toString();

            if(isEmployee(userName, passW,Manager)){
                if(Manager){
                    fxmlViewName = "menu-admin-view.fxml";
                }
                else {
                    fxmlViewName = "employee-view.fxml";
                }
                menuView.nextPage(event,fxmlViewName,"Quản lý bán hàng");
                EmployeesStore.setEmployess(EmployessServices.findEmployeeByUser(userName));

            }
            else{
                ShowAlert.show("username hoặc password không đúng",Alert.AlertType.WARNING);
            }
        }
    }

    public void cancel(ActionEvent e) throws IOException {
        user.setText("");
        password.setText("");
        //stop
        Parent root = FXMLLoader.load(getClass().getResource("sign-view.fxml"));
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        System.out.println("Close succesful");
        stage.close();
    }

    public void back(ActionEvent e) throws IOException {
        menuView.nextPage(e,"choose-access-view.fxml","Chọn quyền truy cập");
    }
}
