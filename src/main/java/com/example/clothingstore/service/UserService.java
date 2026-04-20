package com.example.clothingstore.service;

import com.example.clothingstore.entity.User;
import java.util.List;

public interface UserService {
    User register(User user);
    User login(String username, String password);
    List<User> getAllUsers();
}