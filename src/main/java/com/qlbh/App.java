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
        window = FXMLLoader.load(getClass().getResource("choose-access-view.fxml"));
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
