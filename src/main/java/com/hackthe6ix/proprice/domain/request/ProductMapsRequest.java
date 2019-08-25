package com.hackthe6ix.proprice.domain.request;

public class ProductMapsRequest {

    private String product_name;
    private Double user_lat;
    private Double user_long;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getUser_lat() {
        return user_lat;
    }

    public void setUser_lat(Double user_lat) {
        this.user_lat = user_lat;
    }

    public Double getUser_long() {
        return user_long;
    }

    public void setUser_long(Double user_long) {
        this.user_long = user_long;
    }
}
