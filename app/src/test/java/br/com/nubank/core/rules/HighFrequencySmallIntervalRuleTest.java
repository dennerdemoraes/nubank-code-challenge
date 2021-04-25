package br.com.nubank.core.rules;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Transaction;

class HighFrequencySmallIntervalRuleTest {
    
    AccountManager accountManager = mock(AccountManager.class);
    HighFrequencySmallIntervalRule highFrequencySmallIntervalRule = new HighFrequencySmallIntervalRule(accountManager);

    @Test
    void rule_should_return_null_when_is_valid() {
        var result = highFrequencySmallIntervalRule.execute(null, new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        when(accountManager.getLastTransactions(any())).thenReturn(Collections.emptyList());
        
        assertThat(result).isNull();
    }

    @Test
    void rule_should_return_motive_to_fail_when_is_invalid() {
        var transactions = new ArrayList<Transaction>();
        transactions.add(new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:01:10.000Z")));
        transactions.add(new Transaction("Test2", 20, ZonedDateTime.parse("2021-04-25T17:01:20.000Z")));
        transactions.add(new Transaction("Test3", 50, ZonedDateTime.parse("2021-04-25T17:01:30.000Z")));

        when(accountManager.getLastTransactions(any())).thenReturn(transactions);
        
        var result = highFrequencySmallIntervalRule.execute(null, new Transaction("Test", 10, ZonedDateTime.parse("2021-04-25T17:00:00.000Z")));

        assertThat(result).isEqualTo("high-frequency-small-interval");
    }
}
