package com.amboiko.xml.model;

public class Customer {
    private String name;
    private Cart cart;

    public Customer() {
    }

    public Customer(String name, Cart cart) {
        this.name = name;
        this.cart = cart;
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