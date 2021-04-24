package br.com.nubank.core.entity;

public enum EventType {
    TRANSACTION("transactionEventHandler"),
    ACCOUNT("accountEventHandler");

    private final String eventHandler;

    EventType(String eventHandler) {
        this.eventHandler = eventHandler;
    }

    public String getEventHandler() {
        return eventHandler;
    }
}
