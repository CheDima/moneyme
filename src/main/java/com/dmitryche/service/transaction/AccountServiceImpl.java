package com.dmitryche.service.transaction;

import com.dmitryche.dataservice.DataService;
import com.dmitryche.model.Account;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.service.validation.ValidationResult;
import com.dmitryche.service.validation.ValidationService;

import javax.inject.Inject;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private ValidationService validationService;
    private DataService dataService;

    @Inject
    public AccountServiceImpl(ValidationService validationService, DataService dataService) {
        this.validationService = validationService;
        this.dataService = dataService;
    }

    @Override
    public TransactionResponse transfer(TransactionRequest request) {
        ValidationResult validationResult = validationService.validateTransaction(request);
        if (!validationResult.isValid()) {
            return validationResult.buildResponse();
        }
        return dataService.processTransaction(request);
    }

    @Override
    public Account getAccountByNumber(String number) {
        return dataService.getAccountByNum(number).orElse(null);
    }

    @Override
    public List<Account> getAccounts() {
        return dataService.getAllAccounts();
    }
}
