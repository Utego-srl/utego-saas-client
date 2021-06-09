package com.utego.saasapiclient.exceptions;

public class NotFound extends SaasClientException {
    public NotFound() {
        super("The object you are looking for do not exist.");
    }
}
