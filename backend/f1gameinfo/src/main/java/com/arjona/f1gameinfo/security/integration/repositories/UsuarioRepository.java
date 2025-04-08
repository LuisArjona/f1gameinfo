package com.arjona.f1gameinfo.security.integration.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arjona.f1gameinfo.business.model.Circuito;
import com.arjona.f1gameinfo.business.model.Piloto;
import com.arjona.f1gameinfo.business.model.UsuarioDTO;
import com.arjona.f1gameinfo.security.integration.model.Usuario;

/**
 * Repositorio que maneja todo lo
 * relacionado con los usuarios
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	/**
	 * Devuelve el usuario dado su username
	 * @param username del usuario
	 * @return Un {@code Optional} que contiene el usuario si se encuentra, o 
	 * {@code Optional.empty()} si no se encuentra ningún usuario con el nombre de usuario proporcionado.
	 */
	Optional<Usuario> findByUsername(String username);
	
	/**
	 * Comprueba si existe un usuario
	 * @param username del usuario
	 * @return Si se encuentra {@code True}, si no {@code False}
	 */
	boolean existsByUsername(String username);
	
	/**
	 * Devuelve las monedas de un usuario
	 * @param id del usuario
	 * @return {@code Integer} las monedas del usuario, o {@code Null} si no existe el usuario
	 */
	@Query("SELECT u.monedas FROM Usuario u WHERE u.id = :id")
	Integer findMonedasById(@Param("id") Long id);

	/**
	 * Devuelve el ranking de los usuarios
	 * ordenado por cantidad de cartas compradas
	 * de mas a menos
	 * @return {@code List} del ranking de usuarios
	 */
	@Query("SELECT new com.arjona.f1gameinfo.business.model.UsuarioDTO("
            + "u.username, "
            + "SIZE(u.pilotos), "
            + "SIZE(u.circuitos), "
            + "SIZE(u.pilotos) + SIZE(u.circuitos)) "
            + "FROM Usuario u "
            + "ORDER BY SIZE(u.pilotos) + SIZE(u.circuitos) DESC")
	List<UsuarioDTO> getAllUsuariosRanking();
	
	/**
	 * Devuelve los pilotos propiedad de un usuario
	 * @param id del usuario
	 * @return {@code Set} de pilotos
	 */
	@Query("SELECT u.pilotos FROM Usuario u WHERE u.username = :username")
	Set<Piloto> findPilotosByUsername(@Param("username") String username);

	/**
	 * Devuelve los circuitos propiedad de un usuario
	 * @param id del usuario
	 * @return {@code Set} de circuitos
	 */
	@Query("SELECT u.circuitos FROM Usuario u WHERE u.username = :username")
	Set<Circuito> findCircuitosByUsername(@Param("username") String username);
	
	/**
	 * Devuelve los pilotos propiedad de un usuario
	 * @param id del usuario
	 * @return {@code Set} de pilotos
	 */
	@Query("SELECT u.pilotos FROM Usuario u WHERE u.id = :id")
	Set<Piloto> findPilotosById(@Param("id") Long id);

	/**
	 * Devuelve los circuitos propiedad de un usuario
	 * @param id del usuario
	 * @return {@code Set} de circuitos
	 */
	@Query("SELECT u.circuitos FROM Usuario u WHERE u.id = :id")
	Set<Circuito> findCircuitosById(@Param("id") Long id);


}
