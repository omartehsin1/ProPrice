package com.hackthe6ix.proprice.domain.response;

public class SSResponse {

    private String productName;
    private Double productPrice;
    private float confidence_score;

    public float getConfidence_score() {
        return confidence_score;
    }

    public void setConfidence_score(float confidence_score) {
        this.confidence_score = confidence_score;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
