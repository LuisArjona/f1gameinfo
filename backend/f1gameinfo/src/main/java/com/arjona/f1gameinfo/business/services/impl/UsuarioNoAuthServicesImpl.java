package com.arjona.f1gameinfo.business.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arjona.f1gameinfo.business.model.UsuarioDTO;
import com.arjona.f1gameinfo.business.services.UsuarioNoAuthServices;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;

@Service
public class UsuarioNoAuthServicesImpl implements UsuarioNoAuthServices{
	
	private UsuarioRepository usuarioRepository;

	public UsuarioNoAuthServicesImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public List<UsuarioDTO> getRanking() {
		return usuarioRepository.getAllUsuariosRanking();
	}

	@Override
	public Integer getMonedas(Long id) {
		if(!usuarioRepository.existsById(id))
			throw new IllegalStateException("No existe el usuario");
		return usuarioRepository.findMonedasById(id);
	}

}
