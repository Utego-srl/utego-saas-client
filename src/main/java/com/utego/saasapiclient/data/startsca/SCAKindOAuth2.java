package com.utego.saasapiclient.data.startsca;

import java.util.Objects;

public class SCAKindOAuth2 implements SCAKind {
    public String url;
    public String param;

    public SCAKindOAuth2(String url, String param) {
        this.url = url;
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCAKindOAuth2 that = (SCAKindOAuth2) o;
        return url.equals(that.url) && param.equals(that.param);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, param);
    }
}
