package com.utego.saasapiclient.data.retrieveadapterscastatus;

import java.util.Objects;

public class RetrieveAdapterSCAStatusResponse {
    public SCAStatus status; //can be null

    public RetrieveAdapterSCAStatusResponse(SCAStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveAdapterSCAStatusResponse that = (RetrieveAdapterSCAStatusResponse) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
