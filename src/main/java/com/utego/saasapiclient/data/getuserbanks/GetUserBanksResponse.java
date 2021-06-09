package com.utego.saasapiclient.data.getuserbanks;

import java.util.List;
import java.util.Objects;

public class GetUserBanksResponse {
    public List<BankAccountResult> banks;

    public GetUserBanksResponse(List<BankAccountResult> banks) {
        this.banks = banks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetUserBanksResponse that = (GetUserBanksResponse) o;
        return banks.equals(that.banks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banks);
    }
}
