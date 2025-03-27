package com.arjona.f1gameinfo.security;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro para validar un token JWT
 */
public class FiltroAutentificacionJWT extends OncePerRequestFilter{
	
	 @Autowired
	 private UtilsJWT utilsJwt;
	 
	 @Autowired
	 private UsuarioDetailsService usuarioDetailsService;
	 
	 private static final List<String> RUTAS_PUBLICAS = List.of(
	            "/autentificacion/",
	            "/uploads/images/");
	 
	 /**
	  * En aquellos endpoints no públicos intenta validar el token JWT,
	  * si es correcto establece en el contexto de seguridad las credenciales del usuario del token,
	  * caso contrario no establece el contexto de seguridad que derivará en un {@code 403 FORBIDDEN}
	  */
	 @Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		 
	 	if(! RUTAS_PUBLICAS.stream().anyMatch(request.getServletPath()::startsWith)) {
			 try {
				 
				 String jwt = parseJwt(request);
				 
				 if (jwt != null && utilsJwt.validarJwt(jwt)) {
					 
					 UsuarioDetails usuario = (UsuarioDetails) usuarioDetailsService.loadUserByUsername(utilsJwt.getTokenUsername(jwt));
					 
					 System.out.println(usuario.getUsername() + " " + usuario.getPassword() + " " + usuario.getId());

					 UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, Collections.emptyList());
					 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					 SecurityContextHolder.getContext().setAuthentication(authentication);
				 }
			 } catch (Exception e) {
				 logger.error("Cannot set user authentication: {}", e);
			 }
	 	}
		
		 
		 filterChain.doFilter(request, response);
	 }
	 
	 

	 /**
	  * Recupera el token de la petición,
	  * si no existe o no sigue el formato devuelve {@code null},
	  * si sigue el formato lo devuelve
	  * @param request
	  * @return token
	  */
	 private String parseJwt(HttpServletRequest request) {
	    	
		String token = request.getHeader("Authorization"); 
	        
		if (token != null && token.startsWith("Bearer ")) {
			 return token.substring(7);
		}
		
		return null;
	}
}
