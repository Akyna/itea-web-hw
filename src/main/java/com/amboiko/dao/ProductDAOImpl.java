package com.amboiko.dao;

import com.amboiko.entity.Category;
import com.amboiko.entity.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private final SessionFactory sessionFactory;

    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getProducts(String categoryId) {
        List<Product> result = new ArrayList<>();

        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Category> category = cq.from(Category.class);
            Path<Product> productPath = category.get("products");
            cq.select(productPath);
            cq.where(cb.equal(category.get("id"), categoryId));
            Query<Product> query = session.createQuery(cq);
            List<Product> resultList = query.getResultList();

            result.addAll(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Product getProduct(String productId) {
        return null;
    }
}

