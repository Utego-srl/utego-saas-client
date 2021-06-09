package com.utego.saasapiclient.data.startsca;

import java.util.Objects;

public class SCAKindRedirect implements SCAKind {
    public String url;

    public SCAKindRedirect(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCAKindRedirect that = (SCAKindRedirect) o;
        return url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
