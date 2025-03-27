package com.arjona.f1gameinfo.presentation.restcontrollers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arjona.f1gameinfo.business.model.CartaCompradaDTO;
import com.arjona.f1gameinfo.business.model.CartaUsuarioDTO;
import com.arjona.f1gameinfo.business.services.CartaServices;

/**
 * Controlador encargado de atender las
 * peticiones acerca de las cartas
 */
@CrossOrigin
@RequestMapping("/cartas")
@RestController
public class CartaController {
	
	private CartaServices cartaServices;
	
	public CartaController(CartaServices cartaServices) {
		this.cartaServices = cartaServices;
	}

	/**
	 * Atiende la petición de actualizar las 
	 * cartas y/o monedas de un usuario
	 * 
	 * @param id del usuario
	 * @param monedas del usuario
	 * @param idPiloto a comprar
	 * @param idCircuito a comprar
	 * @return {@code 200 OK}
	 */
	@PatchMapping("/{id}")
	public ResponseEntity<?> actualizarCartasUsuario(
			@PathVariable Long id, 
			@RequestParam(required = true) Integer monedas, 
            @RequestParam(required = false) Long idPiloto, 
            @RequestParam(required = false) Long idCircuito) {
		
		cartaServices.actualizarCartas(id, monedas, idPiloto, idCircuito);
		return ResponseEntity.ok("Usuario actualizado correctamente.");
	}
	
	/**
	 * Atiende la petición para obtener
	 * las cartas customizadas de todos los usuarios
	 * 
	 * @return {@code 200 OK} con {@code List} con las cartas customizadas
	 */
	@GetMapping("/usuarios")
	public List<CartaUsuarioDTO> getAllDtos(){
		return cartaServices.getAllDtos();
	}
	
	/**
	 * Atiende la petición para obtener
	 * las carta customizada de un usuario
	 * 
	 * @param id del usuario
	 * @return {@code 200 OK} con las carta customizada
	 */
	@GetMapping("/usuarios/{id}")
	public CartaUsuarioDTO getCartaUsuarioFromUsuario(@PathVariable Long id){
		return cartaServices.getCartaUsuario(id);
	}
	
	/**
	 * Atiende la petición para obtener las 
	 * cartas compradas de un usuario
	 * 
	 * @param id del usuario
	 * @return {@code 200 OK} con {@code List} con las cartas compradas
	 */
	@GetMapping("/{id}")
	public CartaCompradaDTO getCompradasUsuario(@PathVariable Long id) {
		return cartaServices.getCartasCompradasFromUsuario(id);
	}
	
	/**
	 * Atiende la petición para crear una
	 * carta customizada para el usuario
	 * 
	 * @param id del usuario
	 * @param valoracion de la carta
	 * @param imagen de la carta
	 * @return {@code 200 OK}
	 */
	@PostMapping("/uploads/{id}")
	public ResponseEntity<?> subirCarta(
			@PathVariable Long id, 
			@RequestParam(required = true) Integer valoracion, 
			@RequestParam(required = true) MultipartFile imagen) {
		
		cartaServices.subirCarta(id, valoracion, imagen);
		return ResponseEntity.ok("Carta creada correctamente.");
	}

}
