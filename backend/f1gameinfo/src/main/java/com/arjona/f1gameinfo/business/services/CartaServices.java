package com.arjona.f1gameinfo.business.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.arjona.f1gameinfo.business.model.CartaCompradaDTO;
import com.arjona.f1gameinfo.business.model.CartaUsuarioDTO;

public interface CartaServices {
	
	void actualizarCartas(Long id, Integer monedas, Long idPiloto, Long idCircuito);
	
	List<CartaUsuarioDTO> getAllDtos();
	
	void subirCarta(Long id, Integer valoracion, MultipartFile imagen);
	
	CartaUsuarioDTO getCartaUsuario(Long id);
	
	CartaCompradaDTO getCartasCompradasFromUsuario(Long id);

}
