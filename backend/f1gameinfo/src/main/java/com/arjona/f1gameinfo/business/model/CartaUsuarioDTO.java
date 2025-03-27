package com.arjona.f1gameinfo.business.model;

/**
 * DTO que representa una carta propia
 * que ha creado el usuario
 */
public class CartaUsuarioDTO {
	
	Integer valoracion;
	
	String rutaImagen;
	
	String username;
	
	public CartaUsuarioDTO() {
		
	}

	public CartaUsuarioDTO(Integer valoracion, String rutaImagen, String username) {
		this.valoracion = valoracion;
		this.rutaImagen = rutaImagen;
		this.username = username;
	}

	public Integer getValoracion() {
		return valoracion;
	}

	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
