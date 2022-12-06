package com.qlbh;

import com.services.EmployessServices;
import com.store.EmployeesStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.project.Employess;

import java.io.IOException;
import java.sql.SQLException;

public class removeEpmloyeeController extends menuView {
    @FXML
    private TextField button_user;
    @FXML
    private Label lb_id;
    @FXML
    private Label lb_last_name;
    @FXML
    private Label lb_first_name;
    @FXML
    private Label lb_phone;
    @FXML
    private Label lb_email;
    public void goBack(ActionEvent e) throws IOException {
        this.nextPage(e,"menu-admin-view.fxml","Quản lý bán hàng");
    }

    public void findByUser(ActionEvent e) throws SQLException {
        String user = button_user.getText();
        Employess emp = EmployessServices.findEmployeeByUser(user);
        if (emp != null) {
            lb_id.setText(String.valueOf(emp.getId()));
            lb_first_name.setText(emp.getFirstName());
            lb_last_name.setText(emp.getLastName());
            lb_phone.setText(emp.getPhone());
            lb_email.setText(emp.getEmail());
        }
        else {
            ShowAlert.show("Thông tin không đúng!", Alert.AlertType.INFORMATION);
        }
    }

    public boolean isEqualUserCurrent(int id){
        if(id == EmployeesStore.getEmployess().getId()){
            return true;
        }
        return false;
    }

    public void remove(ActionEvent e) throws SQLException {
        int id = Integer.parseInt(lb_id.getText());
        if(!isEqualUserCurrent(id)){
            if(EmployessServices.removeById(id)){
                ShowAlert.show("Xóa thành công", Alert.AlertType.INFORMATION);
            }
            else {
                lb_id.setText("");
                lb_first_name.setText("");
                lb_last_name.setText("");
                lb_phone.setText("");
                lb_email.setText("");
                ShowAlert.show("Xóa thất bại", Alert.AlertType.WARNING);
            }
        }
        else {
            ShowAlert.show("Không thể xoa user đang đăng nhập", Alert.AlertType.WARNING);
        }
    }
}
