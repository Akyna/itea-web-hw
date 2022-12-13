package com.amboiko.model;

public class AddProductResponse {
    public String message;
    public int quantity;

    public AddProductResponse(String message, int quantity) {
        this.message = message;
        this.quantity = quantity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
