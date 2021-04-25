package br.com.nubank.core.entity;

import java.util.ArrayList;
import java.util.List;

public class AccountState {
    private Account account;
    private List<String> violations = new ArrayList<>();

    public AccountState() {}

    public AccountState(Account account, List<String> violations) {
        this.account = account;
        this.violations = violations;
    }
    
    public Account getAccount() {
        return account;
    }
    public List<String> getViolations() {
        return violations;
    }
    public void setViolations(List<String> violations) {
        this.violations = violations;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
}
