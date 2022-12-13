package com.amboiko.dao;

import com.amboiko.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getProducts(String categoryId);

    Product getProduct(String productId);
}
