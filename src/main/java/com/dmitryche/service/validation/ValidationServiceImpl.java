package com.dmitryche.service.validation;

import com.dmitryche.model.processing.TransactionRequest;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public ValidationResult validateTransaction(TransactionRequest request) {
        return new ValidationResult(true, "Looks good");
    }
}
