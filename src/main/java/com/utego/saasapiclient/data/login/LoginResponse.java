package com.utego.saasapiclient.data.login;

import java.util.Objects;

public class LoginResponse {
    public String accessToken;
    public String tokenType;
    public String refreshToken; //can be null

    public LoginResponse(String accessToken, String tokenType, String refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResponse that = (LoginResponse) o;
        return accessToken.equals(that.accessToken) && tokenType.equals(that.tokenType) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, tokenType, refreshToken);
    }
}
