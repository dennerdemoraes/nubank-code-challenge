package br.com.nubank.core.entity;

import javax.validation.Validation;

public class TransactionEvent extends Event<Transaction> {

    private Transaction transaction;

    public TransactionEvent() {
        setType(EventType.TRANSACTION);
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Transaction getPayload() {
        return transaction;
    }

    @Override
    public boolean isValid() {
        var validatorFactory = Validation.buildDefaultValidatorFactory();
        var validator = validatorFactory.getValidator();
        var violations = validator.validate(transaction);

        return violations.isEmpty();
    }
}
