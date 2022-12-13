package com.amboiko.services;

import com.amboiko.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(String categoryId);

    Product getProduct(String productId);
}
