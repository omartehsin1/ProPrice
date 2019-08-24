package com.hackthe6ix.proprice.domain.response;

public class ProductMapsResponse {

    private Double dest_lat;
    private Double dest_long;

    private String name;
    private String address;
    private boolean isOpen;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDest_lat() {
        return dest_lat;
    }

    public void setDest_lat(Double dest_lat) {
        this.dest_lat = dest_lat;
    }

    public Double getDest_long() {
        return dest_long;
    }

    public void setDest_long(Double dest_long) {
        this.dest_long = dest_long;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
