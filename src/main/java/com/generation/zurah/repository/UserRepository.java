package com.generation.zurah.repository;

import com.generation.zurah.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    public Optional<User> findByUser(String userEmail);
}
