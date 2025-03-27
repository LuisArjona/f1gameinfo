package com.arjona.f1gameinfo.business.repositores;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arjona.f1gameinfo.business.model.CartaUsuario;
import com.arjona.f1gameinfo.business.model.CartaUsuarioDTO;

/**
 * Repositorio para las cartas
 * customizadas de los usuarios
 */
@Repository
public interface CartaUsuarioRepository extends JpaRepository<CartaUsuario, Long>{
	
	/**
	 * Devuelve todas las cartas customizadas de los usuarios
	 * @return Una {@code List} de todas las cartas customizadas de los usuarios
	 */
	@Query("SELECT new com.arjona.f1gameinfo.business.model.CartaUsuarioDTO("
			+ "c.valoracion, "
			+ "c.rutaImagen, "
			+ "c.usuario.username) "
			+ "FROM CartaUsuario c")
	List<CartaUsuarioDTO> getAllDtos();
	
	/**
	 * Encuentra la carta customizada del usuario
	 * @param idUsuario
	 * @return Un {@code Optional} que contiene la carta usuario si se encuentra, o 
	 * {@code Optional.empty()} si no se encuentra la carta del usuario proporcionado.
	 */
	@Query("SELECT new com.arjona.f1gameinfo.business.model.CartaUsuarioDTO("
	        + "c.valoracion, "
	        + "c.rutaImagen, "
	        + "c.usuario.username) "
	        + "FROM CartaUsuario c "
	        + "WHERE c.usuario.id = :idUsuario")
	Optional<CartaUsuarioDTO> getCartaUsuarioFromUsuario(@Param("idUsuario") Long idUsuario);

}
