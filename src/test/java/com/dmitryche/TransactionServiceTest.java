package com.dmitryche;

import com.dmitryche.dataservice.DataService;
import com.dmitryche.dataservice.DataServiceStub;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.service.transaction.TransactionService;
import com.dmitryche.service.transaction.TransactionServiceImpl;
import com.dmitryche.service.validation.ValidationResult;
import com.dmitryche.service.validation.ValidationService;
import com.dmitryche.service.validation.ValidationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Spy
    DataService dataService = new DataServiceStub();

    TransactionService transactionService;
    ValidationService validationService;

    TransactionRequest dummyRequest = new TransactionRequest("", "", 100.0, "");
    ValidationResult validResult = new ValidationResult(true, "Ok");

    @Before
    public void setup() {
        validationService = mock(ValidationServiceImpl.class);
        transactionService = new TransactionServiceImpl(validationService, dataService);
    }

    @Test
    public void testProcessMethodCalled() {
        when(validationService.validateTransaction(dummyRequest)).thenReturn(validResult);
        transactionService.transfer(dummyRequest);
        verify(dataService).processTransaction(dummyRequest);
    }
}
