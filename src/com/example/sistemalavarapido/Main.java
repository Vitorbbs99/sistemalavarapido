package com.example.sistemalavarapido;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/login.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/resources/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Sistema Lava-Rápido");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
