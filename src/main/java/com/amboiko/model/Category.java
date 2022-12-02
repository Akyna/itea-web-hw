package com.amboiko.model;

public class Category {
    private long id;
    private String title;
    private String description;
    private String image;

    public long getId() {
        return id;
    }

    public Category setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Category setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Category setImage(String image) {
        this.image = image;
        return this;
    }
}
