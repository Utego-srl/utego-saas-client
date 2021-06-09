package com.utego.saasapiclient;

import com.utego.saasapiclient.data.login.LoginResponse;
import com.utego.saasapiclient.exceptions.SaasClientException;
import com.utego.saasapiclient.exceptions.Unauthorized;
import org.junit.Test;

import java.io.IOException;

public class LoginRefreshTokenTest {

    SaasClientConfig config = new SaasClientConfig(
            System.getenv("SAAS_URL"),
            System.getenv("SAAS_ACCESS_KEY"),
            System.getenv("SAAS_SECRET_KEY")
    );

    SaasClient saasClient = new SaasClient(config);


    @Test(expected = Unauthorized.class)
    public void login_refresh_token_should_not_work_for_invalid_token() throws SaasClientException, IOException {

        saasClient.loginRefreshToken("invalid_refresh_token");
    }

    @Test
    public void login_refresh_token_should_works_for_token_from_login_request() throws SaasClientException, IOException {

        LoginResponse loginResponse = saasClient.login();

        saasClient.loginRefreshToken(loginResponse.refreshToken);
    }
}