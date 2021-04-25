package br.com.nubank.core.rules;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

public class HighFrequencySmallIntervalRule implements Rule {

    @Override
    public String execute(Account account, Transaction transaction, AccountManager accountManager) {
        var transactions = accountManager.getLastTransactions(transaction.getTime());

        if (transactions.size() == 3) {
            return "high-frequency-small-interval";
        }

        return null;
    }
    
}
