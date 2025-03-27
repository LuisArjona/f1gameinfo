package com.arjona.f1gameinfo.presentation.config;

/**
 * Clase para devolver el mensaje de error en el gestor
 * de excepciones como JSON y no texto plano
 */
public class HttpCustomError {

	private String error;
	
	public HttpCustomError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}	
}
