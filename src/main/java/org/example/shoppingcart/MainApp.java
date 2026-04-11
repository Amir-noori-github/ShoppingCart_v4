package org.example.shoppingcart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Suppress Sonar warning: JavaFX Application must have a public constructor
@SuppressWarnings("java:S1118")
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/org/example/shoppingcart/shoppingcart-view.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("AMIR NOORI / Shopping Cart App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
