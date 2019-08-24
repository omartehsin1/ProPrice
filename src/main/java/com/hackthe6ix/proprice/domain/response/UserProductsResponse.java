package com.hackthe6ix.proprice.domain.response;

import java.util.List;

public class UserProductsResponse {

    private List<String> encoded_images;

    public List<String> getEncoded_images() {
        return encoded_images;
    }

    public void setEncoded_images(List<String> encoded_images) {
        this.encoded_images = encoded_images;
    }
}
