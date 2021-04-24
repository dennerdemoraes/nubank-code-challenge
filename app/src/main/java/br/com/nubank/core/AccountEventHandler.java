package br.com.nubank.core;

import br.com.nubank.core.entity.Event;

public class AccountEventHandler implements EventHandler {

    @Override
    public void execute(Event event) {
        System.out.println(event);
    }
}
