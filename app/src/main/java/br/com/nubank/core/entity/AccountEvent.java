package br.com.nubank.core.entity;

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
}
