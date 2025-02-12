package com.arjona.f1gameinfo.presentation.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GestorExcepciones extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return ResponseEntity.badRequest().body(new HttpCustomError("Objeto JSON inválido"));
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		HttpCustomError httpCustomError = new HttpCustomError("Metodo no existente");
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(httpCustomError);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){	
		return ResponseEntity.internalServerError().body(new HttpCustomError("Error del servidor"));
	}
	
	@ExceptionHandler(StatusException.class)
	public ResponseEntity<Object> handleStatusException(StatusException ex){
		return new ResponseEntity<>(new HttpCustomError(ex.getMessage()), ex.getHttpStatus());
	}

	@ExceptionHandler(IllegalStateException.class)
	public void handleIllegalState(IllegalStateException ex){
		throw new StatusException(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){
	
		String entrante = ex.getValue().getClass().toString();
		String requerido = ex.getRequiredType().toString();
		
		return ResponseEntity.badRequest().body(new HttpCustomError("El tipo de valor entrante es " + entrante + ", no coincide con el requerido " + requerido + " para el valor: " + ex.getValue()));
	}
	

	

}
