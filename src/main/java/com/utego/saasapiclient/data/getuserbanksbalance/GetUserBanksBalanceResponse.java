package com.utego.saasapiclient.data.getuserbanksbalance;

import java.math.BigDecimal;
import java.util.Objects;

public class GetUserBanksBalanceResponse {
    public BigDecimal balance;

    public GetUserBanksBalanceResponse(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetUserBanksBalanceResponse that = (GetUserBanksBalanceResponse) o;
        return balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }
}
