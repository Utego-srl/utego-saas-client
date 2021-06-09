package com.utego.saasapiclient.data.common;

import java.util.Objects;

public class BankAccount implements AccountInfo {
    public String iban;

    public BankAccount(String iban) {
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return iban.equals(that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }
}