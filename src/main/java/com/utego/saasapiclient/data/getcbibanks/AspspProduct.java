package com.utego.saasapiclient.data.getcbibanks;

import java.util.Objects;

public class AspspProduct {
    public String aspspProductCode;
    public String aspspProductDescription;
    public String aspspProductUuid;

    public AspspProduct(String aspspProductCode, String aspspProductDescription, String aspspProductUuid) {
        this.aspspProductCode = aspspProductCode;
        this.aspspProductDescription = aspspProductDescription;
        this.aspspProductUuid = aspspProductUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AspspProduct that = (AspspProduct) o;
        return aspspProductCode.equals(that.aspspProductCode) && aspspProductDescription.equals(that.aspspProductDescription) && aspspProductUuid.equals(that.aspspProductUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aspspProductCode, aspspProductDescription, aspspProductUuid);
    }
}
