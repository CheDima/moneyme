package com.dmitryche;

import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.model.processing.TransactionResult;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionControllerIT {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() {
        server = Main.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() {
        server.stop();
    }



    @Test
    public void testTransferNegative() {
        Entity<TransactionRequest> postParam = Entity.entity(
                new TransactionRequest("NO_ACCOUNT", "NO_ACCOUNT_1", 666.0, "Just to do it"), MediaType.APPLICATION_JSON_TYPE);
        TransactionResponse response = target.path("transfer")
                .request(MediaType.APPLICATION_JSON).post(postParam)
                .readEntity(TransactionResponse.class);

        assertEquals(TransactionResult.ERROR, response.getResult());
        assertTrue(response.getComment().contains("not found: NO_ACCOUNT"));
    }

    @Test
    public void testTransferPositive() {
        Entity<TransactionRequest> postParam = Entity.entity(
                new TransactionRequest("TEST_ACCOUNT_1", "TEST_ACCOUNT_2", 5.0, "Just to do it"), MediaType.APPLICATION_JSON_TYPE);
        TransactionResponse response = target.path("transfer")
                .request(MediaType.APPLICATION_JSON).post(postParam)
                .readEntity(TransactionResponse.class);

        assertEquals(TransactionResult.SUCCESS, response.getResult());
        assertTrue(response.getComment().contains("Money transferred"));
    }

}
