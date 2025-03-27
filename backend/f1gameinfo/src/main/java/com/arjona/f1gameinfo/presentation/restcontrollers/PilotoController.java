package com.arjona.f1gameinfo.presentation.restcontrollers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.business.model.Piloto;
import com.arjona.f1gameinfo.business.services.PilotoServices;

/**
 * Controlador encargado de atender las
 * peticiones acerca de los pilotos
 */
@CrossOrigin
@RequestMapping("/pilotos")
@RestController
public class PilotoController {
	
	private PilotoServices pilotoServices;

	public PilotoController(PilotoServices pilotoServices) {
		this.pilotoServices = pilotoServices;
	}
	
	/**
	 * Atiende la petición para obtener
	 * todos los pilotos existentes
	 * 
	 * @return {@code 200 OK} con {@code List} de los pilotos
	 */
	@GetMapping
	public List<Piloto> getAll(){
		return pilotoServices.getAll();
	}
	
	

}
