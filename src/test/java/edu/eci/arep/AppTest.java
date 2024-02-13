package edu.eci.arep;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;

import edu.eci.arep.service.HttpMovieConnection;

/**
 * Unit test for simple HttpServer.
 */
public class AppTest {

    HttpServer server;

    @Before
    public void setUp() {
        server = HttpServer.getInstance();
        server.setStaticFileLocation("target/classes/public");
    }

    @Test
    public void shouldResponseHtmlFiles() {
        byte[] response = null;
        try {
            URI uri = new URI(server.baseStaticFileURI + "/index.html");
            response = server.httpResponseBody(server.TEXT_HTML, uri);
        } catch (IOException | URISyntaxException e ) {
            e.printStackTrace();
        }
        assertNotNull(response);
    }

}