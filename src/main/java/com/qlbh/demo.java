package com.qlbh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class demo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        String rm = "menu-admin-view.fxml";
        Parent roof = FXMLLoader.load(getClass().getResource(rm));
        Scene scene = new Scene(roof);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
