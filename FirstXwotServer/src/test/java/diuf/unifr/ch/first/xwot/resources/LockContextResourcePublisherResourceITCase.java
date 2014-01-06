/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.resources;

import com.sun.jersey.api.client.WebResource;
import diuf.unifr.ch.first.xwot.jaxb.Client;
import javax.ws.rs.core.MediaType;
import static org.junit.Assert.*;
import org.junit.Test;
import utils.TestHelpers;

/**
 *
 * @author leo
 */
public class LockContextResourcePublisherResourceITCase  extends TestHelpers{

   
    @Test
    public void testGetClients() {
        String uri = registerClient();
        WebResource ws = getClient("/lock/pub");
        String result = ws.accept(MediaType.APPLICATION_XML).get(String.class);
        assertResponseContains(result, "<uri>"+uri);
    }
    
    @Test
    public void testPutClient() {
        registerClient();
    }
    
    @Test
    public void testGetClient() {
        String uri = registerClient();
        WebResource ws = getClient("/lock/pub/"+uri);
        String result = ws.accept(MediaType.APPLICATION_XML).get(String.class);
        assertResponseContains(result, "<uri>"+uri);
    }
    
    @Test
    public void testDeleteClient() {
        registerClient();
        String uri = registerClient();
        WebResource ws = getClient("/lock/pub/"+uri);
        String result = ws.accept(MediaType.APPLICATION_XML).delete(String.class);
        assertResponseContains(result, "<uri>"+uri);
        String empty = ws.accept(MediaType.APPLICATION_XML).get(String.class);
        assertResponseContains(empty, "");
    }
    
    private String registerClient() {
        String uri = "http://localhost:4556/";
        WebResource ws = getClient("/lock/pub/"+uri);
        Client clientUri = new Client();
        
        String result = ws.accept(MediaType.APPLICATION_XML).entity(clientUri).put(String.class);
        assertResponseContains(result, "<uri>"+uri);
        return uri;
    }
}
