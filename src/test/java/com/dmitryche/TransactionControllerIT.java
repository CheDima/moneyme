package com.dmitryche;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

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
        String param = "{\"credAccount\":\"WRONG_ACCOUNT\", \"debtAccount\":\"TEST_ACCOUNT_2\", \"amount\":\"5.0\", \"message\":\"Just to do it\"}";
        Response response = target.path("transfer").request().post(Entity.json(param));
        assertTrue(response.readEntity(String.class).contains("not found: WRONG_ACCOUNT"));
    }

    @Test
    public void testTransferPositive() {
        String param = "{\"credAccount\":\"TEST_ACCOUNT_1\", \"debtAccount\":\"TEST_ACCOUNT_2\", \"amount\":\"5.0\", \"message\":\"Just to do it\"}";
        Response response = target.path("transfer").request().post(Entity.json(param));
        assertTrue(response.readEntity(String.class).contains("Money transferred"));
    }

}
