package com.utego.saasapiclient.data.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Card implements AccountInfo {
    public String number;
    public String address;
    public LocalDate expirationDate;
    public String iban; //can be null
    public BigDecimal maxPlafond; //can be null

    public Card(String number, String address, LocalDate expirationDate, String iban, BigDecimal maxPlafond) {
        this.number = number;
        this.address = address;
        this.expirationDate = expirationDate;
        this.iban = iban;
        this.maxPlafond = maxPlafond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return number.equals(card.number) && address.equals(card.address) && expirationDate.equals(card.expirationDate) && Objects.equals(iban, card.iban) && Objects.equals(maxPlafond, card.maxPlafond);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, address, expirationDate, iban, maxPlafond);
    }
}
