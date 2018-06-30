package com.wojciechowski.project.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.wojciechowski.project.user.User;

public interface UserService extends UserDetailsService {
	User findByUserName(String username);
    void save(User user);
}
