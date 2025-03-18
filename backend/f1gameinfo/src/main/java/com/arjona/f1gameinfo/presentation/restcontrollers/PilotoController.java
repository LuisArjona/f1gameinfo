package com.arjona.f1gameinfo.presentation.restcontrollers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.business.model.Piloto;
import com.arjona.f1gameinfo.business.services.PilotoServices;

@CrossOrigin
@RequestMapping("/pilotos")
@RestController
public class PilotoController {
	
	private PilotoServices pilotoServices;

	public PilotoController(PilotoServices pilotoServices) {
		this.pilotoServices = pilotoServices;
	}
	
	@GetMapping
	public List<Piloto> getAll(){
		return pilotoServices.getAll();
	}
	
	

}
