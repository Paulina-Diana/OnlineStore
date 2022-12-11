package edu.uoc.onlinestore.model;

public class ClientPremiun extends Client {
    //----Variables
    private float quota;
    private float discount;
    //----Builders
    public ClientPremiun(){}
    public ClientPremiun(float quota, float discount) {
        this.quota = quota;
        this.discount = discount;
    }
    public ClientPremiun(String name, String address, String nif, String email,  float quota, float discount) {
        super(name, address, nif, email);
        this.quota = quota;
        this.discount = discount;
    }
    //----Abstract override methods
    public String clientType(){
        return "premium";
    }
    public float annualCalc(){
        return quota;
    }
    public float shippingDiscount(){
        return discount;
    }
    //----Getters and setters
    public float getQuota() {
        return quota;
    }
    public void setQuota(float quota) {
        this.quota = quota;
    }
    public float getDiscount() {
        return discount;
    }
    public void setDiscount(float discount) {
        this.discount = discount;
    }
    //----To String
    @Override
    public String toString() {
        return super.toString() + " " +
                "Quota: " + quota + " " +
                "Discount: " + discount;
    }
}
