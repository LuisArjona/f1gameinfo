package com.arjona.f1gameinfo.security.integration.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.arjona.f1gameinfo.security.integration.model.Usuario;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;
import com.arjona.f1gameinfo.security.integration.services.UsuarioServices;

import jakarta.transaction.Transactional;

/**
 * Servicio que gestiona la lógica
 * de la aplicación sobre la
 * autenticación de los usuarios
 */
@Service
public class UsuarioServicesImpl implements UsuarioServices{

    private UsuarioRepository usuarioRepository;
	
    private PasswordEncoder passwordEncoder;

    public UsuarioServicesImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
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
	
	@Transactional
	public void actualizarPass(String passActual, String passNueva, Long id) {
		if(!usuarioRepository.existsById(id))
			throw new IllegalStateException("No existe el usuario.");
		
		Usuario usuario = usuarioRepository.findById(id).get();
		
		if(!passwordEncoder.matches(passActual, usuario.getPassword()))
			throw new IllegalStateException("Contraseña actual incorrecta.");
		
		usuario.setPassword(passwordEncoder.encode(passNueva));
		usuarioRepository.save(usuario);
	}
	
}
