package com.utego.saasapiclient.exceptions;

public class Forbidden extends SaasClientException {
    public Forbidden() {
        super("This operation is forbidden");
    }
}
