package com.stm.Entity;

public enum TokenData {
    CLIENT("clientType"),
    ID("userID"),
    NAME("username"),
    CREATE_DATE("token_create_date"),
    EXPIRATION_DATE("token_expiration_date");

    private final String TokenData;

    TokenData(String TokenData) {
        this.TokenData = TokenData;
    }

    public String getTokenData() {
        return TokenData;
    }
}

