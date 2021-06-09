package com.utego.saasapiclient.data.common;

import com.utego.saasapiclient.data.getbanks.GetBanksResponse;

import java.util.List;
import java.util.Objects;

public class PaginedTransactionResult {
    public Long count;
    public List<TransactionResult> result;

    public PaginedTransactionResult(Long count, List<TransactionResult> result) {
        this.count = count;
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginedTransactionResult that = (PaginedTransactionResult) o;
        return count.equals(that.count) && result.equals(that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, result);
    }
}
