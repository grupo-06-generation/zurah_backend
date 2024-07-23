package com.generation.zurah.controller;

import java.util.List;
import java.util.Optional;

import com.generation.zurah.model.Usuario;
import com.generation.zurah.model.UsuarioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.zurah.repository.UsuarioRepository;
import com.generation.zurah.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Usuario>> getByName(@PathVariable String name) {
		return ResponseEntity.ok(usuarioRepository.findByNameContainingIgnoreCase(name));
	}

	@PostMapping("/login")
	public ResponseEntity<UsuarioLogin> authenticateUsuarios(@RequestBody Optional<UsuarioLogin> usuarioLogin) {
		return usuarioService.authenticateUsuarios(usuarioLogin)
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/register")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.registerUsuario(usuario)
				.map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping("/update")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return usuarioService.updateUsuario(usuario)
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}