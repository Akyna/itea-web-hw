package com.amboiko.services;

import com.amboiko.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;


@Service
public class ProductEntityManager {
    //    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");
    EntityManager manager = Persistence.createEntityManagerFactory("CRM").createEntityManager();

    public Product getProductById(int id) {
        return manager.find(Product.class, id);
    }
}
