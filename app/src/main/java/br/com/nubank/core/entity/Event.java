package br.com.nubank.core.entity;

public class Event {
    private EventType type;
    private Account account;
    private Transaction transaction;

    public EventType getType() {
        return type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
        this.type = EventType.ACCOUNT;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
        this.type = EventType.TRANSACTION;
    }
}
