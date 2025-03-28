package com.arjona.f1gameinfo.business.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arjona.f1gameinfo.business.model.Circuito;
import com.arjona.f1gameinfo.business.repositores.CircuitoRepository;

/**
 * Tests de {@link CircuitoServicesImpl}
 */
@ExtendWith(MockitoExtension.class)
public class CircuitoServicesImplTest {
	
	@Mock
	private CircuitoRepository circuitoRepository;
	
	@InjectMocks
	private CircuitoServicesImpl circuitoServices;
	
	@Test
	void testGetAll() {
		Circuito circuito1 = new Circuito(1L, "piloto1", "pais1", "descripcion1", 1901, 2001);
		Circuito circuito2 = new Circuito(2L, "piloto2", "pais2", "descripcion2", 1902, 2002);
		List<Circuito> esperado = Arrays.asList(circuito1, circuito2);
		
		when(circuitoRepository.findAll()).thenReturn(esperado);
		
		List<Circuito> respuesta = circuitoServices.getAll();
		
		assertEquals(esperado, respuesta);
	}

}
