package com.arjona.f1gameinfo.business.services.impl;

import org.springframework.stereotype.Service;

import com.arjona.f1gameinfo.business.repositores.CircuitoRepository;
import com.arjona.f1gameinfo.business.services.CircuitoServices;

@Service
public class CircuitoServicesImpl implements CircuitoServices{
	
	private CircuitoRepository circuitoRepository;

	public CircuitoServicesImpl(CircuitoRepository circuitoRepository) {
		this.circuitoRepository = circuitoRepository;
	}

}
