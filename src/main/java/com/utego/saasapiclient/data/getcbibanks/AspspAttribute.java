package com.utego.saasapiclient.data.getcbibanks;

import java.util.Objects;

public class AspspAttribute {
    public String attributeName;
    public String attributeValue;

    public AspspAttribute(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AspspAttribute that = (AspspAttribute) o;
        return attributeName.equals(that.attributeName) && attributeValue.equals(that.attributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName, attributeValue);
    }
}
