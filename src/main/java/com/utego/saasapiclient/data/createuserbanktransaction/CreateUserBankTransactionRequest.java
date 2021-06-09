package com.utego.saasapiclient.data.createuserbanktransaction;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

public class CreateUserBankTransactionRequest {
    public BigDecimal value;
    public ZonedDateTime transactionDate;
    public String reason; //can be null
    public String iban; //can be null

    public CreateUserBankTransactionRequest(BigDecimal value, ZonedDateTime transactionDate, String reason, String iban) {
        this.value = value;
        this.transactionDate = transactionDate;
        this.reason = reason;
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserBankTransactionRequest that = (CreateUserBankTransactionRequest) o;
        return value.equals(that.value) && transactionDate.equals(that.transactionDate) && Objects.equals(reason, that.reason) && Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, transactionDate, reason, iban);
    }
}
