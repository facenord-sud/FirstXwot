/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.resources;

import com.sun.jersey.api.client.WebResource;
import diuf.unifr.ch.first.xwot.jaxb.Lock;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.TestHelpers;

/**
 *
 * @author leo
 */
public class LockContextResourceTest extends TestHelpers{
    
    public LockContextResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getLockContextResourceXML method, of class LockContextResource.
     */
    @Test
    public void testGetLockContextResourceXML() {
        WebResource ws = getClient("/lock");
        String result = ws.accept(MediaType.APPLICATION_XML).get(String.class);
        assertNotNull("response should not be null", result);
        assertXmlResponse(result);
    }

    /**
     * Test of putLockContextResourceXML method, of class LockContextResource.
     */
    @Test
    public void testPutLockContextResourceXML() {
        assertTrue(true);
    }
    
    /**
     * Utility method for checking if a given string represents in fact a valid XML Tasks element.
     * @param response The string to check.
     */
    @Override
    protected void assertXmlResponse(String response) {
        assertNotNull("No text returned!", response);

        assertResponseContains(response, "<?xml");
        assertResponseContains(response, "<lock");
        assertResponseContains(response, "</lock>");
    }
}
