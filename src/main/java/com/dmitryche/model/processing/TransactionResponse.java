package com.dmitryche.model.processing;

public class TransactionResponse {
    private TransactionResult result;
    private String comment;

    public TransactionResponse() {
    }

    public TransactionResponse(TransactionResult result, String comment) {
        this.result = result;
        this.comment = comment;
    }

    public TransactionResult getResult() {
        return result;
    }

    public String getComment() {
        return comment;
    }

    public void setResult(TransactionResult result) {
        this.result = result;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
