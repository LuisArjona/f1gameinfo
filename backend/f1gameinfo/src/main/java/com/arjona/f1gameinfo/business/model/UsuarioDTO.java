package com.arjona.f1gameinfo.business.model;

public class UsuarioDTO {
	
	private String username;
	private Integer cantidadPilotos;
	private Integer cantidadCircuitos;
	private Integer cantidadTotal;
	
	public UsuarioDTO(String username, Integer cantidadPilotos, Integer cantidadCircuitos, Integer cantidadTotal) {
		this.username = username;
		this.cantidadPilotos = cantidadPilotos;
		this.cantidadCircuitos = cantidadCircuitos;
		this.cantidadTotal = cantidadTotal;
	}

	public UsuarioDTO() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCantidadPilotos() {
		return cantidadPilotos;
	}

	public void setCantidadPilotos(Integer cantidadPilotos) {
		this.cantidadPilotos = cantidadPilotos;
	}

	public Integer getCantidadCircuitos() {
		return cantidadCircuitos;
	}

	public void setCantidadCircuitos(Integer cantidadCircuitos) {
		this.cantidadCircuitos = cantidadCircuitos;
	}

	public Integer getCantidadTotal() {
		return cantidadTotal;
	}

	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

}
