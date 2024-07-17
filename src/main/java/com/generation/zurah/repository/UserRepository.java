package com.generation.zurah.repository;

import com.generation.zurah.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    public Optional<User> findByUser(String userEmail);
}
