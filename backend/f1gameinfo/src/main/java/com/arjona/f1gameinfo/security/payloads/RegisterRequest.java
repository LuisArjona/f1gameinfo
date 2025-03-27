package com.arjona.f1gameinfo.security.payloads;

/**
 * POJO creado para recibir
 * una petición de registro
 */
public class RegisterRequest {

	private String username;
	private String password;
	
	public RegisterRequest() {
		
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
