package com.utego.saasapiclient.data.common;

import java.util.Objects;

public class Bank {
    public Long id;
    public String name;
    public Adapter adapterName; //can be null
    public String deletedName; //can be null
    public Boolean isDeleted;
    public Boolean enabled;

    public Bank(Long id, String name, Adapter adapterName, String deletedName, Boolean isDeleted, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.adapterName = adapterName;
        this.deletedName = deletedName;
        this.isDeleted = isDeleted;
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id.equals(bank.id) && name.equals(bank.name) && adapterName == bank.adapterName && Objects.equals(deletedName, bank.deletedName) && isDeleted.equals(bank.isDeleted) && enabled.equals(bank.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, adapterName, deletedName, isDeleted, enabled);
    }
}
