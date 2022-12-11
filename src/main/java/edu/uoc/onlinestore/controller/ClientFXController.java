package edu.uoc.onlinestore.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.entities.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ClientType;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientFXController implements Initializable {

    private DAOFactory daoFactory;

    private ClientDAO<ClientEntity> clientDAO;


    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtNif;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtQuota;

    @FXML
    private TextField txtDiscount;


    @FXML
    private TableView<ClientEntity> tableClients;

    @FXML
    public TableColumn<ClientEntity, String> columnName;

    @FXML
    public TableColumn<ClientEntity, String> columnAddress;

    @FXML
    public TableColumn<ClientEntity, String> columnNif;

    @FXML
    public TableColumn<ClientEntity, String> columnEmail;

    @FXML
    public TableColumn<ClientEntity, Enum<ClientType>> columnType;

    @FXML
    public TableColumn<ClientEntity, Float> columnQuota;

    @FXML
    public TableColumn<ClientEntity, Float> columnDiscount;


    public ClientFXController() {
        System.out.println("load contructor");
        this.daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL_JPA);
        this.clientDAO = daoFactory.getClientDAO();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("load initilize");
        loadData();
    }
    private void loadData(){

        try {

            columnName.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getName()));
            columnAddress.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAddress()));
            columnNif.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getNif()));
            columnEmail.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getEmail()));
            columnType.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getType()));
            columnQuota.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getQuota()));
            columnDiscount.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDiscount()));

            tableClients.setItems(FXCollections.observableList(clientDAO.findAll()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void saveClient(ActionEvent actionEvent) {

        String name = txtName.getText();
        String address = txtAddress.getText();
        String nif = txtNif.getText();
        String email = txtEmail.getText();
        String type = txtType.getText();
        String quota = txtQuota.getText();
        String discount = txtDiscount.getText();

        ClientType finalType = ClientType.STANDARD;

        if (type != null && !type.isEmpty() &&  type.equalsIgnoreCase("PREMIUM"))
            finalType = ClientType.PREMIUM;


        try {
            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setName(name);
            clientEntity.setAddress(address);
            clientEntity.setNif(nif);
            clientEntity.setEmail(email);
            clientEntity.setType(finalType);
            clientEntity.setQuota(Float.parseFloat(quota));
            clientEntity.setDiscount(Float.parseFloat(discount));
            clientDAO.create(clientEntity);


        } catch (Exception e) {

        }
        clearClient(actionEvent);
        loadData();

    }

    @FXML
    protected void updateClient(ActionEvent actionEvent) {

        String name = txtName.getText();
        String address = txtAddress.getText();
        String nif = txtNif.getText();
        String email = txtEmail.getText();
        String type = txtType.getText();
        String quota = txtQuota.getText();
        String discount = txtDiscount.getText();

        ClientType finalType = ClientType.STANDARD;

        if (type != null && !type.isEmpty() &&  type.equalsIgnoreCase("PREMIUM"))
            finalType = ClientType.PREMIUM;


        try {
            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setName(name);
            clientEntity.setAddress(address);
            clientEntity.setNif(nif);
            clientEntity.setEmail(email);
            clientEntity.setType(finalType);
            clientEntity.setQuota(Float.parseFloat(quota));
            clientEntity.setDiscount(Float.parseFloat(discount));
            clientDAO.update(clientEntity);


        }catch(Exception e){

        }

        loadData();
    }

    public void selectClient(MouseEvent mouseEvent) {

        ClientEntity clientEntity = tableClients.getSelectionModel().getSelectedItem();
        txtName.setText(clientEntity.getName());
        txtAddress.setText(clientEntity.getAddress());
        txtNif.setText(clientEntity.getNif());
        txtEmail.setText(String.valueOf(clientEntity.getEmail()));
        txtType.setText(String.valueOf(clientEntity.getType()));
        txtQuota.setText(String.valueOf(clientEntity.getQuota()));
        txtDiscount.setText(String.valueOf(clientEntity.getDiscount()));

    }

    public void deleteClient(ActionEvent actionEvent) {

        String email = txtEmail.getText();
        try {
            ClientEntity newClient = new ClientEntity();
            newClient.setEmail(email);
            clientDAO.delete(newClient);
            loadData();

        }catch(Exception e){

        }
        clearClient(actionEvent);

    }

    public void clearClient(ActionEvent actionEvent) {

        txtName.setText("");
        txtAddress.setText("");
        txtNif.setText("");
        txtEmail.setText("");
        txtType.setText("");
        txtQuota.setText("");
        txtDiscount.setText("");

    }

}
