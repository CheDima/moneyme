package com.dmitryche.service.validation;

import com.dmitryche.dataservice.DataService;
import com.dmitryche.model.processing.TransactionRequest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ValidationServiceImpl implements ValidationService {

    DataService dataService;

    @Inject
    public ValidationServiceImpl(DataService ds) {
        dataService = ds;
    }

    @Override
    public ValidationResult validateTransaction(TransactionRequest request) {
        List<String> errorMsgs = new ArrayList<>();
        boolean credAccExists = dataService.getAccountByNum(request.getCredAccount()).isPresent();
        boolean debtAccExists = dataService.getAccountByNum(request.getDebtAccount()).isPresent();
        if (!credAccExists) {
            errorMsgs.add("Credit account not found: " + request.getCredAccount());
        }
        if (!debtAccExists) {
            errorMsgs.add("Debit account not found: " + request.getDebtAccount());
        }
        if (request.getCredAccount().equals(request.getDebtAccount())) {
            errorMsgs.add("You cannot transfer money to the same account");
        }
        if (credAccExists && debtAccExists && !hasEnoughMoney(request.getCredAccount(), request.getAmount())) {
            errorMsgs.add("Balance of credit account is less than transaction amount");
        }

        return new ValidationResult(errorMsgs.isEmpty(), String.join("\n", errorMsgs));
    }

    private boolean hasEnoughMoney(String account, Double amount) {
        return dataService.getAccountByNum(account).filter(acc -> acc.getBalance() >= amount).isPresent();
    }
}
