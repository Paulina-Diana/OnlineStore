package edu.uoc.onlinestore.model;


public class Item {
    private int code;
            private String description;
    private float salePrice, shippingCosts;
    private int preparationTime;

    public Item() {}
    public Item(int code) {
        this.code = code;
    }
    public Item(int code, String description, float salePrice, float shippingCosts, int preparationTime) {
        this.code = code;
        this.description = description;
        this.salePrice = salePrice;
        this.shippingCosts = shippingCosts;
        this.preparationTime = preparationTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    @Override
    public String toString() {
        return  "Code: " + code + ", " +
                "Description: " + description + ", " +
                "Sale price: " + salePrice + ", " +
                "Shipping costs: " + shippingCosts + ", " +
                "Preparation time: " + preparationTime ;
    }
}
