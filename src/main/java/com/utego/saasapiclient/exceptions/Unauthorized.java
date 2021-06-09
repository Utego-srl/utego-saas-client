package com.utego.saasapiclient.exceptions;

public class Unauthorized  extends SaasClientException {
    public Unauthorized() {
        super("Your authorization data is not correct");
    }
}
