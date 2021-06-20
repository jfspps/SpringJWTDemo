package com.example.jwtdemo.service;

import com.example.jwtdemo.domain.security.User;

public interface UserService extends BaseService<User, Long>{
    User findByUserName(String username);
}
