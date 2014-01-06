/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.resources;

import com.sun.jersey.api.client.WebResource;
import diuf.unifr.ch.first.xwot.jaxb.Client;
import javax.ws.rs.core.MediaType;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import utils.TestHelpers;

/**
 *
 * @author leo
 */
public class LockContextResourcePublisherResourceITCase  extends TestHelpers{

   
    @Test
    public void testGetLockContextResourcePublisherResourceXML() {
        WebResource ws = getClient("/lock/pub");
        String result = ws.accept(MediaType.APPLICATION_XML).get(String.class);
        assertNotNull("response should not be null", result);
    }
    
    @Test
    public void testPutLockContextResourcePublisherResourceXML() {
        WebResource ws = getClient("/lock/pub/http://localhost:4556/");
        Client clientUri = new Client();
        
        String result = ws.accept(MediaType.APPLICATION_XML).entity(clientUri).put(String.class);
        assertNotNull("response should not be null", result);
    }
}
