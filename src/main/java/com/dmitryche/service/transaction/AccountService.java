package com.dmitryche.service.transaction;

import com.dmitryche.model.Account;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;

import java.util.List;

public interface AccountService {
    TransactionResponse transfer(TransactionRequest request);

    Account getAccountByNumber(String number);

    List<Account> getAccounts();
}
