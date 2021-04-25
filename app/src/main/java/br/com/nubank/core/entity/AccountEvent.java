package br.com.nubank.core.entity;

import javax.validation.Validation;

public class AccountEvent extends Event<Account> {

    private Account account;

    public AccountEvent() {
        setType(EventType.ACCOUNT);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public Account getPayload() {
        return account;
    }

    @Override
    public boolean isValid() {
        var validatorFactory = Validation.buildDefaultValidatorFactory();
        var validator = validatorFactory.getValidator();
        var violations = validator.validate(account);

        return violations.isEmpty();
    }
}
