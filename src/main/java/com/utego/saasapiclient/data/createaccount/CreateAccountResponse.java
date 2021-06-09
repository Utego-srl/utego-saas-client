package com.utego.saasapiclient.data.createaccount;

import java.util.Objects;

public class CreateAccountResponse {
    public Long accountId;

    public CreateAccountResponse(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountResponse that = (CreateAccountResponse) o;
        return accountId.equals(that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }
}
