package com.dmitryche.service.transaction;

import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;

public interface TransactionService {
    TransactionResponse transfer(TransactionRequest request);
}