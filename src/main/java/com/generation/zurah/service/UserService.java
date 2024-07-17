package com.generation.zurah.service;

import java.util.Optional;

import com.generation.zurah.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.zurah.model.UserLogin;
import com.generation.zurah.repository.UsuarioRepository;
import com.generation.zurah.security.JwtService;

@Service
public class UserService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public Optional<Usuario> registerUser(Usuario usuario) {
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();
		
		usuario.setPassword(encryptPassword(usuario.getPassword()));
		
		return Optional.of(usuarioRepository.save(usuario));
	}
	
	public Optional<Usuario> updateUser(Usuario usuario) {
		
		if (usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> searchUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			
			if((searchUsuario.isPresent()) && (searchUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists!", null);
			
			usuario.setPassword(encryptPassword(usuario.getPassword()));
			
			return Optional.ofNullable(usuarioRepository.save(usuario));
		}
		
		return Optional.empty();
	}
	
	public Optional<UserLogin> authenticateUsers(Optional<UserLogin> userLogin){
		
		var credentials = new UsernamePasswordAuthenticationToken(userLogin.get().getUsuario(), userLogin.get().getPassword());
		
		Authentication authentication = authenticationManager.authenticate(credentials);
		
		if (authentication.isAuthenticated()) {
			
			Optional<Usuario> user = usuarioRepository.findByUsuario(userLogin.get().getUsuario());
			
			if(user.isPresent()) {
				
				userLogin.get().setId(user.get().getId());
				userLogin.get().setName(user.get().getName());
				userLogin.get().setPhoto(user.get().getPhoto());
				userLogin.get().setIs_seller(user.get().getIs_seller());
				userLogin.get().setToken(generateToken(userLogin.get().getUsuario()));
				userLogin.get().setPassword("");
				
				return userLogin;
			}
		}
		
		return Optional.empty();
	}
	
	private String encryptPassword(String password) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(password);
	}
	
	private String generateToken(String user) {
		return "Bearer " + jwtService.generateToken(user);
	}
}
