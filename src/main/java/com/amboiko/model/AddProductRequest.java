package com.amboiko.model;

import java.io.Serializable;

public class AddProductRequest implements Serializable {
    private static final long serialVersionUID = -2097885019886999473L;
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
