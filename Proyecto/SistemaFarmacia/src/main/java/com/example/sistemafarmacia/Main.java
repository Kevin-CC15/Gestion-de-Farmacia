package com.example.sistemafarmacia;

import com.example.sistemafarmacia.Vista.navegacion.ControladorNavegacion;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        new ControladorNavegacion(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
