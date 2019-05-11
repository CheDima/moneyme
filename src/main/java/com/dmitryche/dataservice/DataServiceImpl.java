package com.dmitryche.dataservice;

import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.model.processing.TransactionResult;

public class DataServiceImpl implements DataService {
    @Override
    public TransactionResponse processTransaction(TransactionRequest request) {
        return new TransactionResponse(TransactionResult.SUCCESS,
                String.format("Money Transferred from %s to %s", request.getCredAccount(), request.getDebtAccount()));
    }
}
