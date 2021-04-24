package br.com.nubank.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    @JsonProperty(value = "active-card")
    private Boolean activeCard;

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
}