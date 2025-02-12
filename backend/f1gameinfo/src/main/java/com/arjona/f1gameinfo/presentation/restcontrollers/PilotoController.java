package com.arjona.f1gameinfo.presentation.restcontrollers;

import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.business.services.PilotoServices;

@RestController
public class PilotoController {
	
	private PilotoServices pilotoServices;

	public PilotoController(PilotoServices pilotoServices) {
		this.pilotoServices = pilotoServices;
	}
	
	

}
