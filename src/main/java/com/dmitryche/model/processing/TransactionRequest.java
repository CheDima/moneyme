package com.dmitryche.model.processing;

public class TransactionRequest {
    private String debtAccount;
    private String credAccount;
    private Double amount;
    private String message;

    public TransactionRequest() {
    }

    public TransactionRequest(String debtAccount, String credAccount, Double amount, String message) {
        this.debtAccount = debtAccount;
        this.credAccount = credAccount;
        this.amount = amount;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDebtAccount(String debtAccount) {
        this.debtAccount = debtAccount;
    }

    public void setCredAccount(String credAccount) {
        this.credAccount = credAccount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDebtAccount() {
        return debtAccount;
    }

    public String getCredAccount() {
        return credAccount;
    }

    public Double getAmount() {
        return amount;
    }
}
