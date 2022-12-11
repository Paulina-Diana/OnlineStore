package edu.uoc.onlinestore.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class OnlineStoreFXController {

    private void item(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(OnlineStoreFXMain.class.getResource("ItemView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 783, 540);
            stage.setTitle("Item");
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void client(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(OnlineStoreFXMain.class.getResource("ClientView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 783, 540);
            stage.setTitle("Client");
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void order(){
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(OnlineStoreFXMain.class.getResource("OrderView.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 783, 540);
            stage.setTitle("Order");
            stage.setScene(scene);
            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    protected void viewItem(){
        item();


    }

    @FXML
    protected void viewClient(){

client();
    }

    @FXML
    protected void viewOrder(){
        order();

    }

    public void showItem(ContextMenuEvent contextMenuEvent) {
        item();
    }

    public void showClient(ContextMenuEvent contextMenuEvent) {
        client();
    }

    public void showOrder(ContextMenuEvent contextMenuEvent) {
        order();
    }
}
