package com.dmitryche.dataservice;

import com.dmitryche.model.Account;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;

import java.util.Optional;

public interface DataService {
    TransactionResponse processTransaction(TransactionRequest request);

    Optional<Account> getAccountByNum(String accNumber);
}
