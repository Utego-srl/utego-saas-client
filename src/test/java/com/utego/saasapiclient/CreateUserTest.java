package com.utego.saasapiclient;

import com.utego.saasapiclient.exceptions.SaasClientException;
import org.junit.Test;

import java.io.IOException;

public class CreateUserTest {

    SaasClientConfig config = new SaasClientConfig(
            System.getenv("SAAS_URL"),
            System.getenv("SAAS_ACCESS_KEY"),
            System.getenv("SAAS_SECRET_KEY")
    );

    SaasClient saasClient = new SaasClient(config);

    @Test
    public void create_user_should_works() throws SaasClientException, IOException {

        saasClient.createUser();

    }
}