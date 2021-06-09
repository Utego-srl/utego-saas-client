package com.utego.saasapiclient.data.retrieveoccurringrefresh;

import java.time.Instant;
import java.util.Objects;

public class RetrieveOccurringRefreshResponse {
    public Instant startDate; //can be null

    public RetrieveOccurringRefreshResponse(Instant startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveOccurringRefreshResponse that = (RetrieveOccurringRefreshResponse) o;
        return startDate.equals(that.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate);
    }
}
