package com.dmitryche;

import com.dmitryche.dataservice.DataService;
import com.dmitryche.dataservice.DataServiceStub;
import com.dmitryche.model.Account;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.model.processing.TransactionResult;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Not a unit test but IT as it uses DB
 */
public class DataServiceIT {

    private DataService dataService = new DataServiceStub();

    @Test
    public void testGetAccountByNum() {
        assertTrue(dataService.getAccountByNum("TEST_ACCOUNT_1").isPresent());
        assertFalse(dataService.getAccountByNum("No Such Acc").isPresent());
    }

    @Test
    public void testProcessTransaction() {
        Double amount = 12.0;
        Account accFrom = dataService.getAccountByNum("TEST_ACCOUNT_1").orElseThrow();
        Account accTo = dataService.getAccountByNum("TEST_ACCOUNT_2").orElseThrow();
        Double credBalance = accFrom.getBalance();
        Double debtBalance = accTo.getBalance();

        TransactionRequest request = new TransactionRequest(accFrom.getNumber(), accTo.getNumber(), amount, "Test text");
        TransactionResponse response = dataService.processTransaction(request);

        assertTrue(dataService.getAllTransactions().stream()
                .anyMatch(t ->
                        t.getCredAccount().getNumber().equals(accFrom.getNumber())
                                && t.getCredAccount().getNumber().equals(accFrom.getNumber())
                                && t.getAmount().equals(amount)
                                && "Test text".equals(t.getMessage())
                ));

        assertEquals(credBalance - amount, accFrom.getBalance(), 0);
        assertEquals(debtBalance + amount, accTo.getBalance(), 0);
        assertSame(response.getResult(), TransactionResult.SUCCESS);

    }
}
