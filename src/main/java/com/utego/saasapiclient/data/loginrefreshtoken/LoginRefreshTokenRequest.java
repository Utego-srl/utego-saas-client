package com.utego.saasapiclient.data.loginrefreshtoken;

import java.util.Objects;

public class LoginRefreshTokenRequest {
    public String token;

    public LoginRefreshTokenRequest(String token) {
        this.token = token;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginRefreshTokenRequest that = (LoginRefreshTokenRequest) o;
        return token.equals(that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
