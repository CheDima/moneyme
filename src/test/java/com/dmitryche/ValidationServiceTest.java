package com.dmitryche;

import com.dmitryche.dataservice.DataService;
import com.dmitryche.dataservice.DataServiceStub;
import com.dmitryche.model.Account;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.service.validation.ValidationResult;
import com.dmitryche.service.validation.ValidationService;
import com.dmitryche.service.validation.ValidationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

    @Mock
    private DataService dataService = new DataServiceStub();

    private ValidationService validationService;

    @Before
    public void setup() {
        validationService = new ValidationServiceImpl(dataService);
        when(dataService.getAccountByNum("TEST_ACC1")).thenReturn(Optional.of(new Account("TEST_ACC1", 50.0)));
        when(dataService.getAccountByNum("TEST_ACC2")).thenReturn(Optional.of(new Account("TEST_ACC2", 60.0)));
        when(dataService.getAccountByNum("NO_ACC")).thenReturn(Optional.empty());
    }

    @Test
    public void testValidateValidTransaction() {
        TransactionRequest validRequest = new TransactionRequest("TEST_ACC1", "TEST_ACC2", 20.0, "Happy birthday");
        ValidationResult result1 = validationService.validateTransaction(validRequest);
        assertTrue(result1.isValid());
    }

    @Test
    public void testValidateWrongAccout() {
        TransactionRequest noAccRequest = new TransactionRequest("NO_ACC", "TEST_ACC2", 20.0, "Happy birthday");
        ValidationResult result2 = validationService.validateTransaction(noAccRequest);
        assertFalse(result2.isValid());
        assertTrue(result2.getDescription().contains("account not found"));
    }

    @Test
    public void testValidateTooBigAmount() {
        TransactionRequest notEnoughMoneyRequest = new TransactionRequest("TEST_ACC1", "TEST_ACC2", 20000.0, "Happy birthday");
        ValidationResult result3 = validationService.validateTransaction(notEnoughMoneyRequest);
        assertFalse(result3.isValid());
        assertEquals(result3.getDescription(), "Balance of credit account is less than transaction amount");
    }

    @Test
    public void testValidateTransferBetweenSameAccount() {
        TransactionRequest validRequest = new TransactionRequest("TEST_ACC1", "TEST_ACC1", 20.0, "Happy birthday");
        ValidationResult result4 = validationService.validateTransaction(validRequest);
        assertFalse(result4.isValid());
        assertEquals(result4.getDescription(), "You cannot transfer money to the same account");
    }

}
