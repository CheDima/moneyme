package com.dmitryche;

import com.dmitryche.dataservice.DataService;
import com.dmitryche.dataservice.DataServiceStub;
import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.service.transaction.AccountService;
import com.dmitryche.service.transaction.AccountServiceImpl;
import com.dmitryche.service.validation.ValidationResult;
import com.dmitryche.service.validation.ValidationService;
import com.dmitryche.service.validation.ValidationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * It is closely related to DataService so mainly testing happens in Integration Test
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Spy
    private DataService dataService = new DataServiceStub();

    private AccountService accountService;
    private ValidationService validationService;

    private TransactionRequest dummyRequest = new TransactionRequest("acc1", "acc2", 100.0, "comment");
    private ValidationResult validResult = new ValidationResult(true, "Ok");

    @Before
    public void setup() {
        validationService = mock(ValidationServiceImpl.class);
        accountService = new AccountServiceImpl(validationService, dataService);
    }

    @Test
    public void testProcessMethodCalled() {
        when(validationService.validateTransaction(dummyRequest)).thenReturn(validResult);
        accountService.transfer(dummyRequest);
        verify(dataService).processTransaction(dummyRequest);
    }
}
