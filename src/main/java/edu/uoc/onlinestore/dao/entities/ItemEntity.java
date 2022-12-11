package edu.uoc.onlinestore.dao.entities;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code", nullable = false)
    private Integer code;

    private String description;

    @Column(name = "sale_price")
    private float salePrice;

    @Column(name = "shipping_costs")
    private float shippingCosts;

    @Column(name = "preparation_time")
    private int preparationTime;


    public ItemEntity(Integer code, String description, float salePrice, float shippingCosts, int preparationTime) {
        this.code = code;
        this.description = description;
        this.salePrice = salePrice;
        this.shippingCosts = shippingCosts;
        this.preparationTime = preparationTime;
    }

    public ItemEntity() {

    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", salePrice=" + salePrice +
                ", shippingCosts=" + shippingCosts +
                ", preparationTime=" + preparationTime +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public float getShippingCosts() {
        return shippingCosts;
    }

    public void setShippingCosts(float shippingCosts) {
        this.shippingCosts = shippingCosts;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }
}
