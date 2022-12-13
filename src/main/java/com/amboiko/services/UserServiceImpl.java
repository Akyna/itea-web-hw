package com.amboiko.services;

import com.amboiko.dao.UserDAO;
import com.amboiko.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    private UserDAO userDAO;

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public long saveUser(User user) {
        return userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public User getUserByEmailAndPassword(String email, String password) {
        return userDAO.getUserByEmailAndPassword(email, password);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }
}
