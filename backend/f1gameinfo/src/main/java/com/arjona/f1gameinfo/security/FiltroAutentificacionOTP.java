package com.arjona.f1gameinfo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro para validar un código OTP
 */
public class FiltroAutentificacionOTP extends OncePerRequestFilter{
	
	@Autowired
	private UtilsOTP utilsOTP;
	
    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    /**
     * Solo si es el endpoint de login recupera el código OTP y el email e intenta validarlo, si es
     * válido deja que continue con la cadena de seguridad, caso contrario devuelve {@code 401 UNAUTHORIZED}
     */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {
	    
	    if (request.getRequestURI().equals("/autentificacion/login")) {
            String otpCode = request.getParameter("otp");
            String email = request.getParameter("username");
	        
	        try {
	            int otpCodeInt = Integer.parseInt(otpCode);
	            UsuarioDetails usuario = (UsuarioDetails) usuarioDetailsService.loadUserByUsername(email);
	            if (usuario == null || !utilsOTP.verifyCode(usuario.getSecreto(), otpCodeInt)) {
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Código OTP no válido");
	                return;
	            }
	        } catch (NumberFormatException e) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de código OTP no válido");
	            return;
	        }
	    }
	    filterChain.doFilter(request, response);
	}

}
