package com.amboiko.xml.model;

import java.util.Map;

public class Cart {
    private int maxSize;
    Map<Product, Integer> products;

    public Cart() {
    }

    public Cart(int maxSize, Map<Product, Integer> products) {
        this.maxSize = maxSize;
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" + "maxSize=" + maxSize +
                ", products=" + products +
                '}';
    }
}
