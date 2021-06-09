package com.utego.saasapiclient.exceptions;

public class ParseJsonError extends SaasClientException {
    public ParseJsonError(String errorMessage) {
        super(errorMessage);
    }
}
