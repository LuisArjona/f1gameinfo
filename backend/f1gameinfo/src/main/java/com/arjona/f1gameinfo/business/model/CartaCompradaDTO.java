package com.arjona.f1gameinfo.business.model;

import java.util.List;

public class CartaCompradaDTO {
	
	private List<Piloto> pilotos;
	private List<Circuito> circuitos;
	
	public CartaCompradaDTO(List<Piloto> pilotos, List<Circuito> circuitos) {
		this.pilotos = pilotos;
		this.circuitos = circuitos;
	}

	public CartaCompradaDTO() {
		
	}

	public List<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(List<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	public List<Circuito> getCircuitos() {
		return circuitos;
	}

	public void setCircuitos(List<Circuito> circuitos) {
		this.circuitos = circuitos;
	}
	
	
	

}
