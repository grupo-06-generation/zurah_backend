package com.generation.zurah.service;

import java.util.Optional;

import com.generation.zurah.model.Usuario;
import com.generation.zurah.model.UsuarioLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.zurah.repository.UsuarioRepository;
import com.generation.zurah.security.JwtService;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public Optional<Usuario> registerUsuario(Usuario usuario) {
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();
		
		usuario.setPassword(encryptPassword(usuario.getPassword()));
		
		return Optional.of(usuarioRepository.save(usuario));
	}
	
	public Optional<Usuario> updateUsuario(Usuario usuario) {
		
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> searchUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			
			if((searchUsuario.isPresent()) && (searchUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists!", null);
			
			usuario.setPassword(encryptPassword(usuario.getPassword()));
			
			return Optional.ofNullable(usuarioRepository.save(usuario));
		}
		
		return Optional.empty();
	}
	
	public Optional<UsuarioLogin> authenticateUsuarios(Optional<UsuarioLogin> usuarioLogin){
		
		var credentials = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getPassword());
		
		Authentication authentication = authenticationManager.authenticate(credentials);
		
		if (authentication.isAuthenticated()) {
			
			Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
			
			if(usuario.isPresent()) {
				
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setName(usuario.get().getName());
				usuarioLogin.get().setPhoto(usuario.get().getPhoto());
				usuarioLogin.get().setIs_seller(usuario.get().getIs_seller());
				usuarioLogin.get().setToken(generateToken(usuarioLogin.get().getUsuario()));
				usuarioLogin.get().setPassword("");
				
				return usuarioLogin;
			}
		}
		
		return Optional.empty();
	}
	
	private String encryptPassword(String password) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(password);
	}
	
	private String generateToken(String usuario) {
		return "Bearer " + jwtService.generateToken(usuario);
	}
}
