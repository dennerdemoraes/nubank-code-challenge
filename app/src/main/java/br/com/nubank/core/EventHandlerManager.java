package br.com.nubank.core;

import com.google.inject.Inject;

import br.com.nubank.core.entity.AccountState;
import br.com.nubank.core.entity.Event;
import br.com.nubank.core.entity.EventType;
import br.com.nubank.core.usecase.AccountEventHandlerUseCase;
import br.com.nubank.core.usecase.EventHandlerUseCase;
import br.com.nubank.core.usecase.TransactionEventHandlerUseCase;

public class EventHandlerManager {

    private final AccountManager accountManager;
    
    private EventHandlerUseCase useCase;

    @Inject
    public EventHandlerManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setEventHandler(EventType eventType) {
        switch (eventType) {
            case ACCOUNT:
                useCase = new AccountEventHandlerUseCase(accountManager);
                break;
            case TRANSACTION:
                useCase = new TransactionEventHandlerUseCase(accountManager);
                break;
        }
    }

    public AccountState execute(Event<?> event) {
        return useCase.execute(event);
    }
}
