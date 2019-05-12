package com.dmitryche.controller;

import com.dmitryche.model.Account;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.service.transaction.TransactionService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("")
public class TransactionController {

    @Inject
    TransactionService transactionService;

    @GET
    @Path("account/number/{accnumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccountByNum(@PathParam("accnumber") String accNumber) {
        return transactionService.getAccountByNumber(accNumber);
    }

    @Path("/transfer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionResponse transfer(TransactionRequest request) {
        TransactionResponse resp = transactionService.transfer(request);
        return resp;
    }
}
