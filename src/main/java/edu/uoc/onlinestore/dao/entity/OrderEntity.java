package edu.uoc.onlinestore.dao.entity;

import java.time.LocalDateTime;

@Deprecated
public class OrderEntity {
    int codeOrder;
    int amount;
    String email;
    int code;
    LocalDateTime order_date_time;
    public OrderEntity() {
    }
    public OrderEntity(int codeOrder) {
        this.codeOrder = codeOrder;
    }
    public OrderEntity(int codeOrder, int amount, String email, int code, LocalDateTime order_date_time) {
        this.codeOrder = codeOrder;
        this.amount = amount;
        this.email = email;
        this.code = code;
        this.order_date_time = order_date_time;
    }

    public int getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(int codeOrder) {
        this.codeOrder = codeOrder;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getOrder_date_time() {
        return order_date_time;
    }

    public void setOrder_date_time(LocalDateTime order_date_time) {
        this.order_date_time = order_date_time;
    }

    @Override
    public String toString() {
        return "Order code: " + codeOrder + ", " +
                "Amount: " + amount + ", " +
                "Email: " + email + ", " +
                "Item code: " + code + ", " +
                "Order date time: " + order_date_time;
    }
}
