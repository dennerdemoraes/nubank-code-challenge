package br.com.nubank.core.entity;

public abstract class Event<T> {
    private EventType type;

    public abstract T getPayload();
    
    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
}
