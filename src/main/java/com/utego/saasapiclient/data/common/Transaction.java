package com.utego.saasapiclient.data.common;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Transaction {
    public Long id;
    public Long accountId;
    public BigDecimal transactionValue;
    public BigDecimal beforeBalance;  //can be null
    public BigDecimal afterBalance;  //can be null
    public ZonedDateTime insertDate;
    public ZonedDateTime transactionDate;  //can be null
    public String thirdPartyId;  //can be null
    public String reason;  //can be null

    public Transaction(Long id, Long accountId, BigDecimal transactionValue, BigDecimal beforeBalance,
                       BigDecimal afterBalance, ZonedDateTime insertDate, ZonedDateTime transactionDate,
                       String thirdPartyId, String reason) {
        this.id = id;
        this.accountId = accountId;
        this.transactionValue = transactionValue;
        this.beforeBalance = beforeBalance;
        this.afterBalance = afterBalance;
        this.insertDate = insertDate;
        this.transactionDate = transactionDate;
        this.thirdPartyId = thirdPartyId;
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id) && accountId.equals(that.accountId) && transactionValue.equals(that.transactionValue) && Objects.equals(beforeBalance, that.beforeBalance) && Objects.equals(afterBalance, that.afterBalance) && insertDate.equals(that.insertDate) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(thirdPartyId, that.thirdPartyId) && Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountId, transactionValue, beforeBalance, afterBalance, insertDate, transactionDate, thirdPartyId, reason);
    }
}
