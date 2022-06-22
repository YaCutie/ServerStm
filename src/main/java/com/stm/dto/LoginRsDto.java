package com.stm.dto;

public class LoginRsDto {
    private boolean verification;
    private String token;

    private int id;

    public boolean isVerification() {
        return verification;
    }
    public void setVerification(boolean verification) {
        this.verification = verification;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return this.token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}