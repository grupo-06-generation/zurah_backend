package com.generation.zurah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.zurah.model.Product;
import com.generation.zurah.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok(productRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Long id){
		return productRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/name/{name}")
	public ResponseEntity <List<Product>> getByName(@PathVariable String name){
		return ResponseEntity.ok(productRepository.findAllByNameContainingIgnoreCase(name));

	}

	@PostMapping
	public ResponseEntity<Product> post(@Valid @RequestBody Product product){
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(product));

	}

}