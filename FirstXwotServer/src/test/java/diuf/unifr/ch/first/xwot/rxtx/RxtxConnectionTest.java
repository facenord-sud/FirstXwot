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
import org.slf4j.LoggerFactory;
import utils.SerialHelpers;
import utils.TestHelpers;

/**
 *
 * @author leo
 */
public class RxtxConnectionTest extends SerialHelpers {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RxtxConnectionTest.class);

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
            logger.error("not working");
        } catch (UnsupportedCommOperationException ex) {
            logger.error("not working");
        } catch (IOException ex) {
            logger.error("not working");
        }
        assertNotNull("We should see this message and should not be null", conn.getSerialPort());
    }

    /**
     * Test of getLine method, of class RxtxConnection.
     */
    @Test
    public void testGetLine() {
        try {
            RxtxConnection con = RxtxConnection.getInstance();
            sayHello("Hello world!", con);
            sayHello("salut", con);
            sayHello("hola", con);
            sayHello("hallo", con);
            sayHello("ciao", con);
            sayHello("grüesser", con);
        } catch (PortInUseException ex) {
            Logger.getLogger(RxtxConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(RxtxConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RxtxConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void sayHello(String hello, RxtxConnection con) {
        hardware.speak(hello);
        logger.debug("setting up string to arduino");
        assertEquals("line should be equals to '" + hello + "'", hello, con.getLine());
    }
}
