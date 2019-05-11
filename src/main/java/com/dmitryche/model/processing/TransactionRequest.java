package com.dmitryche.model.processing;

public class TransactionRequest {
    private String debtAccount;
    private String credAccount;
    private Double amount;

    public TransactionRequest() {
    }

    public TransactionRequest(String credAccount, String debtAccount,  Double amount) {
        this.debtAccount = debtAccount;
        this.credAccount = credAccount;
        this.amount = amount;
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
