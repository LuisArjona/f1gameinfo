package com.arjona.f1gameinfo.business.model;

import java.util.HashSet;
import java.util.Set;

public class RankingDTO {
	
	private String username;
	private Integer cantidadPilotos;
	private Integer cantidadCircuitos;
	private Integer cantidadTotal;
    private Set<Piloto> pilotos = new HashSet<>();
    private Set<Circuito> circuitos = new HashSet<>();
	
	public RankingDTO(String username, Integer cantidadPilotos, Integer cantidadCircuitos, Integer cantidadTotal, Set<Piloto> pilotos, Set<Circuito> circuitos) {
		this.username = username;
		this.cantidadPilotos = cantidadPilotos;
		this.cantidadCircuitos = cantidadCircuitos;
		this.cantidadTotal = cantidadTotal;
		this.circuitos = circuitos;	
		this.pilotos = pilotos;
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

	public RankingDTO() {
		
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
