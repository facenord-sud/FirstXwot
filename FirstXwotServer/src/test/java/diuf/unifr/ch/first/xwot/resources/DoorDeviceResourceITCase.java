/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.resources;

import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
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
public class DoorDeviceResourceITCase extends TestHelpers{
    
    
    
    public DoorDeviceResourceITCase() {
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
     * Test of getDoorDeviceResourceXML method, of class DoorDeviceResource.
     */
    @Test
    public void testGetDoorDeviceResourceXML() {
        WebResource ws = getClient("/");
        String result = ws.accept(MediaType.APPLICATION_XML).get(String.class);
        assertNotNull("The answer should not be empty", result);
        assertXmlResponse(result);
    }
}
