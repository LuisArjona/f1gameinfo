package com.arjona.f1gameinfo.security.integration.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.arjona.f1gameinfo.security.integration.model.Usuario;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;
import com.arjona.f1gameinfo.security.integration.services.UsuarioServices;

@Service
public class UsuarioServicesImpl implements UsuarioServices{

    private UsuarioRepository usuarioRepository;
	
    private PasswordEncoder passwordEncoder;

    public UsuarioServicesImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Usuario registrarUsuario(String username, String password, String key) {
        if (usuarioRepository.existsByUsername(username)) {
            throw new IllegalStateException("El nombre de usuario ya existe");
        }
        
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setSecreto(key);
        usuario.setMonedas(0);
        return usuarioRepository.save(usuario);
    }
	
}
