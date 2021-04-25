package br.com.nubank.core.rules;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

public class DoubleTransactionRule implements Rule {

    @Override
    public String execute(Account account, Transaction transaction, AccountManager accountManager) {
        var transactions = accountManager.getLastTransactionsByMerchant(transaction.getMerchant(), transaction.getAmount(), transaction.getTime());

        if (transactions.size() > 0) {
            return "double-transaction";
        }

        return null;
    }
    
}
