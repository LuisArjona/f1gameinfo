package com.arjona.f1gameinfo.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.arjona.f1gameinfo.business.model.RankingDTO;
import com.arjona.f1gameinfo.business.model.UsuarioDTO;
import com.arjona.f1gameinfo.business.services.UsuarioNoAuthServices;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;

/**
 * Servicio que gestiona la lógica
 * de la aplicación sobre los usuarios
 * fuera de la autenticación
 */
@Service
public class UsuarioNoAuthServicesImpl implements UsuarioNoAuthServices{
	
	private UsuarioRepository usuarioRepository;

	public UsuarioNoAuthServicesImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public List<RankingDTO> getRanking() {
		List<UsuarioDTO> usuarios = usuarioRepository.getAllUsuariosRanking();
		List<RankingDTO> ranking = new ArrayList();
		for(int i = 0; i < usuarios.size(); i++) {
			UsuarioDTO dto = usuarios.get(i);
			ranking.add(new RankingDTO(dto.getUsername(), 
					dto.getCantidadPilotos(), dto.getCantidadCircuitos(), 
					dto.getCantidadTotal(), 
					usuarioRepository.findPilotosByUsername(dto.getUsername()), 
					usuarioRepository.findCircuitosByUsername(dto.getUsername())));
		}
		return ranking;
	}

	@Override
	public Integer getMonedas(Long id) {
		if(!usuarioRepository.existsById(id))
			throw new IllegalStateException("No existe el usuario");
		return usuarioRepository.findMonedasById(id);
	}
}
