package com.arjona.f1gameinfo.security.integration.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arjona.f1gameinfo.security.integration.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsername(String username);
	boolean existsByUsername(String username);

}
