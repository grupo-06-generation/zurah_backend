package com.generation.zurah.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.generation.zurah.model.Category;
import com.generation.zurah.repository.CategoryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
	
	@Autowired
	public CategoryRepository categoryRepository;

	@GetMapping
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryRepository.findAll());
	}

	// get product or ID
	// http://localhost:8080/category/1
	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Long id) {
		return categoryRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	// get products where contain "name"
	// http://localhost:8080/category/name/a
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Category>> getByName(@PathVariable String name) {
		return ResponseEntity.ok(categoryRepository.findAllByNameContainingIgnoreCase(name));
	}

	@PostMapping
	public ResponseEntity<Category> post(@Valid @RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(category));
	}
	
	@PutMapping
	public ResponseEntity<Category> put(@Valid @RequestBody Category category) {
		return categoryRepository.findById(category.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(category)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		categoryRepository.deleteById(id);
	}

}
