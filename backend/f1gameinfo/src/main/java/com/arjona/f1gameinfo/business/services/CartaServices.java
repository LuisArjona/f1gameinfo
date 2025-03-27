package com.arjona.f1gameinfo.business.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.arjona.f1gameinfo.business.model.CartaCompradaDTO;
import com.arjona.f1gameinfo.business.model.CartaUsuarioDTO;
/**
 * Interfaz para el servicio que gestiona la
 * lógica de la aplicación sobre las cartas
 */
public interface CartaServices {
	
	/**
	 * Actualiza las cartas y/o monedas
	 * del usuario dado su id
	 * 
	 * @param id del usuario
	 * @param monedas del usuario
	 * @param idPiloto a comprar
	 * @param idCircuito a comprar
	 */
	void actualizarCartas(Long id, Integer monedas, Long idPiloto, Long idCircuito);
	
	/**
	 * Devuelve todas las cartas customizadas de los usuarios
	 * @return {@code List} con las cartas customizadas
	 */
	List<CartaUsuarioDTO> getAllDtos();
	
	/**
	 * Crea una carta customizada para
	 * el usuario dado su id
	 * 
	 * @param id del usuario
	 * @param valoracion de la carta
	 * @param imagen de la carta
	 */
	void subirCarta(Long id, Integer valoracion, MultipartFile imagen);
	
	/**
	 * Devuelve la carta customizada del
	 * usuario dado su id
	 * 
	 * @param id del usuario
	 * @return {@link CartaUsuarioDTO} carta customizada
	 */
	CartaUsuarioDTO getCartaUsuario(Long id);
	
	/**
	 * Devuelve las cartas compradas del
	 * usuario dado su id
	 * 
	 * @param id
	 * @return {@link CartaCompradaDTO} cartas compradas
	 */
	CartaCompradaDTO getCartasCompradasFromUsuario(Long id);

}
