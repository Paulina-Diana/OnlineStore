package edu.uoc.onlinestore.controller;

import edu.uoc.onlinestore.dao.ClientDAO;
import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.OrderDAO;
import edu.uoc.onlinestore.dao.entities.ItemEntity;
import edu.uoc.onlinestore.dao.entities.ClientEntity;

import edu.uoc.onlinestore.dao.entities.OrderEntity;
import edu.uoc.onlinestore.dao.factory.DAOFactory;

import edu.uoc.onlinestore.view.OrderView;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.String;

public class OrderController {
    private OrderView orderView;
    private DAOFactory daoFactory;

    ClientDAO<ClientEntity> clientDAO;
    ItemDAO<ItemEntity> itemDAO;
    OrderDAO<OrderEntity> orderDAO;

    public OrderController(OrderView orderView) {
        this.orderView = orderView;
        daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL_JPA);
        this.clientDAO = daoFactory.getClientDAO();
        this.itemDAO = daoFactory.getItemDAO();
        this.orderDAO = daoFactory.getOrderDAO();
    }
    public void add() throws Exception{
        String [] orderData = orderView.addOrder();
        String code = orderData[0];
        String amount = orderData[1];
        String email = orderData[2];
        String codeItem = orderData[3];

        ClientEntity client = clientDAO.findById(email);
        ItemEntity item = itemDAO.findById(Integer.parseInt(codeItem));

        try {
            if(client != null && item != null){
                OrderEntity orderEntity = new OrderEntity();
                orderEntity.setItemEntity(item);
                orderEntity.setClientEntity(client);
                orderEntity.setOrderDateTime(LocalDateTime.now());
                orderEntity.setAmount(Integer.parseInt(amount));

                daoFactory.getOrderDAO().create(orderEntity);

                orderView.orderAdded();
            }
            else{
                throw new Exception();
            }

        }catch(Exception e){
            orderView.errorAdding(e);
        }

    }

    public void delete() throws Exception{
        String code = orderView.deleteOrder();
        OrderEntity optionalOrder = orderDAO.findById(Integer.parseInt(code));
        try {
            if(optionalOrder != null){
                OrderEntity newOrder = new OrderEntity();
                newOrder.setCode(Integer.parseInt(code));

                orderDAO.delete(newOrder);
                orderView.orderDeleted();
            }
            else {
                throw  new Exception();
            }
        }catch(Exception e){
            orderView.errorDeleting(e);
        }
    }

    public void update() throws Exception{
        String [] orderData = orderView.updateOrder();
        String code = orderData[0];
        String amount = orderData[1];
        String email = orderData[2];
        String codeItem = orderData[3];
        OrderEntity orderEntity = orderDAO.findById(Integer.parseInt(code));

        try {
            if(orderEntity != null){
                ItemEntity itemEntity =  itemDAO.findById(Integer.parseInt(codeItem));
                orderEntity.setItemEntity(itemEntity);
                orderEntity.setOrderDateTime(LocalDateTime.now());
                orderEntity.setAmount(Integer.parseInt(amount));

                orderDAO.update(orderEntity);
                orderView.orderUpdated();
            }
            else {
                throw new Exception();
            }
        }catch(Exception e){
            orderView.errorUpdating(e);
        }
    }
    public void show() throws Exception{
        List<OrderEntity> ordersData = orderDAO.findAll();
        List<String> orders = Collections.emptyList();
        if(ordersData != null)
            orders = ordersData.stream().map(c -> c.toString()).collect(Collectors.toList());
        orderView.show(orders);
    }

    //TODO: showPending and showShipped
   public void showPending() throws SQLException{

       List<String> ordersData =  orderDAO.findAll().stream()
               .filter(o ->{
                   int prepTime = o.getItemEntity().getPreparationTime();
                   return o.getOrderDateTime().plusMinutes(prepTime).isAfter(o.getOrderDateTime()) == false;
               })
               .map(o -> o.toString())
           .collect(Collectors.toList());

        orderView.show(ordersData);
    }

    public void showShipped() throws SQLException{

        List<String> ordersData =  orderDAO.findAll().stream()
                .filter(o ->{
                    int prepTime = o.getItemEntity().getPreparationTime();
                    return o.getOrderDateTime().plusMinutes(prepTime).isAfter(o.getOrderDateTime()) == true;
                })
                .map(o -> o.toString())
                .collect(Collectors.toList());

        orderView.show(ordersData);
        }

    }


