package com.utego.saasapiclient.data.startsca;

import java.util.Objects;

public class StartSCAResponse {
    public Long scaId;
    public SCAKind kind;

    public StartSCAResponse(Long scaId, SCAKind kind) {
        this.scaId = scaId;
        this.kind = kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartSCAResponse that = (StartSCAResponse) o;
        return scaId.equals(that.scaId) && kind.equals(that.kind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scaId, kind);
    }
}
