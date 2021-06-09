package com.utego.saasapiclient.data.solvescachallenge;

import java.util.Objects;

public class SolveSCAChallengeRequest {
    public Long scaId;
    public String data;

    public SolveSCAChallengeRequest(Long scaId, String data) {
        this.scaId = scaId;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolveSCAChallengeRequest that = (SolveSCAChallengeRequest) o;
        return scaId.equals(that.scaId) && data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scaId, data);
    }
}
