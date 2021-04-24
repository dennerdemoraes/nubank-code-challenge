package br.com.nubank.core;

import br.com.nubank.core.entity.Event;

public class TransactionEventHandler implements EventHandler {

    @Override
    public void execute(Event event) {
        System.out.println(event);
    }
}
