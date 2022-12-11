package edu.uoc.onlinestore.dao.entities;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class ClientEntity {


    private String name;
    private String address;
    private String nif;
    @Id
    private String email;
    @Enumerated(EnumType.STRING)
    private ClientType type;
    private float quota;
    private float discount;

    public ClientEntity() {
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

    @Override
    public String toString() {
        return "ClientEntity{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nif='" + nif + '\'' +
                "email='" + email + '\'' +
                ", type=" + type +
                ", quota=" + quota +
                ", discount=" + discount +
                '}';
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
