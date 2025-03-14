package com.arjona.f1gameinfo.security.integration.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arjona.f1gameinfo.business.model.CartaCompradaDTO;
import com.arjona.f1gameinfo.business.model.UsuarioDTO;
import com.arjona.f1gameinfo.security.integration.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsername(String username);
	boolean existsByUsername(String username);
	Integer findMonedasById(Long id);
	
	@Query("SELECT new com.arjona.f1gameinfo.business.model.UsuarioDTO("
            + "u.username, "
            + "SIZE(u.pilotos), "
            + "SIZE(u.circuitos), "
            + "SIZE(u.pilotos) + SIZE(u.circuitos)) "
            + "FROM Usuario u "
            + "ORDER BY SIZE(u.pilotos) + SIZE(u.circuitos) DESC")
	List<UsuarioDTO> getAllUsuariosRanking();
	
	@Query("SELECT new com.arjona.f1gameinfo.business.model.CartasCompradasDTO("
	        + "u.pilotos, "
	        + "u.circuitos) "
	        + "FROM Usuario u "
	        + "WHERE u.id = :idUsuario")
	CartaCompradaDTO getCartasCompradasByUsuarioId(@Param("idUsuario") Long idUsuario);

}
