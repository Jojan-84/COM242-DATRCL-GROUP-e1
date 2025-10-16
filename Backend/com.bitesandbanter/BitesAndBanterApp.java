package com.bitesandbanter;

import com.bitesandbanter.service.DataService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//Application Launcher - main

import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main application launcher.
 * NOTE: Ensure JavaFX modules (controls, fxml) are configured in your project.
 */
public class BitesAndBanterApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initialize the Singleton Data Service
        DataService.getInstance(); 
        
        // --- LINK TO YOUR INITIAL FXML HERE ---
        // Change the path below to your starting FXML file (e.g., /fxml/Login.fxml or /fxml/CustomerOrder.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/bitesandbanter/fxml/CustomerOrder.fxml"));
        
        Parent root = loader.load();
        
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Bites and Banter Ordering System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
