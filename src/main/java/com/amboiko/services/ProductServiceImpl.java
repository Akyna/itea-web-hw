package com.amboiko.services;

import com.amboiko.dao.ProductDAO;
import com.amboiko.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public List<Product> getProducts(String categoryId) {
        return productDAO.getProducts(categoryId);
    }

    @Override
    @Transactional
    public Product getProduct(String productId) {
        return productDAO.getProduct(productId);
    }
}
