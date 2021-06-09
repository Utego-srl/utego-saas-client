package com.utego.saasapiclient.data.common;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

public class TransactionResult {
    public Long id;
    public BigDecimal value;
    public BigDecimal balanceBefore; //can be null
    public BigDecimal balanceAfter; //can be null
    public String bankName;
    public Long bankId;
    public Long accountId;
    public OffsetDateTime transactionDate; //can be null
    public String reason; //can be null

    public TransactionResult(Long id, BigDecimal value, BigDecimal balanceBefore, BigDecimal balanceAfter,
                             String bankName, Long bankId, Long accountId,
                             OffsetDateTime transactionDate, String reason) {
        this.id = id;
        this.value = value;
        this.balanceBefore = balanceBefore;
        this.balanceAfter = balanceAfter;
        this.bankName = bankName;
        this.bankId = bankId;
        this.accountId = accountId;
        this.transactionDate = transactionDate;
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionResult that = (TransactionResult) o;
        return id.equals(that.id) && value.equals(that.value) && Objects.equals(balanceBefore, that.balanceBefore) && Objects.equals(balanceAfter, that.balanceAfter) && bankName.equals(that.bankName) && bankId.equals(that.bankId) && accountId.equals(that.accountId) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, balanceBefore, balanceAfter, bankName, bankId, accountId, transactionDate, reason);
    }
}
