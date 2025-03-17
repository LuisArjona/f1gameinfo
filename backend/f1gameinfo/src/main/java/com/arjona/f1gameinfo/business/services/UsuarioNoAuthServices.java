package com.arjona.f1gameinfo.business.services;

import java.util.List;

import com.arjona.f1gameinfo.business.model.UsuarioDTO;

public interface UsuarioNoAuthServices {
	
	List<UsuarioDTO> getRanking();
	
	Integer getMonedas(Long id);

}
