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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

    @Mock
    DataService dataService = new DataServiceStub();

    ValidationService validationService;

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
    }

    @Test
    public void testValidateTooBigAmount() {
        TransactionRequest notEnoughMoneyRequest = new TransactionRequest("TEST_ACC1", "TEST_ACC2", 20000.0, "Happy birthday");
        ValidationResult result3 = validationService.validateTransaction(notEnoughMoneyRequest);
        assertFalse(result3.isValid());
    }
}
