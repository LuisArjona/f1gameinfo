package com.arjona.f1gameinfo.business.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arjona.f1gameinfo.business.model.RankingDTO;
import com.arjona.f1gameinfo.business.model.UsuarioDTO;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;

/**
 * Tests de {@link UsuarioNoAuthServicesImpl}
 */
@ExtendWith(MockitoExtension.class)
public class UsuarioNoAuthServicesImplTest {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private UsuarioNoAuthServicesImpl usuarioNoAuthServices;
	
	@Test
	void testGetRanking() {
		RankingDTO dto1 = new RankingDTO("prueba1", 10, 3, 13, null, null);
		RankingDTO dto2 = new RankingDTO("prueba2", 5, 6, 11, null, null);
		UsuarioDTO dto1v1 = new UsuarioDTO("prueba1", 10, 3, 13);
		UsuarioDTO dto2v1 = new UsuarioDTO("prueba2", 5, 6, 11);
		List<RankingDTO> esperado = Arrays.asList(dto1, dto2);
		
		when(usuarioRepository.getAllUsuariosRanking()).thenReturn(Arrays.asList(dto1v1, dto2v1));
		
		List<RankingDTO> respuesta = usuarioNoAuthServices.getRanking();
		assertEquals(esperado, respuesta);
	}
	
	@Test
	void testGetMonedasOk() {
		when(usuarioRepository.existsById(1L)).thenReturn(true);
		
		Integer esperado = 100;
		when(usuarioRepository.findMonedasById(1L)).thenReturn(esperado);
		
		Integer respuesta = usuarioNoAuthServices.getMonedas(1L);
		
		assertEquals(esperado, respuesta);
	}
	
	@Test
	void testGetMonedasUsuarioInexistente() {
		when(usuarioRepository.existsById(1L)).thenReturn(false);
		
		assertThrows(IllegalStateException.class, 
				() -> usuarioNoAuthServices.getMonedas(1L));
	}

}
