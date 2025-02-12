package com.arjona.f1gameinfo.presentation.restcontrollers;

import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.business.services.CircuitoServices;

@RestController
public class CircuitoController {
	
	private CircuitoServices circuitoServices;

	public CircuitoController(CircuitoServices circuitoServices) {
		this.circuitoServices = circuitoServices;
	}
	
	

}
