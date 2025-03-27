package com.arjona.f1gameinfo.presentation.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Controla las excepciones lanzadas a nivel de controlador y las
 * transforma en la respuesta con el contenido y código http correspondiente
 */
@ControllerAdvice
public class GestorExcepciones extends ResponseEntityExceptionHandler{
	
	/**
	 * Controla cuando el formato del 
	 * body de la petición no es correcto
	 * 
	 * @param ex {@link HttpMessageNotReadableException}
	 * @return Un {@link ResponseEntity} con un código {@code 400 BAD REQUEST}
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return ResponseEntity.badRequest().body(new HttpCustomError("Objeto JSON inválido"));
	}
	
	/**
	 * Controla cuando se recibe una petición a un endpoint
	 * existente pero con un método (GET, POST, PUT, DELETE, PATCH...) incorrecto
	 *
	 * @param ex {@link HttpRequestMethodNotSupportedException}
	 * @return Un {@link ResponseEntity} con un código {@code 405 METHOD NOT ALLOWED}
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		HttpCustomError httpCustomError = new HttpCustomError("Metodo no existente");
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(httpCustomError);
	}
	
	/**
	 * Controla cuando sucede una excepción inesperada para asegurar
	 * no mostrar una traza de error al usuario con información sensible
	 * 
	 * @param ex {@link Exception}
	 * @return Un {@link ResponseEntity} con un código {@code 500 INTERNAL SERVER ERROR}
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex){	
		return ResponseEntity.internalServerError().body(new HttpCustomError("Error del servidor"));
	}
	
	/**
	 * Controla escenarios en los que ciertas condiciones específicas de la
	 * lógica de negocio no se cumplen y lanza una excepcion personalizada
	 * con la que devolver un código http al gusto
	 * 
	 * @param ex {@link StatusException}
	 * @return Un {@link ResponseEntity} con el código de respuesta de la excepcion
	 */
	@ExceptionHandler(StatusException.class)
	public ResponseEntity<Object> handleStatusException(StatusException ex){
		return new ResponseEntity<>(new HttpCustomError(ex.getMessage()), ex.getHttpStatus());
	}

	/**
	 * Controla cuando un dato recibido es incompatible con el
	 * funcionamiento correcto de la aplicación (i.e un dato nulo)
	 * 
	 * @param ex {@link IllegalStateException}
	 * @return Un {@link ResponseEntity} con un código {@code 400 BAD REQUEST}
	 */
	@ExceptionHandler(IllegalStateException.class)
	public void handleIllegalState(IllegalStateException ex){
		throw new StatusException(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Controla cuando un dato recibido es de tipo incorrecto
	 * (i.e recibe una cadena cuando pide un entero)
	 * @param ex {@link MethodArgumentTypeMismatchException}
	 * @return Un {@link ResponseEntity} con un código {@code 400 BAD REQUEST}
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex){
	
		String entrante = ex.getValue().getClass().toString();
		String requerido = ex.getRequiredType().toString();
		
		return ResponseEntity.badRequest().body(new HttpCustomError("El tipo de valor entrante es " + entrante + ", no coincide con el requerido " + requerido + " para el valor: " + ex.getValue()));
	}
	

	

}
