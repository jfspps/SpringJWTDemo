package com.example.jwtdemo.service.sdjpa;

import com.example.jwtdemo.domain.security.Authority;
import com.example.jwtdemo.repositories.sdjpa.AuthorityJPARepo;
import com.example.jwtdemo.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class AuthoritySDjpa implements AuthorityService {

    private final AuthorityJPARepo authorityJPARepo;

    public AuthoritySDjpa(AuthorityJPARepo authorityJPARepo) {
        this.authorityJPARepo = authorityJPARepo;
    }

    @Override
    public Authority save(Authority object) {
        return authorityJPARepo.save(object);
    }

    @Override
    public Authority findById(Long aLong) {
        return authorityJPARepo.findById(aLong).orElse(null);
    }

    @Override
    public Set<Authority> findAll() {
        Set<Authority> authorities = new HashSet<>();
        authorities.addAll(authorityJPARepo.findAll());
        return authorities;
    }

    @Override
    public void delete(Authority objectT) {
        authorityJPARepo.delete(objectT);
    }

    @Override
    public void deleteById(Long aLong) {
        authorityJPARepo.deleteById(aLong);
    }
}
