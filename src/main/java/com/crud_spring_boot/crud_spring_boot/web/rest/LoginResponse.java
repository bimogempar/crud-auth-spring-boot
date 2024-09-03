package com.crud_spring_boot.crud_spring_boot.web.rest;

public class LoginResponse {
    private String token;
    private long expiresIn;

    public String getToken(){
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
