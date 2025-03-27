package com.arjona.f1gameinfo.presentation.restcontrollers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.business.model.UsuarioDTO;
import com.arjona.f1gameinfo.business.services.UsuarioNoAuthServices;

/**
 * Controlador encargado de atender las
 * peticiones acerca de los usuarios
 * fuera de la autenticación
 */
@CrossOrigin
@RequestMapping("/usuarios")
@RestController
public class UsuarioNoAuthController {
	
	private UsuarioNoAuthServices usuarioNoAuthServices;

	public UsuarioNoAuthController(UsuarioNoAuthServices rankingServices) {
		this.usuarioNoAuthServices = rankingServices;
	}
	
	/**
	 * Atiende la petición para obtener
	 * el ranking de los usuarios
	 * 
	 * @return {@code 200 OK} con {@code List} con los usuarios
	 */
	@GetMapping("/ranking")
	public List<UsuarioDTO> getRanking(){
		return usuarioNoAuthServices.getRanking();
	}
	
	/**
	 * Atiende la petición para obtener
	 * las monedas de un usuario
	 * 
	 * @param id del usuario
	 * @return {@code 200 OK} con {@code Integer} monedas
	 */
	@GetMapping("/{id}/monedas")
	public Integer getMonedasFromUsuario(@PathVariable Long id) {
		return usuarioNoAuthServices.getMonedas(id);
	}

}
