package com.generation.zurah.repository;

import com.generation.zurah.model.User;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<User, Long>{
	
    public Optional<User> findByUser(String userEmail);
}
