package br.com.nubank.core.usecase;

import br.com.nubank.application.JsonParser;
import br.com.nubank.core.EventHandlerManager;
import br.com.nubank.core.entity.Event;

import java.util.Objects;

import com.google.inject.Inject;

public class AuthorizerUseCase {

    private final EventHandlerManager eventHandlerManager;

    @Inject
    public AuthorizerUseCase(EventHandlerManager eventHandlerManager) { 
        this.eventHandlerManager = eventHandlerManager;
    }

    public void execute(String event) {
        var eventObject = JsonParser.parseJsonToObject(event, Event.class);

        if (Objects.nonNull(eventObject)) {
            eventHandlerManager.setEventHandler(eventObject.getType());
            eventHandlerManager.execute(eventObject);
        }
    }
}
