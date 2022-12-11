package edu.uoc.onlinestore.model;

import java.util.List;

public class Data {

    private ListClient listClient;

    private ListItem listItem;

    private ListOrder listOrder;

    public ListClient getListClient() {
        return listClient;
    }

    public void setListClient(ListClient listClient) {
        this.listClient = listClient;
    }

    public ListItem getListItem() {
        return listItem;
    }

    public void setListItem(ListItem listItem) {
        this.listItem = listItem;
    }

    public ListOrder getListOrder() {
        return listOrder;
    }

    public void setListOrder(ListOrder listOrder) {
        this.listOrder = listOrder;
    }

    public Data(){
        this.listClient = new ListClient();
        this.listItem = new ListItem();
        this.listOrder = new ListOrder();

    }

    public Data(ListClient listClient, ListItem listItem, ListOrder listOrder) {
        this.listClient = listClient;
        this.listItem = listItem;
        this.listOrder = listOrder;
    }

    public List<Client> showAllClient(){
        return listClient.getLists();
    }

    public List<Item> showAllItem(){
        return listItem.getLists();
    }

    public List<Order> showAllOrder(){
        return listOrder.getLists();
    }

}
