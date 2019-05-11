package com.dmitryche;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dmitryche.model.processing.TransactionRequest;
import com.dmitryche.model.processing.TransactionResponse;
import com.dmitryche.model.processing.TransactionResult;
import org.glassfish.grizzly.TransformationResult;
import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionControllerTest {

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
    public void testTransfer() {
        Entity<TransactionRequest> postParam = Entity.entity(
                new TransactionRequest("SENDER_ACCOUNT", "RECEIVER_ACCOUNT", 666.0), MediaType.APPLICATION_JSON_TYPE);
        TransactionResponse response = target.path("transfer")
                .request(MediaType.APPLICATION_JSON).post(postParam)
                .readEntity(TransactionResponse.class);

        assertEquals(TransactionResult.SUCCESS, response.getResult());
        assertTrue(response.getComment().contains("from SENDER_ACCOUNT"));
        assertTrue(response.getComment().contains("to RECEIVER_ACCOUNT"));
    }

}
