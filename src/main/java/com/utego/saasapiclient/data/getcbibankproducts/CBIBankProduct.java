package com.utego.saasapiclient.data.getcbibankproducts;

import java.util.Objects;

public class CBIBankProduct {
    public String aspspProductCode;
    public String aspspProductDescription;
    public String aspspProductUuid;
    public Boolean isCreditCard;//can be null
    public Boolean isBankAccount; //can be null

    public CBIBankProduct(String aspspProductCode,
                          String aspspProductDescription,
                          String aspspProductUuid,
                          Boolean isCreditCard,
                          Boolean isBankAccount) {
        this.aspspProductCode = aspspProductCode;
        this.aspspProductDescription = aspspProductDescription;
        this.aspspProductUuid = aspspProductUuid;
        this.isCreditCard = isCreditCard;
        this.isBankAccount = isBankAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CBIBankProduct that = (CBIBankProduct) o;
        return aspspProductCode.equals(that.aspspProductCode) && aspspProductDescription.equals(that.aspspProductDescription) && aspspProductUuid.equals(that.aspspProductUuid) && Objects.equals(isCreditCard, that.isCreditCard) && Objects.equals(isBankAccount, that.isBankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aspspProductCode, aspspProductDescription, aspspProductUuid, isCreditCard, isBankAccount);
    }
}
