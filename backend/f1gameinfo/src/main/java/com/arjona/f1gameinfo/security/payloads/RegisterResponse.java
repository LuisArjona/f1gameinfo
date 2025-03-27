package com.arjona.f1gameinfo.security.payloads;

/**
 * POJO creado para devolver el qr y
 * secreto de una petición de registro
 */
public class RegisterResponse {
    private String qr;
    private String secreto;

    public RegisterResponse(String qr, String secreto) {
        this.qr = qr;
        this.secreto = secreto;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public String getSecreto() {
        return secreto;
    }

    public void setSecreto(String secreto) {
        this.secreto = secreto;
    }
}
