package com.qlbh;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private Parent window;
    @Override
    public void start(Stage stage) throws Exception {
        //
        System.out.println(getClass().getResource("menu-admin-view.fxml").toString());
        System.out.println(getClass().toString());
        window = FXMLLoader.load(getClass().getResource("sign-view.fxml"));
        Scene scene = new Scene(window);
        stage.setTitle("Sign");
        stage.setScene(scene);
        stage.show();

        //

    }

    public static void main(String[] args) {
        launch(args);
    }
}
