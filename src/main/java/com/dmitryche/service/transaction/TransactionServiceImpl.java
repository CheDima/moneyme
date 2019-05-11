package com.dmitryche.service.transaction;

import com.dmitryche.dataservice.DataService;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.service.validation.ValidationResult;
import com.dmitryche.service.validation.ValidationService;

import javax.inject.Inject;

public class TransactionServiceImpl implements TransactionService {
    @Inject
    ValidationService validationService;

    @Inject
    DataService dataService;

    @Override
    public TransactionResponse transfer(TransactionRequest request) {
        ValidationResult validationResult = validationService.validateTransaction(request);
        if (!validationResult.isValid()) {
            return validationResult.buildResponse();
        }
        return dataService.processTransaction(request);
    }
}
