package com.arjona.f1gameinfo.business.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import com.arjona.f1gameinfo.business.model.CartaCompradaDTO;
import com.arjona.f1gameinfo.business.model.CartaUsuario;
import com.arjona.f1gameinfo.business.model.CartaUsuarioDTO;
import com.arjona.f1gameinfo.business.model.Circuito;
import com.arjona.f1gameinfo.business.model.Piloto;
import com.arjona.f1gameinfo.business.repositores.CartaUsuarioRepository;
import com.arjona.f1gameinfo.business.repositores.CircuitoRepository;
import com.arjona.f1gameinfo.business.repositores.PilotoRepository;
import com.arjona.f1gameinfo.security.integration.model.Usuario;
import com.arjona.f1gameinfo.security.integration.repositories.UsuarioRepository;

/**
 * Tests de {@link CartaServicesImpl}
 */
@ExtendWith(MockitoExtension.class)
public class CartaServicesImplTest {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private CircuitoRepository circuitoRepository;
	
	@Mock
	private PilotoRepository pilotoRepository;

	@Mock
	private CartaUsuarioRepository cartaUsuarioRepository;
	
	@InjectMocks
	private CartaServicesImpl cartaServices;
	
	@Test
	void testActualizarCartasOk() {
		Usuario user = new Usuario(1L, "prueba@gmail.com", "password", 
				new HashSet<>(), new HashSet<>(), 100, "secreto");
		Circuito circuito = new Circuito(2L, "piloto", "pais", "descripcion", 1900, 2000);
		
		when(usuarioRepository.existsById(user.getId())).thenReturn(true);
		when(usuarioRepository.findById(user.getId())).thenReturn(Optional.of(user));
		
		user.setMonedas(200);
		
		when(circuitoRepository.existsById(circuito.getId())).thenReturn(true);
		when(circuitoRepository.findById(circuito.getId())).thenReturn(Optional.of(circuito));
		
		user.addCircuito(circuito);
		
		cartaServices.actualizarCartas(1L, 200, null, 2L);
		
		verify(usuarioRepository, times(1)).save(user);
	}
	
	@Test
	void testActualizarCartasUsuarioInexistente() {
		
		when(usuarioRepository.existsById(1L)).thenReturn(false);
		
		assertThrows(IllegalStateException.class,
				() -> cartaServices.actualizarCartas(1L, 200, null, null));
	}
	
	@Test
	void testActualizarCircuitoInexistente() {
		Usuario user = new Usuario(1L, "prueba@gmail.com", "password", 
				new HashSet<>(), new HashSet<>(), 100, "secreto");
		
		when(usuarioRepository.existsById(user.getId())).thenReturn(true);
		when(usuarioRepository.findById(user.getId())).thenReturn(Optional.of(user));
		
		when(circuitoRepository.existsById(2L)).thenReturn(false);
		
		assertThrows(IllegalStateException.class,
				() -> cartaServices.actualizarCartas(1L, 200, null, 2L));
	}
	
	@Test
	void testActualizarPilotoInexistente() {
		Usuario user = new Usuario(1L, "prueba@gmail.com", "password", 
				new HashSet<>(), new HashSet<>(), 100, "secreto");
		
		when(usuarioRepository.existsById(user.getId())).thenReturn(true);
		when(usuarioRepository.findById(user.getId())).thenReturn(Optional.of(user));
		
		when(pilotoRepository.existsById(2L)).thenReturn(false);
		
		assertThrows(IllegalStateException.class,
				() -> cartaServices.actualizarCartas(1L, 200, 2L, null));
	}
	
	@Test
	void testGetAllDtos() {
		CartaUsuarioDTO dto1 = new CartaUsuarioDTO(80, "imagen.jpg", "prueba1@gmail.com");
		CartaUsuarioDTO dto2 = new CartaUsuarioDTO(86, "imagen.png", "prueba2@gmail.com");
		
		List<CartaUsuarioDTO> esperado = Arrays.asList(dto1, dto2);
		
		when(cartaUsuarioRepository.getAllDtos()).thenReturn(esperado);
		
		List<CartaUsuarioDTO> respuesta = cartaServices.getAllDtos();
		
		assertEquals(esperado, respuesta);
	}
	
	@Test
	void testSubirCartaOk() throws IOException {
	    Usuario user = new Usuario(1L, "prueba@gmail.com", "password", 
	            new HashSet<>(), new HashSet<>(), 100, "secreto");
	    when(usuarioRepository.existsById(user.getId())).thenReturn(true);
	    when(usuarioRepository.findById(user.getId())).thenReturn(Optional.of(user));

	    MultipartFile imagen = Mockito.mock(MultipartFile.class);
	    when(imagen.getOriginalFilename()).thenReturn("imagen.jpg");

	    cartaServices.subirCarta(user.getId(), 50, imagen);

	    verify(cartaUsuarioRepository, times(1)).save(Mockito.any(CartaUsuario.class));
	}
	
	@Test
	void testSubirCartaUsuarioInexistente() throws IOException {
	    when(usuarioRepository.existsById(1L)).thenReturn(false);

	    MultipartFile imagen = Mockito.mock(MultipartFile.class);
	    
	    assertThrows(IllegalStateException.class, 
	    		() -> cartaServices.subirCarta(1L, 50, imagen));
	}
	
	@Test
	void testSubirCartaValoracionInvalida() throws IOException {
	    when(usuarioRepository.existsById(1L)).thenReturn(false);

	    MultipartFile imagen = Mockito.mock(MultipartFile.class);
	    
	    assertThrows(IllegalStateException.class, 
	    		() -> cartaServices.subirCarta(1L, 100, imagen));
	}
	
	@Test
	void testGetCartaUsuarioOk() {
		CartaUsuarioDTO carta = new CartaUsuarioDTO(80, "imagen.jpg", "prueba1@gmail.com");
		when(cartaUsuarioRepository.getCartaUsuarioFromUsuario(1L)).thenReturn(Optional.of(carta));
		CartaUsuarioDTO respuesta = cartaServices.getCartaUsuario(1L);
		
		assertEquals(carta, respuesta);
	}
	
	@Test
	void testGetCartaUsuarioInexistente() {
		when(cartaUsuarioRepository.getCartaUsuarioFromUsuario(1L)).thenReturn(Optional.empty());
		assertThrows(IllegalStateException.class, 
				() -> cartaServices.getCartaUsuario(1L));
	}
	
	@Test
	void testGetCartasCompradasFromUsuarioOk() {
	    when(usuarioRepository.existsById(1L)).thenReturn(true);
	    
	    Set<Piloto> pilotos = new HashSet<>();
	    Set<Circuito> circuitos = new HashSet<>();
	    pilotos.add(new Piloto(2L, "piloto", "equipo", 20, 0, 1, 10, 
	    		"descripcion", 2025, 80, 1500, "imagen.jpg"));
	    circuitos.add(new Circuito(3L, "circuito", "pais", "descripcion", 1900, 2000));
	    
	    when(usuarioRepository.findPilotosById(1L)).thenReturn(pilotos);
	    when(usuarioRepository.findCircuitosById(1L)).thenReturn(circuitos);
	    
	    CartaCompradaDTO esperado = new CartaCompradaDTO(pilotos, circuitos);
	    
	    CartaCompradaDTO respuesta = cartaServices.getCartasCompradasFromUsuario(1L);
	    
	    assertEquals(esperado.getPilotos(), respuesta.getPilotos());
	    assertEquals(esperado.getCircuitos(), respuesta.getCircuitos());
	}
	
	@Test
	void testGetCartasCompradasFromUsuarioInexistente() {
	    when(usuarioRepository.existsById(1L)).thenReturn(false);
	    
	    assertThrows(IllegalStateException.class, 
	    		() -> cartaServices.getCartasCompradasFromUsuario(1L));
	}

}
