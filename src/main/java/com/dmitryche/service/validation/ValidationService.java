package com.dmitryche.service.validation;

import com.dmitryche.model.processing.TransactionRequest;

public interface ValidationService {
    ValidationResult validateTransaction(TransactionRequest request);
}
