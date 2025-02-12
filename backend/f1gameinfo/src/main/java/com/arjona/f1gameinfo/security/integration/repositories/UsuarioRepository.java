package com.arjona.f1gameinfo.security.integration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arjona.f1gameinfo.business.model.UsuarioDTO;
import com.arjona.f1gameinfo.security.integration.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsername(String username);
	boolean existsByUsername(String username);
	
	@Query("SELECT new com.arjona.f1gameinfo.business.model.UsuarioDTO("
            + "u.username, "
            + "SIZE(u.pilotos), "
            + "SIZE(u.circuitos), "
            + "SIZE(u.pilotos) + SIZE(u.circuitos)) "
            + "FROM Usuario u "
            + "ORDER BY SIZE(u.pilotos) + SIZE(u.circuitos) DESC")
	List<UsuarioDTO> getAllUsuariosRanking();

}
