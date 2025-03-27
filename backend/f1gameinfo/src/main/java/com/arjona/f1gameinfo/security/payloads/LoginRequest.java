package com.arjona.f1gameinfo.security.payloads;

/**
 * POJO creado para recibir
 * una petición de login
 */
public class LoginRequest {
    
    private String username;
    private String password;
    private String otp;
    
    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
