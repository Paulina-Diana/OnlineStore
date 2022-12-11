package edu.uoc.onlinestore.dao.entity;

import edu.uoc.onlinestore.dao.entities.ClientType;
@Deprecated
public class ClientEntity {

    private String name;
    private String address;
    private String nif;
    private String email;
    private ClientType type;
    private float quota;
    private float discount;

    public ClientEntity(){

    }
    public ClientEntity(String email){
        this.email = email;
    }
    public ClientEntity(String name, String address, String nif, String email, ClientType type, float quota, float discount) {
        this.name = name;
        this.address = address;
        this.nif = nif;
        this.email = email;
        this.type = type;
        this.quota = quota;
        this.discount = discount;
    }

    public float annualCalc(){
        return quota;
    }
    public float shippingDiscount(){
        return discount;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", " +
                "Address: " + address + ", " +
                "Nif: " + nif + ", " +
                "Email: " + email + ", " +
                "Type: " + type + ", " +
                "Quota: " + quota + ", " +
                "Discount: " + discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

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
}
