package com.dmitryche.service.validation;

import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.model.processing.TransactionResult;

public class ValidationResult {
    private final boolean isValid;
    private final String description;

    public ValidationResult(boolean isValid, String description) {
        this.isValid = isValid;
        this.description = description;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getDescription() {
        return description;
    }

    public TransactionResponse buildResponse() {
        if (isValid) {
            return new TransactionResponse(TransactionResult.SUCCESS, description);
        } else {
            return new TransactionResponse(TransactionResult.ERROR, description);
        }
    }
}
