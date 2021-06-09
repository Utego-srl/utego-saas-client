package com.utego.saasapiclient.data.startsca;

import java.util.Objects;

public class SCAKindDecoupledMessage implements SCAKind {
    public String message;

    public SCAKindDecoupledMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SCAKindDecoupledMessage that = (SCAKindDecoupledMessage) o;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
