package com.arjona.f1gameinfo.presentation.config;

import org.springframework.http.HttpStatus;

/**
 * Excepción personalizada para escenarios en los que ciertas 
 * condiciones específicas de la lógica de negocio no se cumplen
 */
public class StatusException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	
	public StatusException(String mensajeExcepcion, HttpStatus httpStatus) {
		super(mensajeExcepcion);
		this.httpStatus = httpStatus;
	}
	
	public StatusException(String mensajeExcepcion, int httpStatusCode) {
		super(mensajeExcepcion);
		this.httpStatus = HttpStatus.valueOf(httpStatusCode);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
