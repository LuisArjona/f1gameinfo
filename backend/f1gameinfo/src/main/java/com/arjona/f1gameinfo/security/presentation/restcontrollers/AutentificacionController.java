package com.arjona.f1gameinfo.security.presentation.restcontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arjona.f1gameinfo.presentation.config.StatusException;
import com.arjona.f1gameinfo.security.UtilsJWT;
import com.arjona.f1gameinfo.security.UtilsOTP;
import com.arjona.f1gameinfo.security.integration.services.UsuarioServices;
import com.arjona.f1gameinfo.security.payloads.LoginRequest;
import com.arjona.f1gameinfo.security.payloads.RegisterRequest;
import com.arjona.f1gameinfo.security.payloads.RegisterResponse;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.arjona.f1gameinfo.security.payloads.JwtResponse;

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

}
