package com.arjona.f1gameinfo.business.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa
 * los circuitos
 */
@Entity
@Table(name = "CIRCUITOS")
public class Circuito {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int anhoConstruccion;

    @Column(nullable = false)
    private long precio;
    
    @Column(nullable = true)
    private String rutaImagen;
    
	public Circuito() {
	}

	public Circuito(Long id, String nombre, String pais, String descripcion, int anhoConstruccion, long precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.descripcion = descripcion;
		this.anhoConstruccion = anhoConstruccion;
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getAnhoConstruccion() {
		return anhoConstruccion;
	}

	public void setAnhoConstruccion(int anhoConstruccion) {
		this.anhoConstruccion = anhoConstruccion;
	}

	public long getPrecio() {
		return precio;
	}

	public void setPrecio(long precio) {
		this.precio = precio;
	}
	
	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circuito other = (Circuito) obj;
		return Objects.equals(id, other.id);
	}
    
}
