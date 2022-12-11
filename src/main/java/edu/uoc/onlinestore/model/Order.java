package edu.uoc.onlinestore.model;

import edu.uoc.onlinestore.dao.entity.ClientEntity;
import edu.uoc.onlinestore.dao.entities.ClientType;

import java.time.LocalDateTime;

public class Order {
    //----Variables
    private int code, amount;
    private ClientEntity client;
    private Item item;
    private LocalDateTime orderDateTime;
    //----Builders
    public Order() {}
    public Order(int code, int amount, ClientEntity client, Item item, LocalDateTime orderTime) {
        this.code = code;
        this.amount = amount;
        this.client = client;
        this.item = item;
        this.orderDateTime = orderTime;
    }
    public Order(int code, int amount, ClientEntity client, Item item) {
        this.code = code;
        this.amount = amount;
        this.client = client;
        this.item = item;
        this.orderDateTime = LocalDateTime.now();
    }
    //----Methods
    public boolean orderSent(){
        int prepTime = item.getPreparationTime();
        if(orderDateTime.plusMinutes(prepTime).isAfter(orderDateTime))
            return true;
        else
            return false;
    }
    public float shippingPrice(){
        //String clientType = client.clientType();
        ClientType clientType = client.getType();
        float quota = client.annualCalc();
        if (clientType.equals(ClientType.PREMIUM)&&quota>=30)
            return (item.getShippingCosts() - (item.getShippingCosts()*client.shippingDiscount()/100));
        else
            return item.getShippingCosts();
    }
    //----Getters and setters
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public ClientEntity getClient() {
        return client;
    }
    public void setClient(ClientEntity client) {
        this.client = client;
    }
    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }
    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }
    //----To string
    @Override
    public String toString() {
        return  "Code: " + code + " " +
                "Order date and time: " + orderDateTime + " " +
                "Client NIF: " + client.getNif() + " " +
                "Client name: " + client.getName() + " " +
                "Item code: " + item.getCode() + " " +
                "Item description: " + item.getDescription() + " " +
                "Amount: " + amount + " " +
                "Item price: " + item.getSalePrice() + " " +
                "Shipping costs: " + shippingPrice() + " " +
                "Total price: " + (shippingPrice()+item.getSalePrice()) + " " +
                "Sent: " + orderSent() + "";
    }
}
