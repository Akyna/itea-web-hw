package com.amboiko.dao;

import com.amboiko.entity.User;

public interface UserDAO {
    long saveUser(User user);

    User getUserByEmailAndPassword(String email, String password);

    User getUserById(Long id);
}
