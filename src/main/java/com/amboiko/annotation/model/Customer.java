package com.amboiko.annotation.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Customer {
    private final String name;
    @Autowired
    private Cart cart;

    public Customer(String name) {
        this.name = name;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Customer{" + "name='" + name + '\'' +
                ", cart=" + cart +
                '}';
    }
}
