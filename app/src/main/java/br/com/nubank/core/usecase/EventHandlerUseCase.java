package br.com.nubank.core.usecase;

import br.com.nubank.core.entity.AccountState;
import br.com.nubank.core.entity.Event;

public interface EventHandlerUseCase {
    AccountState execute(Event<?> event);
}
