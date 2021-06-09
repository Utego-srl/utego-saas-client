package com.utego.saasapiclient.data.common;

import java.math.BigDecimal;
import java.util.Objects;

public class TransactionSearchRequest {
    public String description;
    public BigDecimal moreThanAmount; //can be null
    public BigDecimal lessThanAmount; //can be null
    public BigDecimal exactAmount; //can be null
    public Boolean descSort; //can be null

    public TransactionSearchRequest(String description, BigDecimal moreThanAmount, BigDecimal lessThanAmount, BigDecimal exactAmount, Boolean descSort) {
        this.description = description;
        this.moreThanAmount = moreThanAmount;
        this.lessThanAmount = lessThanAmount;
        this.exactAmount = exactAmount;
        this.descSort = descSort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionSearchRequest that = (TransactionSearchRequest) o;
        return Objects.equals(description, that.description) && Objects.equals(moreThanAmount, that.moreThanAmount) && Objects.equals(lessThanAmount, that.lessThanAmount) && Objects.equals(exactAmount, that.exactAmount) && Objects.equals(descSort, that.descSort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, moreThanAmount, lessThanAmount, exactAmount, descSort);
    }
}
