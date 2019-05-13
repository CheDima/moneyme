package com.dmitryche.controller;

import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.service.transaction.AccountService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("")
public class TransactionController {

    @Inject
    private AccountService accountService;

    @Path("/transfer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionResponse transfer(TransactionRequest request) {
        TransactionResponse resp = accountService.transfer(request);
        return resp;
    }
}
