package br.com.nubank.core.rules;

import org.junit.jupiter.api.Test;

import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.ZonedDateTime;

class InsuficientLimitRuleTest {

    InsuficientLimitRule insuficientLimitRule = new InsuficientLimitRule();

    @Test
    void rule_should_return_null_when_is_valid() {
        var result = insuficientLimitRule.execute(new Account(true, 100), new Transaction("Test2", 20, ZonedDateTime.parse("2021-04-25T17:01:20.000Z")));

        assertThat(result).isNull();
    }

    @Test
    void rule_should_return_motive_to_fail_when_is_invalid() {
        var result = insuficientLimitRule.execute(new Account(true, 10), new Transaction("Test2", 20, ZonedDateTime.parse("2021-04-25T17:01:20.000Z")));

        assertThat(result).isEqualTo("insufficient-limit");
    }
    
}
