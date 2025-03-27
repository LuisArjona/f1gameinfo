package com.arjona.f1gameinfo.business.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arjona.f1gameinfo.business.model.Piloto;
import com.arjona.f1gameinfo.business.repositores.PilotoRepository;
import com.arjona.f1gameinfo.business.services.PilotoServices;

/**
 * Servicio que gestiona la lógica
 * de la aplicación sobre los pilotos
 */
@Service
public class PilotoServicesImpl implements PilotoServices{
	
	private PilotoRepository pilotoRepository;

	public PilotoServicesImpl(PilotoRepository pilotoRepository) {
		this.pilotoRepository = pilotoRepository;
	}

	@Override
	public List<Piloto> getAll() {
		return pilotoRepository.findAll();
	}
}
