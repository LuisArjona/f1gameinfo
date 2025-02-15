package com.arjona.f1gameinfo.security.integration.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.arjona.f1gameinfo.business.model.Circuito;
import com.arjona.f1gameinfo.business.model.Piloto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
        name = "USUARIO_PILOTOS",
        joinColumns = @JoinColumn(name = "ID_USUARIO"),
        inverseJoinColumns = @JoinColumn(name = "ID_PILOTO")
    )
    private Set<Piloto> pilotos = new HashSet<>();
    
    @ManyToMany
    @JoinTable(
        name = "USUARIO_CIRCUITOS",
        joinColumns = @JoinColumn(name = "ID_USUARIO"),
        inverseJoinColumns = @JoinColumn(name = "ID_CIRCUITO")
    )
    private Set<Circuito> circuitos = new HashSet<>();
    
    private Integer monedas;
    
	public Usuario() {
	}

	public Usuario(Long id, String username, String password, Set<Piloto> pilotos, Set<Circuito> circuitos, Integer monedas) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.pilotos = pilotos;
		this.circuitos = circuitos;
		this.monedas = monedas;
		}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(Set<Piloto> pilotos) {
		this.pilotos = pilotos;
	}
	
	public void addPiloto(Piloto piloto) {
		this.pilotos.add(piloto);
	}

	public Set<Circuito> getCircuitos() {
		return circuitos;
	}

	public void setCircuitos(Set<Circuito> circuitos) {
		this.circuitos = circuitos;
	}
	
	public void addCircuito(Circuito circuito) {
		this.circuitos.add(circuito);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	

	public Integer getMonedas() {
		return monedas;
	}

	public void setMonedas(Integer monedas) {
		this.monedas = monedas;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
    
}
