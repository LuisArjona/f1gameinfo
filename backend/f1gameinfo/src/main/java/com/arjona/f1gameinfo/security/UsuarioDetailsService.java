package com.arjona.f1gameinfo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;
import com.arjona.f1gameinfo.security.integration.model.Usuario;

/**
 * Servicio dentro para obtener los datos
 * de un usuario dentro de un {@link UserDetails}
 */
@Service
public class UsuarioDetailsService implements UserDetailsService{
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario: " + username + " no encontrado."));
	
		return new UsuarioDetails(usuario);
	}

}
