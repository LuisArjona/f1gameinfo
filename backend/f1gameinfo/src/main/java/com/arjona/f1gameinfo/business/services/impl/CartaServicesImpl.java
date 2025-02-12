package com.arjona.f1gameinfo.business.services.impl;

import org.springframework.stereotype.Service;

import com.arjona.f1gameinfo.business.repositores.CircuitoRepository;
import com.arjona.f1gameinfo.business.repositores.PilotoRepository;
import com.arjona.f1gameinfo.business.services.CartaServices;
import com.arjona.f1gameinfo.security.integration.model.Usuario;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

import com.arjona.f1gameinfo.business.model.Circuito;
import com.arjona.f1gameinfo.business.model.Piloto;

@Service
public class CartaServicesImpl implements CartaServices{
	
	private UsuarioRepository usuarioRepository;
	
	private CircuitoRepository circuitoRepository;
	
	private PilotoRepository pilotoRepository;

	

	public CartaServicesImpl(UsuarioRepository usuarioRepository, CircuitoRepository circuitoRepository,
			PilotoRepository pilotoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.circuitoRepository = circuitoRepository;
		this.pilotoRepository = pilotoRepository;
	}



	@Transactional
	@Override
	public void actualizarCartas(Long id, Integer monedas, Long idPiloto, Long idCircuito) {
		if(! usuarioRepository.existsById(id))
			throw new IllegalStateException("Usuario no existente.");
		
		Usuario usuario = usuarioRepository.findById(id).get();
		usuario.setMonedas(monedas);
		
		if(idPiloto == null && idCircuito == null)
			throw new IllegalStateException("No hay piloto o circuito que actualizar.");
		
		if(idPiloto != null) {
			if(! pilotoRepository.existsById(idPiloto))
				throw new IllegalStateException("Piloto no existente.");
			Piloto piloto = pilotoRepository.findById(idPiloto).get();
			usuario.addPiloto(piloto);
		}
		
		if(idCircuito != null) {
			if(! circuitoRepository.existsById(idCircuito))
				throw new IllegalStateException("Circuito no existente.");
			Circuito circuito = circuitoRepository.findById(idCircuito).get();
			usuario.addCircuito(circuito);
		}
		
		usuarioRepository.save(usuario);
	}
	
	

}
