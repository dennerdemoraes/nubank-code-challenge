package br.com.nubank.core.entity;

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
}
