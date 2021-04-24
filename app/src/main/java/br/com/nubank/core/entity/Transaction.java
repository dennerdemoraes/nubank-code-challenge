package br.com.nubank.core.entity;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

public class Transaction {
    private String merchant;
    private Integer amount;
    private ZonedDateTime time;

    public Transaction() {}

    public Transaction(String merchant, Integer amount, ZonedDateTime time) {
        this.merchant = merchant;
        this.amount = amount;
        this.time = time;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(@NotNull String merchant) {
        this.merchant = merchant;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(@NotNull Integer amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(@NotNull ZonedDateTime time) {
        this.time = time;
    }
}
