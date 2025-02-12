package com.arjona.f1gameinfo.security.integration.services;

import com.arjona.f1gameinfo.security.integration.model.Usuario;

public interface UsuarioServices {
	
	Usuario registrarUsuario(String username, String password);

}
