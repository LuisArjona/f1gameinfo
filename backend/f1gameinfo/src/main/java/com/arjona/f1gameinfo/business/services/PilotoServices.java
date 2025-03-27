package com.arjona.f1gameinfo.business.services;

import java.util.List;

import com.arjona.f1gameinfo.business.model.Piloto;

/**
 * Interfaz para el servicio que gestiona la
 * lógica de la aplicación sobre los pilotos
 */
public interface PilotoServices {
	
	/**
	 * Devuelve todos los circuitos existentes
	 * 
	 * @return {@code List} de {@link Piloto}
	 */
	List<Piloto> getAll();

}
