package com.arjona.f1gameinfo.business.model;

import com.arjona.f1gameinfo.security.integration.model.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa una carta propia
 * que ha creado el usuario
 */
@Entity
@Table(name="carta_usuario")
public class CartaUsuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(nullable = false)
	private Integer valoracion;
    
    @Column(nullable = false)
	String rutaImagen;

    @OneToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", unique = true)
	Usuario usuario;
	
	public CartaUsuario() {
		
	}
	
	public CartaUsuario(Long id, Integer valoracion, String rutaImagen, Usuario usuario) {
		this.id = id;
		this.valoracion = valoracion;
		this.rutaImagen = rutaImagen;
		this.usuario = usuario;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
