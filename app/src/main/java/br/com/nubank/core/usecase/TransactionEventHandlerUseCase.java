package br.com.nubank.core.usecase;

import java.util.ArrayList;
import java.util.Objects;

import com.google.inject.Inject;

import br.com.nubank.core.AccountManager;
import br.com.nubank.core.entity.AccountState;
import br.com.nubank.core.entity.Event;
import br.com.nubank.core.entity.Transaction;
import br.com.nubank.core.rules.AccountNotInitializedRule;
import br.com.nubank.core.rules.CardNotActiveRule;
import br.com.nubank.core.rules.DoubleTransactionRule;
import br.com.nubank.core.rules.HighFrequencySmallIntervalRule;
import br.com.nubank.core.rules.InsuficientLimitRule;
import br.com.nubank.core.rules.Rule;

public class TransactionEventHandlerUseCase implements EventHandlerUseCase {

    private final AccountManager accountManager;

    @Inject
    public TransactionEventHandlerUseCase(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    @Override
    public AccountState execute(Event<?> event) {
        var transaction = (Transaction) event.getPayload();
        var account = accountManager.getAccount();

        var rules = new ArrayList<Rule>();
        rules.add(new AccountNotInitializedRule());
        rules.add(new CardNotActiveRule());
        rules.add(new InsuficientLimitRule());
        rules.add(new HighFrequencySmallIntervalRule());
        rules.add(new DoubleTransactionRule());
        
        var violations = new ArrayList<String>();
        for (Rule rule : rules) {
            var error = rule.execute(account, transaction, accountManager);

            if (Objects.nonNull(error))
                violations.add(error);
        }

        if (violations.size() > 0) {
            return new AccountState(account, violations);
        }

        accountManager.addTransaction(transaction);
        return new AccountState(account, new ArrayList<>());
    }
}
