package com.generation.zurah.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.zurah.model.Category;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    public List<Category> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
