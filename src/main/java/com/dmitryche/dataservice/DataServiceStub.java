package com.dmitryche.dataservice;

import com.dmitryche.model.Account;
import com.dmitryche.model.Transaction;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.model.processing.TransactionResult;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * To be replaced with real DB implementation
 */
public class DataServiceStub implements DataService {

    // I know, we should not do this on prod :) Only for stub purposes!
    private List<Account> accounts = null;
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public TransactionResponse processTransaction(TransactionRequest request) {
        try {
            transactions.add(new Transaction(
                    getAccountByNum(request.getDebtAccount()).orElseThrow(),
                    getAccountByNum(request.getCredAccount()).orElseThrow(),
                    ZonedDateTime.now(),
                    request.getAmount(),
                    request.getMessage()));
            return new TransactionResponse(TransactionResult.SUCCESS,
                    String.format("Money Transferred from %s to %s", request.getCredAccount(), request.getDebtAccount()));

        } catch (Exception ex) { // more specific exceptions should be caught here
            return new TransactionResponse(TransactionResult.ERROR,
                    "Problem occured while transferring the money: " + ex.getMessage());

        }
    }

    @Override
    public Optional<Account> getAccountByNum(String accNumber) {
        return getAccounts().stream().filter(acc -> acc.getNumber().equals(accNumber)).findFirst();
    }

    private List<Account> getAccounts() {
        if (accounts != null) {
            return accounts;
        }

        JAXBContext jaxbContext = null;
        Accounts accs = null;
        try {
            jaxbContext = JAXBContext.newInstance(Accounts.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            accs = (Accounts) jaxbUnmarshaller.unmarshal(new File(getClass().getClassLoader().getResource("accounts.xml").getFile()));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return accs.getAccounts();

    }

    @XmlRootElement(name = "accounts")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class Accounts {
        @XmlElement(name = "account")
        private List<Account> accounts = null;

        List<Account> getAccounts() {
            return accounts;
        }

        public void setAccounts(List<Account> accounts) {
            this.accounts = accounts;
        }
    }

}
