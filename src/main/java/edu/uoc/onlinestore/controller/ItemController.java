package edu.uoc.onlinestore.controller;

import edu.uoc.onlinestore.dao.ItemDAO;
import edu.uoc.onlinestore.dao.entities.ItemEntity;
import edu.uoc.onlinestore.dao.factory.DAOFactory;
import edu.uoc.onlinestore.model.*;
import edu.uoc.onlinestore.view.ItemView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ItemController  {
    private ItemView itemView;
    private DAOFactory daoFactory;

    private ItemDAO<ItemEntity> itemDAO;

    public ItemController(ItemView itemView) {
        this.itemView = itemView;
        this.daoFactory = DAOFactory.getDAOFactory(DAOFactory.Factory.MYSQL_JPA);
        this.itemDAO = daoFactory.getItemDAO();

    }
    public void add() throws Exception {
        String [] itemData = itemView.addItem();
        String code = itemData[0];
        String description = itemData[1];
        String salePrice = itemData[2];
        String shippingCosts = itemData[3];
        String preparationTime = itemData[4];

        try {
                ItemEntity newItem = new ItemEntity(Integer.parseInt(code), description, Float.parseFloat(salePrice), Float.parseFloat(shippingCosts), Integer.parseInt(preparationTime));
                itemDAO.create(newItem);
                itemView.itemAdded();

        }catch(Exception e){
            itemView.errorAdding(e);
        }
    }

    public void delete() throws Exception{
        String code = itemView.deleteItem();
        try {
                ItemEntity newItem = new ItemEntity();
                newItem.setCode(Integer.parseInt(code));
                itemDAO.delete(newItem);
                itemView.itemDeleted();

        }catch(Exception e){
            itemView.errorDeleting(e);
        }
    }

    public void update() throws Exception{
        String [] itemData = itemView.updateItem();
        String code = itemData[0];
        String description = itemData[1];
        String salePrice = itemData[2];
        String shippingCosts = itemData[3];
        String preparationTime = itemData[4];
        Optional<ItemEntity> item = itemDAO.findAll().stream().filter(c-> c.getCode() == Integer.parseInt(code)).findFirst();

        try {
            if(item.isPresent()){
                ItemEntity itemEntity = item.get();
                itemEntity.setDescription(description);
                itemEntity.setSalePrice( Float.parseFloat(salePrice));
                itemEntity.setShippingCosts(Float.parseFloat(shippingCosts));
                itemEntity.setPreparationTime(Integer.parseInt(preparationTime));
                itemDAO.update(itemEntity);
                itemView.itemUpdated();
            }
            else {
                throw  new Exception();
            }
        }catch(Exception e){
            itemView.errorUpdating(e);
        }
    }

    public void show() throws Exception{
        List<ItemEntity> itemsData = itemDAO.findAll();
        List<String> items = Collections.emptyList();
        if(itemsData != null)
            items = itemsData.stream().map(c -> c.toString()).collect(Collectors.toList());

        itemView.show(items);
    }
}
