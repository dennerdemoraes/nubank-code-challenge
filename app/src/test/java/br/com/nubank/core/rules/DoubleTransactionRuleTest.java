package br.com.nubank.core.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

class DoubleTransactionRuleTest {

    AccountManager accountManager = mock(AccountManager.class);
    DoubleTransactionRule doubleTransactionRule = new DoubleTransactionRule(accountManager);

    @Test
    void rule_should_return_null_when_is_valid() {
        var result = doubleTransactionRule.execute(new Account(true, 100), new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        when(accountManager.getLastTransactionsByMerchant(anyString(), anyInt(), any())).thenReturn(Collections.emptyList());
        
        assertThat(result).isNull();
    }

    @Test
    void rule_should_return_motive_to_fail_when_is_invalid() {
        var transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        when(accountManager.getLastTransactionsByMerchant(anyString(), anyInt(), any())).thenReturn(transactions);
        
        var result = doubleTransactionRule.execute(new Account(true, 100), new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        assertThat(result).isEqualTo("double-transaction");
    }
    
}
