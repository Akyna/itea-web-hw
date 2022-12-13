package com.amboiko.services;

import com.amboiko.entity.User;

public interface UserService {
    long saveUser(User user);

    User getUserByEmailAndPassword(String email, String password);

    User getUserById(Long id);
}
