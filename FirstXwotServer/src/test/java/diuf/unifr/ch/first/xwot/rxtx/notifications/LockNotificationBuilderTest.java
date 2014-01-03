/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.notifications;

import diuf.unifr.ch.first.xwot.notifications.LockNotificationBuilder;
import diuf.unifr.ch.first.xwot.jaxb.Client;
import diuf.unifr.ch.first.xwot.rxtx.RxtxConnection;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.entity.StringEntity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author leo
 */
public class LockNotificationBuilderTest {
   
    private RxtxConnection rxtx;
    
    public LockNotificationBuilderTest() {
        try {
            rxtx = RxtxConnection.getInstance();
        } catch (PortInUseException ex) {
            Logger.getLogger(LockNotificationBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(LockNotificationBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LockNotificationBuilderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Test of hasNotification method, of class LockNotificationBuilder.
     */
    @Test
    public void testHasNotification() {
        
    }

    /**
     * Test of jaxbToStringEntity method, of class LockNotificationBuilder.
     */
    @Test
    public void testJaxbToStringEntity() {
        System.out.println("jaxbToStringEntity");
        rxtx.setLine("{\"9\":{\"speed\":60},\"10\":"
                    + "{\"speed\":93}, \"14\":{\"oldPosition\":511, \"position\""
                    + ":1023}}, \"15\":{\"oldPosition\":511, \"position\":1023}");
        System.out.println(rxtx.getLine());
        Client client = new Client();
        LockNotificationBuilder instance = new LockNotificationBuilder();
        StringEntity result = instance.jaxbToStringEntity(client);
        assertNotNull(result);
    }
    
}
