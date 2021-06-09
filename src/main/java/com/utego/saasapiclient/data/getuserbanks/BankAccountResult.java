package com.utego.saasapiclient.data.getuserbanks;

import com.utego.saasapiclient.data.common.AccountType;
import com.utego.saasapiclient.data.common.AdapterGroup;
import com.utego.saasapiclient.data.common.SCAStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;

public class BankAccountResult {
    public Long accountId;
    public Long bankId;
    public String name;
    public BigDecimal balance;
    public BigDecimal availableBalance; //can be null
    public BigDecimal maxPlafond; //can be null
    public String iban; //can be null
    public String address; //can be null
    public String cardNumber; //can be null
    public LocalDate expirationDate; //can be null
    public AccountType accountType;
    public SCAStatus scaStatus; //can be null
    public Boolean refreshFailed;
    public Boolean outOfRefreshes;
    public OffsetDateTime lastRefresh; //can be null
    public AdapterGroup adapterName; //can be null

    public BankAccountResult(Long accountId, Long bankId, String name, BigDecimal balance,
                             BigDecimal availableBalance, BigDecimal maxPlafond, String iban, String address,
                             String cardNumber, LocalDate expirationDate, AccountType accountType, SCAStatus scaStatus,
                             Boolean refreshFailed, Boolean outOfRefreshes, OffsetDateTime lastRefresh,
                             AdapterGroup adapterName) {
        this.accountId = accountId;
        this.bankId = bankId;
        this.name = name;
        this.balance = balance;
        this.availableBalance = availableBalance;
        this.maxPlafond = maxPlafond;
        this.iban = iban;
        this.address = address;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.accountType = accountType;
        this.scaStatus = scaStatus;
        this.refreshFailed = refreshFailed;
        this.outOfRefreshes = outOfRefreshes;
        this.lastRefresh = lastRefresh;
        this.adapterName = adapterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountResult that = (BankAccountResult) o;
        return accountId.equals(that.accountId) && bankId.equals(that.bankId) && name.equals(that.name) && balance.equals(that.balance) && Objects.equals(availableBalance, that.availableBalance) && Objects.equals(maxPlafond, that.maxPlafond) && Objects.equals(iban, that.iban) && Objects.equals(address, that.address) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(expirationDate, that.expirationDate) && accountType == that.accountType && scaStatus == that.scaStatus && refreshFailed.equals(that.refreshFailed) && outOfRefreshes.equals(that.outOfRefreshes) && Objects.equals(lastRefresh, that.lastRefresh) && adapterName == that.adapterName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, bankId, name, balance, availableBalance, maxPlafond, iban, address, cardNumber, expirationDate, accountType, scaStatus, refreshFailed, outOfRefreshes, lastRefresh, adapterName);
    }
}
