package com.dmitryche.dataservice;

import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;

public interface DataService {
    TransactionResponse processTransaction(TransactionRequest request);
}
