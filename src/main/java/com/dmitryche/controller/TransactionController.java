package com.dmitryche.controller;

import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.service.transaction.TransactionService;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResult;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("")
public class TransactionController {

    @Inject
    TransactionService transactionService;

    @Path("/transfer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionResponse transfer(TransactionRequest request) {
        TransactionResponse resp = transactionService.transfer(request);
        return resp;
    }
}
