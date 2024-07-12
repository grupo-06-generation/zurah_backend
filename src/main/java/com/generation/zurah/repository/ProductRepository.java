package com.generation.zurah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.zurah.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	public List<Product> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
