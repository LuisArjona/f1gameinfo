package com.arjona.f1gameinfo.business.model;

import java.util.Set;

public class CartaCompradaDTO {
	
	private Set<Piloto> pilotos;
	private Set<Circuito> circuitos;
	
	public CartaCompradaDTO(Set<Piloto> pilotos, Set<Circuito> circuitos) {
		this.pilotos = pilotos;
		this.circuitos = circuitos;
	}

	public CartaCompradaDTO() {
		
	}

	public Set<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(Set<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	public Set<Circuito> getCircuitos() {
		return circuitos;
	}

	public void setCircuitos(Set<Circuito> circuitos) {
		this.circuitos = circuitos;
	}
	
}
