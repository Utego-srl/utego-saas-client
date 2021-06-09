package com.utego.saasapiclient.exceptions;

public class BadRequest extends SaasClientException {
    public BadRequest() {
        super("Your json is invalid");
    }
}
