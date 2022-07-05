package com.stm.dto;

import lombok.Builder;

@Builder
public class RegistrationRsDto {

    private boolean verification;
    private String token;

    private int id;

    public boolean isVerification() {
        return verification;
    }
    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
