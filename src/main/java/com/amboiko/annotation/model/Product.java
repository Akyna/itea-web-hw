package com.amboiko.annotation.model;

public class Product {
    private long id;
    private String name;
    private double price;

    public Product setId(long id) {
        this.id = id;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
