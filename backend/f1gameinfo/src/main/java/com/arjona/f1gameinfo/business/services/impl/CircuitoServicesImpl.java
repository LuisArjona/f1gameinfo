package com.arjona.f1gameinfo.business.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arjona.f1gameinfo.business.model.Circuito;
import com.arjona.f1gameinfo.business.repositores.CircuitoRepository;
import com.arjona.f1gameinfo.business.services.CircuitoServices;

/**
 * Servicio que gestiona la lógica
 * de la aplicación sobre los circuitos
 */
@Service
public class CircuitoServicesImpl implements CircuitoServices{
	
	private CircuitoRepository circuitoRepository;

	public CircuitoServicesImpl(CircuitoRepository circuitoRepository) {
		this.circuitoRepository = circuitoRepository;
	}

	@Override
	public List<Circuito> getAll() {
		return circuitoRepository.findAll();
	}
}
