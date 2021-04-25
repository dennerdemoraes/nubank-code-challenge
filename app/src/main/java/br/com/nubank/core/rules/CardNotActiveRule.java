package br.com.nubank.core.rules;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

public class CardNotActiveRule implements Rule {

    @Override
    public String execute(Account account, Transaction transaction, AccountManager accountManager) {
        if (!account.getActiveCard()) {
            return "card-not-active";
        }

        return null;
    }
    
}
