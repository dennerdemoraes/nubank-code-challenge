package br.com.nubank.core.rules;

import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

public interface Rule {
    String execute(Account account, Transaction transaction);
}
