package com.amboiko;

public abstract class AbstractProduct implements MainProduct {
    protected String name = "DEFAULT_PRODUCT";

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AbstractProduct{" + "name='" + getTitle() + '\'' +
                '}';
    }
}
