package com.utego.saasapiclient.data.createbank;


import com.utego.saasapiclient.data.common.Adapter;

import java.util.Objects;

public class CreateBankRequest {
    public String name;
    public Adapter adapter;

    public CreateBankRequest(String name, Adapter adapter) {
        this.name = name;
        this.adapter = adapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateBankRequest that = (CreateBankRequest) o;
        return name.equals(that.name) && adapter == that.adapter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, adapter);
    }
}
