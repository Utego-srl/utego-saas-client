package com.utego.saasapiclient;

import com.utego.saasapiclient.exceptions.SaasClientException;
import com.utego.saasapiclient.exceptions.Unauthorized;
import org.junit.Test;

import java.io.IOException;

public class LoginTest {

    @Test(expected = IOException.class)
    public void login_should_throws_IOException_for_incorrect_url() throws SaasClientException, IOException {
        SaasClientConfig config = new SaasClientConfig(
                "http://local.local.local",
                System.getenv("SAAS_ACCESS_KEY"),
                System.getenv("SAAS_SECRET_KEY")
        );

        SaasClient saasClient = new SaasClient(config);

        saasClient.login();
    }


    @Test(expected = Unauthorized.class)
    public void login_should_throws_Unauthorized_for_incorrect_credentials() throws SaasClientException, IOException {
        SaasClientConfig config = new SaasClientConfig(
                System.getenv("SAAS_URL"),
                "Incorrect",
                "Incorrect"
        );

        SaasClient saasClient = new SaasClient(config);

        saasClient.login();
    }

    @Test
    public void login_should_works_without_exception_for_correct_credentials() throws SaasClientException, IOException {
        SaasClientConfig config = new SaasClientConfig(
                System.getenv("SAAS_URL"),
                System.getenv("SAAS_ACCESS_KEY"),
                System.getenv("SAAS_SECRET_KEY")
        );

        SaasClient saasClient = new SaasClient(config);

        saasClient.login();
    }
}