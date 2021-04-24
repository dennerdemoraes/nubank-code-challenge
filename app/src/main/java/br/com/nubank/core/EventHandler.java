package br.com.nubank.core;

import br.com.nubank.core.entity.Event;

public interface EventHandler {
    void execute(Event event);
}
