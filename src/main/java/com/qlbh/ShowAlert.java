package com.qlbh;

import javafx.scene.control.Alert;

public class ShowAlert {

    public static void show(String content, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setContentText(content);
        alert.show();
    }
}
