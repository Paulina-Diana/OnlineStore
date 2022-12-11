package edu.uoc.onlinestore.controller;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.OrderDAO;
import edu.uoc.onlinestore.dao.entities.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ItemEntity;
import edu.uoc.onlinestore.dao.entities.OrderEntity;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.chrono.Chronology;
import java.util.ResourceBundle;

public class OrderFXController implements Initializable {

    private DAOFactory daoFactory;

    private OrderDAO<OrderEntity> orderDAO;

    private ClientDAO<ClientEntity> clientDAO;

    private ItemDAO<ItemEntity> itemDAO;

    @FXML
    private TableView<OrderEntity> tableOrders;
    @FXML
    public TableColumn<OrderEntity, Integer> codeOrder;

    @FXML
    public TableColumn<OrderEntity, Integer> amount;

    @FXML
    public TableColumn<OrderEntity, String> email;

    @FXML
    public TableColumn<OrderEntity, Integer> code;

    @FXML
    public TableColumn<OrderEntity, LocalDateTime> orderDateTime;






    @FXML
    private TextField txtCodeOrder;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtCodeItem;

    @FXML
    private TextField txtOrderDateTime;

    public OrderFXController() {
        System.out.println("load contructor");
        this.daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL_JPA);
        this.orderDAO = daoFactory.getOrderDAO();
        this.clientDAO = daoFactory.getClientDAO();
        this.itemDAO = daoFactory.getItemDAO();
    }

    private void loadData(){
        try {

            codeOrder.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCode()));
            amount.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAmount()));
            email.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getClientEntity().getEmail()));
            orderDateTime.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getOrderDateTime()));
            code.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getItemEntity().getCode()));

            tableOrders.setItems(FXCollections.observableList(orderDAO.findAll()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void saveOrder(ActionEvent actionEvent) {
        System.out.println("saveOrder "+txtCodeOrder.getText());

        try {

            ClientEntity clientEntity = clientDAO.findById(txtEmail.getText());
            ItemEntity itemEntity = itemDAO.findById(Integer.parseInt(txtCodeItem.getText()));

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setAmount(Integer.parseInt(txtAmount.getText()));
            orderEntity.setClientEntity(clientEntity);
            orderEntity.setItemEntity(itemEntity);
            orderEntity.setOrderDateTime(LocalDateTime.now());

            orderDAO.create(orderEntity);
            loadData();
            clearOrder(actionEvent);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateOrder(ActionEvent actionEvent) {
        try {
            System.out.println("updateOrder");
            OrderEntity orderEntity = orderDAO.findById(Integer.parseInt(txtCodeOrder.getText()));
            ItemEntity itemEntity = itemDAO.findById(Integer.parseInt(txtCodeItem.getText()));
            orderEntity.setItemEntity(itemEntity);
            orderEntity.setOrderDateTime(LocalDateTime.now());
            orderEntity.setAmount(Integer.parseInt(txtAmount.getText()));

            orderDAO.update(orderEntity);
            loadData();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void deleteOrder(ActionEvent actionEvent) {

        String code = txtCodeOrder.getText();
        try {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setCode(Integer.parseInt(code));

            orderDAO.delete(orderEntity);
            loadData();

        }catch(Exception e){

        }
        clearOrder(actionEvent);

    }

    public void clearOrder(ActionEvent actionEvent) {

        txtCodeOrder.setText("");
        txtAmount.setText("");
        txtEmail.setText("");
        txtCodeItem.setText("");
        txtOrderDateTime.setText("");

        System.out.println("newOrder");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    public void newOrder(ActionEvent actionEvent) {
        clearOrder(actionEvent);
    }

    public void selectOrder(MouseEvent mouseEvent) {

            OrderEntity orderEntity = tableOrders.getSelectionModel().getSelectedItem();
            txtCodeOrder.setText(orderEntity.getCode()+"");
            txtAmount.setText(orderEntity.getAmount()+"");
            txtEmail.setText(orderEntity.getClientEntity().getEmail());
            txtCodeItem.setText(orderEntity.getItemEntity().getCode()+"");
            txtOrderDateTime.setText( orderEntity.getOrderDateTime() == null ?"":orderEntity.getOrderDateTime().toString());

    }
}
