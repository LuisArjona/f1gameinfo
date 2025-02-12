package com.arjona.f1gameinfo.presentation.restcontrollers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.business.model.Circuito;
import com.arjona.f1gameinfo.business.services.CircuitoServices;

@RequestMapping("/circuitos")
@RestController
public class CircuitoController {
	
	private CircuitoServices circuitoServices;

	public CircuitoController(CircuitoServices circuitoServices) {
		this.circuitoServices = circuitoServices;
	}
	
	@GetMapping
	public List<Circuito> getAll(){
		return circuitoServices.getAll();
	}
	
	

}
