package com.utego.saasapiclient.data.createaccount;

import com.utego.saasapiclient.data.common.AccountInfo;

import java.util.Objects;

public class CreateAccountRequest {

    public AccountInfo accountInfo;

    public CreateAccountRequest(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateAccountRequest that = (CreateAccountRequest) o;
        return accountInfo.equals(that.accountInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountInfo);
    }
}

