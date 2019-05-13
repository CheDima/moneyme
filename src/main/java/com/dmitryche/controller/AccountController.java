package com.dmitryche.controller;

import com.dmitryche.model.Account;
import com.dmitryche.service.transaction.AccountService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("account")
public class AccountController {

    @Inject
    private AccountService accountService;

    @GET
    @Path("/bynumber/{accnumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccountByNum(@PathParam("accnumber") String accNumber) {
        return accountService.getAccountByNumber(accNumber);
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getAllAccounts(@PathParam("accnumber") String accNumber) {
        return accountService.getAccounts();
    }
}
