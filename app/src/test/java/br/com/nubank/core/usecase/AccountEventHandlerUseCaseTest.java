package br.com.nubank.core.usecase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.AccountEvent;

import static org.assertj.core.api.Assertions.assertThat;

class AccountEventHandlerUseCaseTest {
    
    AccountManager accountManager = mock(AccountManager.class);
    AccountEventHandlerUseCase useCase = new AccountEventHandlerUseCase(accountManager);

    @Test
    void when_dont_exist_an_account_should_return_an_account_without_violations() {
        var expetedAccount = new Account(true, 100);

        var event = new AccountEvent();
        event.setAccount(new Account(true, 100));

        var accountState = useCase.execute(event);

        assertThat(accountState.getAccount()).usingRecursiveComparison().isEqualTo(expetedAccount);
        assertThat(accountState.getViolations()).isEmpty();
    }

    @Test
    void when_already_exist_an_account_should_contains_violations() {
        var expetedAccount = new Account(false, 90);
        
        var event = new AccountEvent();
        event.setAccount(new Account(true, 100));
        
        when(accountManager.getAccount()).thenReturn(new Account(false, 90));
        var accountState = useCase.execute(event);

        assertThat(accountState.getAccount()).usingRecursiveComparison().isEqualTo(expetedAccount);
        assertThat(accountState.getViolations()).containsExactly("account-already-initialized");
    }
}
