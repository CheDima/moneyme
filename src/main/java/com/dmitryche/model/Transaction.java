package com.dmitryche.model;

import java.time.ZonedDateTime;

public class Transaction {
    private Account debtAccount;
    private Account credAccount;
    private ZonedDateTime transactionTime;
    private Double amount;
    private String message;
    //TODO: Add currency

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(ZonedDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Account getDebtAccount() {
        return debtAccount;
    }

    public void setDebtAccount(Account debtAccount) {
        this.debtAccount = debtAccount;
    }

    public Account getCredAccount() {
        return credAccount;
    }

    public void setCredAccount(Account credAccount) {
        this.credAccount = credAccount;
    }
}
