package br.com.nubank.core.rules;

import org.junit.jupiter.api.Test;

import br.com.nubank.core.entity.Account;

import static org.assertj.core.api.Assertions.assertThat;

class AccountNotInitializedRuleTest {

    AccountNotInitializedRule accountNotInitializedRule = new AccountNotInitializedRule();

    @Test
    void rule_should_return_null_when_is_valid() {
        var result = accountNotInitializedRule.execute(new Account(true, 100), null);

        assertThat(result).isNull();
    }

    @Test
    void rule_should_return_motive_to_fail_when_is_invalid() {
        var result = accountNotInitializedRule.execute(null, null);

        assertThat(result).isEqualTo("account-not-initialized");
    }
    
}
