package com.generation.zurah.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.zurah.model.Category;
import com.generation.zurah.repository.CategoryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	public CategoryRepository categoryRepository;
	
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryRepository.findAll());
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
