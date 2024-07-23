package com.generation.zurah.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.zurah.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
    public List<Usuario> findByNameContainingIgnoreCase(@Param("name") String name);
    public Optional<Usuario> findByUsuario(String usuario);
    
}
