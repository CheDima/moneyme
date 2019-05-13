package com.dmitryche;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class AccountControllerIT {

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
    public void testGetAllAccounts() {
        Response response = target.path("account/all").request(MediaType.APPLICATION_JSON).get();
        assertNotNull(response);
        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
        String content = response.readEntity(String.class);
        assertTrue(content.contains("\"number\":\"TEST_ACCOUNT_1\",\"balance\":1000.0"));
        assertTrue(content.contains("\"number\":\"TEST_ACCOUNT_2\",\"balance\":-1000.0"));
        assertTrue(content.contains("\"number\":\"1234567890\",\"balance\":0.0"));
    }

    @Test
    public void testGetAccountByNum() {
        Response response = target.path("account/bynumber/TEST_ACCOUNT_1").request(MediaType.APPLICATION_JSON).get();
        assertNotNull(response);
        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
        String content = response.readEntity(String.class);
        assertTrue(content.contains("\"number\":\"TEST_ACCOUNT_1\",\"balance\":1000.0"));
        assertFalse(content.contains("\"number\":\"TEST_ACCOUNT_2\",\"balance\":-1000.0"));
    }
}
