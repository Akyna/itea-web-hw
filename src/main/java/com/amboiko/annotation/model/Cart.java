package com.amboiko.annotation.model;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class Cart {
    private final int maxSize;
    @Autowired
    private Map<Product, Integer> products;

    public Cart(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public String toString() {
        return "Cart{" + "maxSize=" + maxSize +
                ", products=" + products +
                '}';
    }
}
