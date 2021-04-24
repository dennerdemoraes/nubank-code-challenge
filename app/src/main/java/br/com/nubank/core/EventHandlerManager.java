package br.com.nubank.core;

import br.com.nubank.core.entity.Event;
import br.com.nubank.core.entity.EventType;

public class EventHandlerManager {
    private EventHandler eventHandler;

    public void setEventHandler(EventType eventType) {
        switch (eventType) {
            case ACCOUNT:
                eventHandler = new AccountEventHandler();
                break;
            case TRANSACTION:
                eventHandler = new TransactionEventHandler();
                break;
        }
    }

    public void execute(Event event) {
        eventHandler.execute(event);
    }
}
