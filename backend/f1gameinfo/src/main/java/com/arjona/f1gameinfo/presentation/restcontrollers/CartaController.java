package com.arjona.f1gameinfo.presentation.restcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.business.services.CartaServices;

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

}
