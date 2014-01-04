/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx;

import diuf.unifr.ch.first.xwot.rxtx.test.ConnectionSimulator;
import diuf.unifr.ch.first.xwot.rxtx.test.HardwareSpeaker;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.TestHelpers;

/**
 *
 * @author leo
 */
public class RxtxConnectionTest extends TestHelpers{


    public RxtxConnectionTest() {
        
    }

    /**
     * Test of getInstance method, of class RxtxConnection.
     */
    @Test
    public void testGetInstance() {
        RxtxConnection conn = null;
        try {
            conn = RxtxConnection.getInstance();
        } catch (PortInUseException ex) {
            System.out.println("not working");
        } catch (UnsupportedCommOperationException ex) {
            System.out.println("not working");
        } catch (IOException ex) {
            System.out.println("not working");
        }
        assertNotNull("We should see this message and should not be null", conn.getSerialPort());
    }

    /**
     * Test of getLine method, of class RxtxConnection.
     */
    @Test
    public void testGetLine() {
        try {
            
            String hello = "hello world";
            RxtxConnection con = RxtxConnection.getInstance();
            hardware.speak(hello);
            assertEquals("line should be equals to '" + hello + "'", con.getLine(), hello);
        } catch (PortInUseException ex) {
            Logger.getLogger(RxtxConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(RxtxConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RxtxConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
