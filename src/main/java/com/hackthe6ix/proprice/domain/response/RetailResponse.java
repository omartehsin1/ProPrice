package com.hackthe6ix.proprice.domain.response;

public class RetailResponse {

    public enum RETAIL_TYPE {
        BEST_BUY, AMAZON, WALMART;
    }

    public String price;
    public RETAIL_TYPE retail_type;

    public RETAIL_TYPE getRetail_type() {
        return retail_type;
    }

    public void setRetail_type(RETAIL_TYPE retail_type) {
        this.retail_type = retail_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
