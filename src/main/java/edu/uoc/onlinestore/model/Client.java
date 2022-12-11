package edu.uoc.onlinestore.model;

public abstract class Client {
        private String name;
        private String address;
        private String nif;
        private String email;
    //----Builders
    public Client() {}
    public Client(String name, String address, String nif, String email) {
        this.name = name;
        this.address = address;
        this.nif = nif;
        this.email = email;

    }
    //----Abstract methods
    public abstract String clientType();
    public abstract float annualCalc();
    public abstract float shippingDiscount();
    //----Getters and setters
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
    //----To string
    @Override
    public String toString() {
        return  "Name: " + name + " " +
                "Address: " + address + " " +
                "NIF: " + nif + " " +
                "Email: " + email;
    }
}
