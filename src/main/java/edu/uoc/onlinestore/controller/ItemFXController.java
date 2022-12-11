package edu.uoc.onlinestore.controller;

import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.entities.ItemEntity;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ItemFXController implements Initializable {


    private DAOFactory daoFactory;

    private ItemDAO<ItemEntity> itemDAO;


    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtSale;

    @FXML
    private TextField txtPreparation;

    @FXML
    private TextField txtShipping;


    @FXML
    private TableView<ItemEntity> tableItems;
    @FXML
    public TableColumn<ItemEntity, Integer> columnCode;

    @FXML
    public TableColumn<ItemEntity, String> columnDescription;

    @FXML
    public TableColumn<ItemEntity, Float> columnSale;

    @FXML
    public TableColumn<ItemEntity, Float> columnShipping;

    @FXML
    public TableColumn<ItemEntity, Integer> columnPreparation;




    public ItemFXController() {
        System.out.println("load contructor");
        this.daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL_JPA);
        this.itemDAO = daoFactory.getItemDAO();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("load initilize");

            loadData();

    }

    private void loadData(){
        try {

            columnCode.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCode()));
            columnDescription.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDescription()));
            columnSale.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getSalePrice()));
            columnPreparation.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getPreparationTime()));
            columnShipping.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getShippingCosts()));

            tableItems.setItems(FXCollections.observableList(itemDAO.findAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void saveItem(ActionEvent actionEvent){

        System.out.println("save item from ItemView.fxml");

        String description = txtDescription.getText();
        String salePrice = txtSale.getText();
        String shippingCosts = txtShipping.getText();
        String preparationTime = txtPreparation.getText();

        try {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setDescription(description);
            itemEntity.setPreparationTime(Integer.parseInt(preparationTime));
            itemEntity.setSalePrice(Float.parseFloat(salePrice));
            itemEntity.setShippingCosts(Float.parseFloat(shippingCosts));
            itemDAO.create(itemEntity);


        }catch(Exception e){

        }
        clearItem(actionEvent);
        loadData();


    }

    @FXML
    protected void updateItem(ActionEvent actionEvent){

        String code = txtCode.getText();
        String description = txtDescription.getText();
        String salePrice = txtSale.getText();
        String shippingCosts = txtShipping.getText();
        String preparationTime = txtPreparation.getText();

        try {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.setCode(Integer.parseInt(code));
            itemEntity.setDescription(description);
            itemEntity.setPreparationTime(Integer.parseInt(preparationTime));
            itemEntity.setSalePrice(Float.parseFloat(salePrice));
            itemEntity.setShippingCosts(Float.parseFloat(shippingCosts));
            itemDAO.update(itemEntity);


        }catch(Exception e){

        }

        loadData();


    }


    public void selectItem(MouseEvent mouseEvent) {

       ItemEntity itemEntity = tableItems.getSelectionModel().getSelectedItem();
       txtCode.setText(String.valueOf(itemEntity.getCode()));
       txtDescription.setText(itemEntity.getDescription());
       txtPreparation.setText(String.valueOf(itemEntity.getPreparationTime()));
       txtSale.setText(String.valueOf(itemEntity.getSalePrice()));
       txtShipping.setText(String.valueOf(itemEntity.getShippingCosts()));

    }

    public void deleteItem(ActionEvent actionEvent) {

        String code = txtCode.getText();
        try {
            ItemEntity newItem = new ItemEntity();
            newItem.setCode(Integer.parseInt(code));
            itemDAO.delete(newItem);
            loadData();

        }catch(Exception e){

        }
        clearItem(actionEvent);

    }

    public void clearItem(ActionEvent actionEvent) {

        txtCode.setText("");
        txtDescription.setText("");
        txtPreparation.setText("");
        txtSale.setText("");
        txtShipping.setText("");

    }
}
