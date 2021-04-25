package br.com.nubank.core.usecase;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;
import br.com.nubank.core.entity.TransactionEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionEventHandlerUseCaseTest {
    
    AccountManager accountManager = mock(AccountManager.class);
    TransactionEventHandlerUseCase transactionEventHandlerUseCase = new TransactionEventHandlerUseCase(accountManager);

    @Test
    void given_a_valid_scenario_account_state_shold_be_returned_without_violations() {
        var event = new TransactionEvent();
        event.setTransaction(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        when(accountManager.getAccount()).thenReturn(new Account(true, 100));
        var accountState = transactionEventHandlerUseCase.execute(event);

        assertThat(accountState.getViolations()).isEmpty();
    }

    @Test
    void given_an_scenario_where_account_is_not_initialized_account_state_shold_be_returned_with_violations() {
        var event = new TransactionEvent();
        event.setTransaction(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        when(accountManager.getAccount()).thenReturn(null);
        var accountState = transactionEventHandlerUseCase.execute(event);

        assertThat(accountState.getViolations()).containsExactly("account-not-initialized");
    }

    @Test
    void given_an_scenario_where_card_is_not_active_account_state_shold_be_returned_with_violations() {
        var event = new TransactionEvent();
        event.setTransaction(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        when(accountManager.getAccount()).thenReturn(new Account(false, 100));
        var accountState = transactionEventHandlerUseCase.execute(event);

        assertThat(accountState.getViolations()).containsExactly("card-not-active");
    }

    @Test
    void given_an_scenario_where_account_have_not_suficient_limit_account_state_shold_be_returned_with_violations() {
        var event = new TransactionEvent();
        event.setTransaction(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        when(accountManager.getAccount()).thenReturn(new Account(true, 10));
        var accountState = transactionEventHandlerUseCase.execute(event);

        assertThat(accountState.getViolations()).containsExactly("insufficient-limit");
    }

    @Test
    void given_an_scenario_where_there_are_more_then_3_transactions_account_state_shold_be_returned_with_violations() {
        var event = new TransactionEvent();
        event.setTransaction(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        var transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:01:10.000Z")));
        transactions.add(new Transaction("Test2", 20, ZonedDateTime.parse("2021-04-25T17:01:20.000Z")));
        transactions.add(new Transaction("Test3", 50, ZonedDateTime.parse("2021-04-25T17:01:30.000Z")));

        when(accountManager.getLastTransactions(any())).thenReturn(transactions);
        when(accountManager.getAccount()).thenReturn(new Account(true, 1000));
        var accountState = transactionEventHandlerUseCase.execute(event);

        assertThat(accountState.getViolations()).containsExactly("high-frequency-small-interval");
    }

    @Test
    void given_an_scenario_where_there_are_insuficient_limit_and_more_then_3_transactions_account_state_shold_be_returned_with_violations() {
        var event = new TransactionEvent();
        event.setTransaction(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        var transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:01:10.000Z")));
        transactions.add(new Transaction("Test2", 20, ZonedDateTime.parse("2021-04-25T17:01:20.000Z")));
        transactions.add(new Transaction("Test3", 50, ZonedDateTime.parse("2021-04-25T17:01:30.000Z")));

        when(accountManager.getLastTransactions(any())).thenReturn(transactions);
        when(accountManager.getAccount()).thenReturn(new Account(true, 10));
        var accountState = transactionEventHandlerUseCase.execute(event);

        assertThat(accountState.getViolations()).containsExactly("insufficient-limit", "high-frequency-small-interval");
    }

    @Test
    void given_an_scenario_where_there_is_duplicated_transactions_account_state_shold_be_returned_with_violations() {
        var event = new TransactionEvent();
        event.setTransaction(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        var transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:01:01.000Z")));

        when(accountManager.getLastTransactionsByMerchant(anyString(), anyInt(), any())).thenReturn(transactions);
        when(accountManager.getAccount()).thenReturn(new Account(true, 1000));
        var accountState = transactionEventHandlerUseCase.execute(event);

        assertThat(accountState.getViolations()).containsExactly("double-transaction");
    }

    @Test
    void given_an_scenario_where_there_are_insuficient_limit_and_duplicated_transactions_account_state_shold_be_returned_with_violations() {
        var event = new TransactionEvent();
        event.setTransaction(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        var transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction("Test", 20, ZonedDateTime.parse("2021-04-25T17:01:01.000Z")));

        when(accountManager.getLastTransactionsByMerchant(anyString(), anyInt(), any())).thenReturn(transactions);
        when(accountManager.getAccount()).thenReturn(new Account(true, 10));
        var accountState = transactionEventHandlerUseCase.execute(event);

        assertThat(accountState.getViolations()).containsExactly("insufficient-limit", "double-transaction");
    }
}
