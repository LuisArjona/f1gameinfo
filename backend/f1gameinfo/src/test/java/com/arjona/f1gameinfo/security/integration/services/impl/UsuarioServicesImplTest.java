package com.arjona.f1gameinfo.security.integration.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.arjona.f1gameinfo.security.integration.model.Usuario;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;

/**
 * Tests de {@link UsuarioServicesImpl}
 */
@ExtendWith(MockitoExtension.class)
public class UsuarioServicesImplTest {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UsuarioServicesImpl usuarioServices;
	
	@Test
	void testRegistrarUsuarioOk() {
		Usuario user = new Usuario();
		user.setUsername("prueba@gmail.com");
		user.setPassword("prueba");
		user.setSecreto("secreto");
		user.setMonedas(0);
		
		when(usuarioRepository.existsByUsername(user.getUsername())).thenReturn(false);
		when(passwordEncoder.encode(user.getPassword())).thenReturn("codificada");
		user.setPassword("codificada");
		when(usuarioRepository.save(user)).thenReturn(user);
		
		Usuario user2 = usuarioServices.registrarUsuario(user.getUsername(), "prueba", user.getSecreto());
		
		assertEquals(user, user2);
	}
	
	@Test
	void testRegistrarUsuarioExistente() {
		Usuario user = new Usuario();
		user.setUsername("prueba@gmail.com");
		user.setPassword("prueba");
		user.setSecreto("secreto");
		
		when(usuarioRepository.existsByUsername(user.getUsername())).thenReturn(true);
		
		assertThrows(IllegalStateException.class, 
				() -> usuarioServices.registrarUsuario(user.getUsername(), 
						user.getPassword(), user.getSecreto()));
	}

}
