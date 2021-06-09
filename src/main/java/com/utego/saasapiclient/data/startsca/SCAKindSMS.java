package com.utego.saasapiclient.data.startsca;

import java.util.Objects;

public class SCAKindSMS implements SCAKind {
    public String phoneNumber; //can be null

    public SCAKindSMS(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCAKindSMS that = (SCAKindSMS) o;
        return phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}
