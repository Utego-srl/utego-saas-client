package com.utego.saasapiclient.data.readconflicts;

import java.util.Objects;

public class ReadConflictsResponse {
    public Boolean isConflicted;

    public ReadConflictsResponse(Boolean isConflicted) {
        this.isConflicted = isConflicted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReadConflictsResponse that = (ReadConflictsResponse) o;
        return isConflicted.equals(that.isConflicted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isConflicted);
    }
}
