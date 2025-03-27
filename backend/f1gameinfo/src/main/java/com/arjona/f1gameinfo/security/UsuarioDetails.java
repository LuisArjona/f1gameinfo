package com.arjona.f1gameinfo.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arjona.f1gameinfo.security.integration.model.Usuario;
/**
 * Almacena la información de un usuario de manera práctica para su uso
 * en el contexto de Spring Security dentro de {@link Authentication}
 */
public class UsuarioDetails implements UserDetails{
	private static final long serialVersionUID = 1L;

	private final Usuario usuario;
	
	public UsuarioDetails(Usuario usuario) {
        this.usuario = usuario;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getUsername();
	}
	

	/**
	 * Obtiene el id del usuario
	 * @return id del usuario
	 */
    public Long getId() {
		return usuario.getId();
	}
    
    /**
     * Obtiene el secreto del usuario
     * @return secreto 2FA
     */
    public String getSecreto() {
    	return usuario.getSecreto();
    }

}
