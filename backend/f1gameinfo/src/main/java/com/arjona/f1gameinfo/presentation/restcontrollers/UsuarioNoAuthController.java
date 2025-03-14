package com.arjona.f1gameinfo.presentation.restcontrollers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.business.model.UsuarioDTO;
import com.arjona.f1gameinfo.business.services.UsuarioNoAuthServices;

@RequestMapping("/usuarios")
@RestController
public class UsuarioNoAuthController {
	
	private UsuarioNoAuthServices usuarioNoAuthServices;

	public UsuarioNoAuthController(UsuarioNoAuthServices rankingServices) {
		this.usuarioNoAuthServices = rankingServices;
	}
	
	@GetMapping("/ranking")
	public List<UsuarioDTO> getRanking(){
		return usuarioNoAuthServices.getRanking();
	}
	
	@GetMapping("/{id}/monedas")
	public Integer getMonedasFromUsuario(@PathVariable Long id) {
		return usuarioNoAuthServices.getMonedas(id);
	}

}
