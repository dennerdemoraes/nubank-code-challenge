package br.com.nubank.core.rules;

import org.junit.jupiter.api.Test;

import br.com.nubank.core.entity.Account;

import static org.assertj.core.api.Assertions.assertThat;

class CardNotActiveRuleTest {

    CardNotActiveRule cardNotActiveRule = new CardNotActiveRule();

    @Test
    void rule_should_return_null_when_is_valid() {
        var result = cardNotActiveRule.execute(new Account(true, 100), null);

        assertThat(result).isNull();
    }

    @Test
    void rule_should_return_motive_to_fail_when_is_invalid() {
        var result = cardNotActiveRule.execute(new Account(false, 90), null);

        assertThat(result).isEqualTo("card-not-active");
    }
    
}
