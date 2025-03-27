package com.arjona.f1gameinfo.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

/**
 * Componente para gestionar los
 * códigos OTP
 */
@Component
public class UtilsOTP {
	
    private final GoogleAuthenticator gAuth;

    @Autowired
    public UtilsOTP() {
        this.gAuth = new GoogleAuthenticator();
    }
    
    /**
     * Genera un secreto
     * 
     * @return secreto
     */
    public GoogleAuthenticatorKey generateKey() {
        return gAuth.createCredentials();
    }

    /**
     * Genera un QR único para la 2FA basado
     * en el email y secreto del usuario
     * 
     * @param email del usuario
     * @param key secreto del usuario
     * @return QR
     */
    public String generateQRCode(String email, GoogleAuthenticatorKey key) {
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL("f1gameinfo", email, key);
    }

    /**
     * Verifica si el código OTP es válido
     * con el secreto del usuario
     * 
     * @param secreto del usuario
     * @param code otp
     * @return {@code True} si es válido, si no {@code False}
     */
    public boolean verifyCode(String secreto, int code) {
        return gAuth.authorize(secreto, code, new Date().getTime());
    }
}
