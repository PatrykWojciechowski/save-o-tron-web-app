package com.wojciechowski.project.dao;

import com.wojciechowski.project.user.User;

public interface UserDAO {
    User findByUsername(String username);
    void save(User user);
}
