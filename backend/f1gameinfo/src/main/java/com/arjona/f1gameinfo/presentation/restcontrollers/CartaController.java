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

@CrossOrigin
@RequestMapping("/cartas")
@RestController
public class CartaController {
	
	private CartaServices cartaServices;
	
	public CartaController(CartaServices cartaServices) {
		this.cartaServices = cartaServices;
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> actualizarCartasUsuario(
			@PathVariable Long id, 
			@RequestParam(required = true) Integer monedas, 
            @RequestParam(required = false) Long idPiloto, 
            @RequestParam(required = false) Long idCircuito) {
		
		cartaServices.actualizarCartas(id, monedas, idPiloto, idCircuito);
		return ResponseEntity.ok("Usuario actualizado correctamente.");
	}
	
	@GetMapping("/usuarios")
	public List<CartaUsuarioDTO> getAllDtos(){
		return cartaServices.getAllDtos();
	}
	
	@GetMapping("/usuarios/{id}")
	public CartaUsuarioDTO getCartaUsuarioFromUsuario(@PathVariable Long id){
		return cartaServices.getCartaUsuario(id);
	}
	
	@GetMapping("/{id}")
	public CartaCompradaDTO getCompradasUsuario(@PathVariable Long id) {
		return cartaServices.getCartasCompradasFromUsuario(id);
	}
	
	@PostMapping("/uploads/{id}")
	public ResponseEntity<?> subirCarta(
			@PathVariable Long id, 
			@RequestParam(required = true) Integer valoracion, 
			@RequestParam(required = true) MultipartFile imagen) {
		
		cartaServices.subirCarta(id, valoracion, imagen);
		return ResponseEntity.ok("Carta creada correctamente.");
	}

}
