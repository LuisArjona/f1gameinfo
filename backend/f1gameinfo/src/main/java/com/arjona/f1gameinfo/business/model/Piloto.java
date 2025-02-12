package com.arjona.f1gameinfo.business.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PILOTOS")
public class Piloto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String nombreEquipo;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private int victorias;

    @Column(nullable = false)
    private int podios;

    @Column(nullable = false)
    private int carreras;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int anhoDebut;

    @Column(nullable = false)
    private int valoracion;

    @Column(nullable = false)
    private long precio;
    
    @Column(nullable = true)
    private String rutaImagen;
    
	public Piloto() {
	}

	public Piloto(Long id, String nombre, String nombreEquipo, int edad, int victorias, int podios, int carreras,
			String descripcion, int anhoDebut, int valoracion, long precio, String rutaImagen) {
		this.id = id;
		this.nombre = nombre;
		this.nombreEquipo = nombreEquipo;
		this.edad = edad;
		this.victorias = victorias;
		this.podios = podios;
		this.carreras = carreras;
		this.descripcion = descripcion;
		this.anhoDebut = anhoDebut;
		this.valoracion = valoracion;
		this.precio = precio;
		this.rutaImagen = rutaImagen;
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

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getPodios() {
		return podios;
	}

	public void setPodios(int podios) {
		this.podios = podios;
	}

	public int getCarreras() {
		return carreras;
	}

	public void setCarreras(int carreras) {
		this.carreras = carreras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getAnhoDebut() {
		return anhoDebut;
	}

	public void setAnhoDebut(int añoDebut) {
		this.anhoDebut = añoDebut;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
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
		Piloto other = (Piloto) obj;
		return Objects.equals(id, other.id);
	}
    
}
