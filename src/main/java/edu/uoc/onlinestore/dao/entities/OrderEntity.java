package edu.uoc.onlinestore.dao.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codeOrder", nullable = false)
    private Integer code;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "email")
    private ClientEntity clientEntity;

    @ManyToOne
    @JoinColumn(name = "code")
    private ItemEntity itemEntity;

    @Column(name = "order_date_time" ,columnDefinition = "TIMESTAMP")
    private LocalDateTime orderDateTime;

    public OrderEntity() {

    }

    public OrderEntity(Integer code, Integer amount, LocalDateTime orderDateTime, ClientEntity clientEntity, ItemEntity itemEntity) {
        this.code = code;
        this.amount = amount;
        this.clientEntity = clientEntity;
        this.itemEntity = itemEntity;
        this.orderDateTime = orderDateTime;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "code=" + code +
                ", amount=" + amount +
                ", clientEntity=" + clientEntity +
                ", itemEntity=" + itemEntity +
                ", orderDateTime=" + orderDateTime +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }
}
