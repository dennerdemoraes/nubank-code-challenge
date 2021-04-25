package br.com.nubank.core.rules;


import java.util.Objects;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

public class AccountNotInitializedRule implements Rule {

    @Override
    public String execute(Account account, Transaction transaction, AccountManager accountManager) {
        if (Objects.isNull(account)) {
            return "account-not-initialized";
        }

        return null;
    }
    
}
