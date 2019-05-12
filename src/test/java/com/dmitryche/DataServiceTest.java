package com.dmitryche;

import com.dmitryche.dataservice.DataService;
import com.dmitryche.dataservice.DataServiceStub;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DataServiceTest {

    DataService dataService = new DataServiceStub();

    @Test
    public void testGetAccountByNum() {
        assertTrue(dataService.getAccountByNum("111222333").isPresent());
        assertFalse(dataService.getAccountByNum("No Such Acc").isPresent());
    }
}
