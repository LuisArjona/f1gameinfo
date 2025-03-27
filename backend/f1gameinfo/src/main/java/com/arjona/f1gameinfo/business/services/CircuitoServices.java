package com.arjona.f1gameinfo.business.services;

import java.util.List;

import com.arjona.f1gameinfo.business.model.Circuito;

/**
 * Interfaz para el servicio que gestiona la
 * lógica de la aplicación sobre los circuitos
 */
public interface CircuitoServices {
	
	/**
	 * Devuelve todos los circuitos existentes
	 * 
	 * @return {@code List} de {@link Circuito}
	 */
	List<Circuito> getAll();

}
