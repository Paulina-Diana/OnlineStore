package edu.uoc.onlinestore.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OnlineStoreFXMain extends Application {
    public static void main(String[] args) {
        Application.launch(OnlineStoreFXMain.class,args);
       // launch();


    }

    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader fxmlLoader = new FXMLLoader(OnlineStoreFXMain.class.getResource("OnlineStoreView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Online Store App");
        stage.setScene(scene);
        stage.show();

    }
}
