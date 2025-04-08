package com.arjona.f1gameinfo.business.services;

import java.util.List;

import com.arjona.f1gameinfo.business.model.RankingDTO;
import com.arjona.f1gameinfo.business.model.UsuarioDTO;

/**
 * Interfaz para el servicio que gestiona la
 * lógica de la aplicación sobre los usuarios
 * fuera de la autenticación
 */
public interface UsuarioNoAuthServices {
	/**
	 * Devuelve el ranking de
	 * los usuarios
	 * 
	 * @return {@code List} del ranking
	 */
	List<RankingDTO> getRanking();
	
	/**
	 * Devuelve las monedas del
	 * usuario dado su id
	 * 
	 * @param id del usuario
	 * @return {@code Integer} monedas
	 */
	Integer getMonedas(Long id);

}
