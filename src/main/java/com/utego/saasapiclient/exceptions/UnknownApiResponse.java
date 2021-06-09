package com.utego.saasapiclient.exceptions;

import okhttp3.Response;

import java.io.IOException;

public class UnknownApiResponse extends SaasClientException {

    private static String readBody(Response response) {
        String body;
        try {
            body = response.body().string();
        } catch (IOException e) {
            body = "";
        }
        return body;
    }
    public UnknownApiResponse(Response response) {
        super("Request failed with status " + response.code() + " and body " + readBody(response));
    }
}
