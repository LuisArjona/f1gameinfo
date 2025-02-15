package com.arjona.f1gameinfo.business.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arjona.f1gameinfo.business.model.CartaUsuario;
import com.arjona.f1gameinfo.business.model.CartaUsuarioDTO;

public interface CartaUsuarioRepository extends JpaRepository<CartaUsuario, Long>{
	
	@Query("SELECT new com.arjona.f1gameinfo.business.model.CartaUsuarioDTO("
			+ "c.valoracion, "
			+ "c.rutaImagen, "
			+ "c.usuario.username) "
			+ "FROM CartaUsuario c")
	List<CartaUsuarioDTO> getAllDtos();

}
