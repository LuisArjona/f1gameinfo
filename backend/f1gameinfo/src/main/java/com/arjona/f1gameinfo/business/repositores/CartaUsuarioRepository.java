package com.arjona.f1gameinfo.business.repositores;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arjona.f1gameinfo.business.model.CartaUsuario;
import com.arjona.f1gameinfo.business.model.CartaUsuarioDTO;

public interface CartaUsuarioRepository extends JpaRepository<CartaUsuario, Long>{
	
	@Query("SELECT new com.arjona.f1gameinfo.business.model.CartaUsuarioDTO("
			+ "c.valoracion, "
			+ "c.rutaImagen, "
			+ "c.usuario.username) "
			+ "FROM CartaUsuario c")
	List<CartaUsuarioDTO> getAllDtos();
	
	@Query("SELECT new com.arjona.f1gameinfo.business.model.CartaUsuarioDTO("
	        + "c.valoracion, "
	        + "c.rutaImagen, "
	        + "c.usuario.username) "
	        + "FROM CartaUsuario c "
	        + "WHERE c.usuario.id = :idUsuario")
	Optional<CartaUsuarioDTO> getCartaUsuarioFromUsuario(@Param("idUsuario") Long idUsuario);

}
