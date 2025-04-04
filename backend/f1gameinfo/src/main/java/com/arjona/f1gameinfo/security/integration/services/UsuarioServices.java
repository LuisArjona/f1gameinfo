package com.arjona.f1gameinfo.security.integration.services;

import com.arjona.f1gameinfo.security.integration.model.Usuario;

/**
 * Interfaz para el servicio que gestiona la
 * lógica de la aplicación sobre la
 * autenticación de los usuarios
 */
public interface UsuarioServices {
	
	/**
	 * Registra un usuario no existente
	 * en la aplicación
	 * 
	 * @param username usuario
	 * @param password contraseña
	 * @param key secreto 2FA
	 * @return {@link Usuario} creado
	 */
	Usuario registrarUsuario(String username, String password, String key);
	
	/**
	 * Actualiza la contraseña de
	 * un usuario existente
	 * 
	 * @param passActual
	 * @param passNueva
	 * @param id
	 */
	public void actualizarPass(String passActual, String passNueva, Long id);

}
