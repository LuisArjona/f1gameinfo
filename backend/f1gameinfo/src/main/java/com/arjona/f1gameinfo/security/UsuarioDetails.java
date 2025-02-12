package com.arjona.f1gameinfo.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arjona.f1gameinfo.security.integration.model.Usuario;

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
	

    public Long getId() {
		return usuario.getId();
	}

}
