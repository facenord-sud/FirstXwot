/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import diuf.unifr.ch.first.xwot.rxtx.RxtxConnection;
import diuf.unifr.ch.first.xwot.rxtx.test.ConnectionSimulator;
import diuf.unifr.ch.first.xwot.rxtx.test.HardwareSpeaker;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author leo
 */
public class TestHelpers {

    private Client client;
    private WebResource webResource;
    private static final String BASE_URI = "http://0.0.0.0:9090/FirstXwotServer/resources/";
    

    /**
     * Utility method which creates a new jersey-client.
     *
     * @return
     */
    protected WebResource getClient(String uri) {

        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("door" + uri);
        return webResource;

    }

    /**
     * Utility method for checking if a given string represents in fact a valid
     * XML Tasks element.
     *
     * @param response The string to check.
     */
    protected void assertXmlResponse(String response) {
        assertNotNull("No text returned!", response);

        assertResponseContains(response, "<?xml");
        assertResponseContains(response, "<door");
        assertResponseContains(response, "</door>");
    }

    /**
     * Helper Method. Check a string contains some given characters.
     *
     * @param response the string to verify.
     * @param text pattern to search for.
     */
    protected void assertResponseContains(String response, String text) {
        assertTrue("Response should contain " + text + " but was: " + response, response.contains(text));
    }
}
