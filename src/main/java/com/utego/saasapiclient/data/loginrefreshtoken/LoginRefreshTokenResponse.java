package com.utego.saasapiclient.data.loginrefreshtoken;

import java.util.Objects;

public class LoginRefreshTokenResponse {
    protected String accessToken;
    protected String tokenType;
    protected String refreshToken; //can be null


    public LoginRefreshTokenResponse(String accessToken, String tokenType, String refreshToken) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.refreshToken = refreshToken;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRefreshTokenResponse that = (LoginRefreshTokenResponse) o;
        return accessToken.equals(that.accessToken) && tokenType.equals(that.tokenType) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, tokenType, refreshToken);
    }
}
