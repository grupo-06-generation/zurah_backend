package com.generation.zurah.controller;

import java.util.List;
import java.util.Optional;

import com.generation.zurah.model.Usuario;
import com.generation.zurah.repository.CategoryRepository;
import com.generation.zurah.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.zurah.model.Product;
import com.generation.zurah.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(productRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Long id) {
		return productRepository.findById(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Product>> getByName(@PathVariable String name) {
		return ResponseEntity.ok(productRepository.findAllByNameContainingIgnoreCase(name));

	}

	@PostMapping
    public ResponseEntity<Product> post(@Valid @RequestBody Product product) {
        if (!categoryRepository.existsById(product.getCategory().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found with ID " + product.getCategory().getId(), null);
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(product.getUsuario().getId());
        if (usuarioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with ID " + product.getUsuario().getId(), null);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productRepository.save(product));
    }

    @PutMapping
    public ResponseEntity<Product> put(@Valid @RequestBody Product product) {
        if (productRepository.existsById(product.getId())) {
            if (categoryRepository.existsById(product.getCategory().getId())) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productRepository.save(product));
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found with ID " + product.getCategory().getId(), null);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")

    public void Delete(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        productRepository.deleteById(id);
    }
}



