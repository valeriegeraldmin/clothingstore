package com.example.clothingstore.service;

import com.example.clothingstore.entity.User;

public interface UserService {
    User register(User user);
    User login(String username, String password);
}