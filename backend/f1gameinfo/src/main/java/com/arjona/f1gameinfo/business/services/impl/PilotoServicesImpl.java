package com.arjona.f1gameinfo.business.services.impl;

import org.springframework.stereotype.Service;

import com.arjona.f1gameinfo.business.repositores.PilotoRepository;
import com.arjona.f1gameinfo.business.services.PilotoServices;

@Service
public class PilotoServicesImpl implements PilotoServices{
	
	private PilotoRepository pilotoRepository;

	public PilotoServicesImpl(PilotoRepository pilotoRepository) {
		this.pilotoRepository = pilotoRepository;
	}

}
