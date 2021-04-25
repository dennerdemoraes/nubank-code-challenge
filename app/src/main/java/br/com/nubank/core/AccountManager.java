package br.com.nubank.core;

import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.nubank.core.entity.Account;
import br.com.nubank.core.entity.Transaction;

public class AccountManager {
    public Account account;
    public List<Transaction> transactions = new ArrayList<>();

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        account.withdrawAmount(transaction.getAmount());
    }

    public List<Transaction> getLastTransactions(ZonedDateTime time) {
        return transactions.stream()
            .filter((transaction) -> 
                transaction.getTime().isBefore(ChronoZonedDateTime.from(time)) 
                && transaction.getTime().isAfter(ChronoZonedDateTime.from(time.minusMinutes(2)))
            )
            .collect(Collectors.toList());
    }

    public List<Transaction> getLastTransactionsByMerchant(String merchant, Integer amount, ZonedDateTime time) {
        return transactions.stream()
            .filter((transaction) -> 
                transaction.getTime().isBefore(ChronoZonedDateTime.from(time)) 
                && transaction.getTime().isAfter(ChronoZonedDateTime.from(time.minusMinutes(2)))
            )
            .filter((transaction) -> transaction.getMerchant() == merchant && transaction.getAmount() == amount)
            .collect(Collectors.toList());
    }
}
