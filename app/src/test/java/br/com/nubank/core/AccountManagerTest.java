package br.com.nubank.core;

import org.junit.jupiter.api.Test;

import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZonedDateTime;

class AccountManagerTest {

    AccountManager accountManager = new AccountManager();
    
    @Test
    void when_add_transaction_the_availiable_limit_should_decrease() {
        accountManager.setAccount(new Account(true, 100));
        accountManager.addTransaction(new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        assertThat(accountManager.getAccount().getAvailableLimit()).isEqualTo(90);
    }

    @Test
    void transaction_should_not_be_empty_when_searched_2_minutes_later() {
        accountManager.setAccount(new Account(true, 100));
        accountManager.addTransaction(new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        var transactions = accountManager.getLastTransactions(ZonedDateTime.parse("2021-04-25T17:01:59.000Z"));

        assertThat(transactions).isNotEmpty();
    }

    @Test
    void transactions_should_be_empty_when_searched_more_than_2_minutes_later() {
        accountManager.setAccount(new Account(true, 100));
        accountManager.addTransaction(new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        var transactions = accountManager.getLastTransactions(ZonedDateTime.parse("2021-04-25T17:03:00.000Z"));

        assertThat(transactions).isEmpty();
    }

    @Test
    void transaction_should_not_be_empty_when_searched_2_mintes_later_with_merchant_and_amount() {
        accountManager.setAccount(new Account(true, 100));
        accountManager.addTransaction(new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        var transactions = accountManager.getLastTransactionsByMerchant("Test", 10, ZonedDateTime.parse("2021-04-25T17:01:59.000Z"));

        assertThat(transactions).isNotEmpty();
    }

    @Test
    void transaction_should_be_emtpty_when_searched_with_more_than_2_minutes_later_with_merchant_and_amount() {
        accountManager.setAccount(new Account(true, 100));
        accountManager.addTransaction(new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        var transactions = accountManager.getLastTransactionsByMerchant("Test", 10, ZonedDateTime.parse("2021-04-25T17:03:00.000Z"));

        assertThat(transactions).isEmpty();
    }
}
