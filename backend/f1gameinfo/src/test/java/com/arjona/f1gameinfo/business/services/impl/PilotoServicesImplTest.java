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
import com.arjona.f1gameinfo.business.model.Piloto;
import com.arjona.f1gameinfo.business.repositores.PilotoRepository;

/**
 * Tests de {@link CircuitoServicesImpl}
 */
@ExtendWith(MockitoExtension.class)
public class PilotoServicesImplTest {
	
	@Mock
	private PilotoRepository pilotoRepository;
	
	@InjectMocks
	private PilotoServicesImpl pilotoServices;
	
	@Test
	void testGetAll() {
		Piloto p1 = new Piloto(1L, "piloto1", "equipo1", 21, 1, 2, 11, 
	    		"descripcion", 2025, 80, 1500, "imagen1.jpg");
		Piloto p2 = new Piloto(2L, "piloto2", "equipo2", 22, 0, 1, 10, 
	    		"descripcion", 2025, 80, 1500, "imagen2.jpg");
		
		List<Piloto> esperado = Arrays.asList(p1, p2);
		
		when(pilotoRepository.findAll()).thenReturn(esperado);
		
		List<Piloto> respuesta = pilotoServices.getAll();
		
		assertEquals(esperado, respuesta);
		
	}

}
