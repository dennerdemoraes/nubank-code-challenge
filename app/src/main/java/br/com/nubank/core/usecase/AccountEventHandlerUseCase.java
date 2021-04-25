package br.com.nubank.core.usecase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import com.google.inject.Inject;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.AccountState;
import br.com.nubank.core.entity.Event;

public class AccountEventHandlerUseCase implements EventHandlerUseCase {

    private final AccountManager accountManager;

    @Inject
    public AccountEventHandlerUseCase(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public AccountState execute(Event<?> event) {
        var account = (Account) event.getPayload();

        if (Objects.isNull(accountManager.getAccount())) {
            accountManager.setAccount(account);
            return new AccountState(account, new ArrayList<>());
        }

        return new AccountState(accountManager.getAccount(), Collections.singletonList("account-already-initialized"));
    }
}
