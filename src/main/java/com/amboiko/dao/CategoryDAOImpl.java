package com.amboiko.dao;

import com.amboiko.entity.Category;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private final SessionFactory sessionFactory;

    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> result = new ArrayList<>();

        try {
            Session session = sessionFactory.getCurrentSession();
            TypedQuery<Tuple> namedQuery = session.createNamedQuery("Category.getAll", Tuple.class);
            List<Tuple> results = namedQuery.getResultList();

            // "SELECT c.id, c.title, c.description, c.image"
            for (Tuple row : results) {
                result.add(new Category()
                        .setId((int) row.get(0))
                        .setTitle((String) row.get(1))
                        .setDescription((String) row.get(2))
                        .setImage((String) row.get(3))
                );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}

