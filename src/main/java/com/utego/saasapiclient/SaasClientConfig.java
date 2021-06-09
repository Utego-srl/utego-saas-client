package com.utego.saasapiclient;

public class SaasClientConfig {
    protected String url;
    protected String accessKey;
    protected String secretKey;

    public SaasClientConfig(String url, String accessKey, String secretKey) {
        this.url = url;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getUrl() {
        return url;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
