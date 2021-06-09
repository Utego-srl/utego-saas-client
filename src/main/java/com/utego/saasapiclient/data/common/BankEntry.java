package com.utego.saasapiclient.data.common;

import java.util.Objects;

public class BankEntry {
    public Long id;
    public String name;
    public String slug; //can be null
    public AdapterGroup adapter; //can be null
    public Boolean enabled;

    public BankEntry(Long id, String name, String slug, AdapterGroup adapter, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.adapter = adapter;
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankEntry bankEntry = (BankEntry) o;
        return id.equals(bankEntry.id) && name.equals(bankEntry.name) && Objects.equals(slug, bankEntry.slug) && adapter == bankEntry.adapter && enabled.equals(bankEntry.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slug, adapter, enabled);
    }
}
