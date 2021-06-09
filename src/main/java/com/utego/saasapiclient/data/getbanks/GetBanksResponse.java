package com.utego.saasapiclient.data.getbanks;

import com.utego.saasapiclient.data.common.Bank;

import java.util.List;
import java.util.Objects;

public class GetBanksResponse {
    public Long count;
    public List<Bank> result;

    public GetBanksResponse(Long count, List<Bank> result) {
        this.count = count;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetBanksResponse that = (GetBanksResponse) o;
        return count.equals(that.count) && result.equals(that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, result);
    }
}
