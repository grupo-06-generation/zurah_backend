package com.generation.zurah.repository;

import com.generation.zurah.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
    public Optional<Usuario> findByUsuario(String usuario);
}
