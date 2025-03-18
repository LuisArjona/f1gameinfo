package com.arjona.f1gameinfo.security.integration.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arjona.f1gameinfo.business.model.Circuito;
import com.arjona.f1gameinfo.business.model.Piloto;
import com.arjona.f1gameinfo.business.model.UsuarioDTO;
import com.arjona.f1gameinfo.security.integration.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByUsername(String username);
	boolean existsByUsername(String username);
	@Query("SELECT u.monedas FROM Usuario u WHERE u.id = :id")
	Integer findMonedasById(@Param("id") Long id);

	
	@Query("SELECT new com.arjona.f1gameinfo.business.model.UsuarioDTO("
            + "u.username, "
            + "SIZE(u.pilotos), "
            + "SIZE(u.circuitos), "
            + "SIZE(u.pilotos) + SIZE(u.circuitos)) "
            + "FROM Usuario u "
            + "ORDER BY SIZE(u.pilotos) + SIZE(u.circuitos) DESC")
	List<UsuarioDTO> getAllUsuariosRanking();
	
	@Query("SELECT u.pilotos FROM Usuario u WHERE u.id = :id")
	Set<Piloto> findPilotosById(@Param("id") Long id);

    
	@Query("SELECT u.circuitos FROM Usuario u WHERE u.id = :id")
	Set<Circuito> findCircuitosById(@Param("id") Long id);


}
