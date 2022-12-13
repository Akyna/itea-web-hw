package com.amboiko.dao;

import com.amboiko.entity.User;
import com.amboiko.services.EncodingService;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long saveUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        return (long) currentSession.save(user);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);

            Predicate[] predicates = new Predicate[2];
            predicates[0] = cb.equal(root.get("email"), email);
            predicates[1] = cb.equal(root.get("password"), EncodingService.md5EncryptionWithSalt(password));
            cq.select(root).where(predicates);

            // TODO: @AngryDev2022
            //  import javax.persistence.Query; or import org.hibernate.query.Query; ???
            Query query = session.createQuery(cq);
            user = (User) query.getSingleResult();
        } catch (NoResultException ignored) {
        }

        return user;
    }

    @Override
    public User getUserById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, id);
    }
}

