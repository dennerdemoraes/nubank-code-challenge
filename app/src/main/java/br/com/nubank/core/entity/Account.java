package br.com.nubank.core.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    @NotNull
    @JsonProperty(value = "active-card")
    private Boolean activeCard;

    @NotNull
    @JsonProperty(value = "available-limit")
    private Integer availableLimit;

    public Account() {}

    public Account(Boolean activeCard, Integer availableLimit) {
        this.activeCard = activeCard;
        this.availableLimit = availableLimit;
    }

    public Boolean getActiveCard() {
        return activeCard;
    }

    public void setActiveCard(Boolean activeCard) {
        this.activeCard = activeCard;
    }

    public Integer getAvailableLimit() {
        return availableLimit;
    }

    public void setAvailableLimit(Integer availableLimit) {
        this.availableLimit = availableLimit;
    }

    public void withdrawAmount(Integer amount) {
        availableLimit -= amount;
    }
}
