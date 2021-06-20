package com.example.jwtdemo.service.sdjpa;

import com.example.jwtdemo.domain.security.User;
import com.example.jwtdemo.repositories.sdjpa.UserJPARepo;
import com.example.jwtdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UserSDjpa implements UserService {

    private UserJPARepo userJPARepo;

    public UserSDjpa(UserJPARepo userJPARepo) {
        this.userJPARepo = userJPARepo;
    }

    @Override
    public User save(User object) {
        return userJPARepo.save(object);
    }

    @Override
    public User findById(Long aLong) {
        return userJPARepo.findById(aLong).orElse(null);
    }

    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        users.addAll(userJPARepo.findAll());
        return users;
    }

    @Override
    public void delete(User objectT) {
        userJPARepo.delete(objectT);
    }

    @Override
    public void deleteById(Long aLong) {
        userJPARepo.deleteById(aLong);
    }

    @Override
    public User findByUserName(String username) {
        return userJPARepo.findByUsername(username);
    }
}
