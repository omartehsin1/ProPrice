package com.hackthe6ix.proprice.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    //Account info
    @Id
    @GeneratedValue
    private int userId;

    private String name;
    private String emailAddress;
    private String phoneNumber;

    //Product
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productsSearched;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Product> getProductsSearched() {
        return productsSearched;
    }

    public void setProductsSearched(List<Product> productsSearched) {
        this.productsSearched = productsSearched;
    }
}
