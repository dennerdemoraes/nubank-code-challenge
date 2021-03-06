package br.com.nubank.core.rules;

import com.google.inject.Inject;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

public class HighFrequencySmallIntervalRule implements Rule {

    private final AccountManager accountManager;

    @Inject
    public HighFrequencySmallIntervalRule(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public String execute(Account account, Transaction transaction) {
        var transactions = accountManager.getLastTransactions(transaction.getTime());

        if (transactions.size() == 3) {
            return "high-frequency-small-interval";
        }

        return null;
    }
    
}
