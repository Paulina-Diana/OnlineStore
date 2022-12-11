package edu.uoc.onlinestore.model;

public class ClientStandard extends Client {
    //----Builder

    public ClientStandard(){}

    public ClientStandard(String name, String address, String nif, String email) {
        super(name, address, nif, email);
    }
    public ClientStandard(String name, String address) {
        super(name, address, "", "");
    }
    //----Abstract override methods
    public String clientType(){
        return "standard";
    }
    public float annualCalc(){
        return 0F;
    }
    public float shippingDiscount(){
        return 0F;
    }
    //----To string
    @Override
    public String toString() {
        return super.toString();
    }
}
