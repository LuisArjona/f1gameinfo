package com.arjona.f1gameinfo.security.presentation.restcontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.presentation.config.StatusException;
import com.arjona.f1gameinfo.security.UtilsJWT;
import com.arjona.f1gameinfo.security.UtilsOTP;
import com.arjona.f1gameinfo.security.integration.services.UsuarioServices;
import com.arjona.f1gameinfo.security.payloads.JwtResponse;
import com.arjona.f1gameinfo.security.payloads.RegisterRequest;
import com.arjona.f1gameinfo.security.payloads.RegisterResponse;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

/**
 * Controlador encargado de atender las
 * peticiones acerca de la autenticación
 * de los usuarios
 */
@CrossOrigin
@RestController
@RequestMapping("/autentificacion")
public class AutentificacionController {

    private AuthenticationManager authenticationManager;
    private UtilsJWT utilsJwt;
    private UsuarioServices usuarioServices;
    private UtilsOTP utilsOTP;
    
    public AutentificacionController(AuthenticationManager authenticationManager, UtilsJWT jwtUtils,
			UsuarioServices usuarioServices, UtilsOTP utilsOTP) {
		this.authenticationManager = authenticationManager;
		this.utilsJwt = jwtUtils;
		this.usuarioServices = usuarioServices;
		this.utilsOTP = utilsOTP;
	}

    /**
     * Atiende la petición de registrar un usuario
     * nuevo en la aplicación
     * 
     * @param registerRequest datos para el registro
     * @return {@code 200 OK} con {@link RegisterResponse}
     */
	@PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegisterRequest registerRequest){
    	
        GoogleAuthenticatorKey key = utilsOTP.generateKey();
    	try {
    		usuarioServices.registrarUsuario(registerRequest.getUsername(), registerRequest.getPassword(), key.getKey());
    	}catch(IllegalStateException e) {
    		throw new StatusException(e.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    	
    	String qr = utilsOTP.generateQRCode(registerRequest.getUsername(), key);
    	
    	return ResponseEntity.ok(new RegisterResponse(qr, key.getKey()));
    }
    
	/**
	 * Atiende la petición de logar
	 * un usuario existente en la aplicación
	 * 
	 * @param username usuario
	 * @param password contraseña
	 * @param otp código de 2FA
	 * @return {@code 200 OK} con {@link JwtResponse}
	 */
    @PostMapping("/login")
    public ResponseEntity<?> logarUsuario(@RequestParam(required = true) String username,
    		@RequestParam(required = true) String password, 
    		@RequestParam(required = true) String otp) {

    	Authentication autenticacion = null;
    	
    	try {
    		autenticacion = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
    	} catch(Exception e) {
    		throw new StatusException(e.getMessage(), HttpStatus.UNAUTHORIZED);
    	}
        
        SecurityContextHolder.getContext().setAuthentication(autenticacion);
        String jwt = utilsJwt.generarJwt(autenticacion);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
    
    @PutMapping("/actualizarpass")
    public ResponseEntity<Void> actualizarPass(@RequestParam(required = true) String passActual,
    		@RequestParam(required = true) String passNueva, 
    		@RequestParam(required = true) Long id){
    	
    	usuarioServices.actualizarPass(passActual, passNueva, id);
    	
    	return ResponseEntity.ok().build();
    	
    }

}
