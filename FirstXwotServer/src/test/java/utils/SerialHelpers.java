/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

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
import org.junit.Before;
import org.junit.BeforeClass;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leo
 */
public class SerialHelpers extends TestHelpers {

    protected static ConnectionSimulator simulator;
    protected static HardwareSpeaker hardware;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SerialHelpers.class);

    @BeforeClass
    public static void beforeClass() {
        simulator = new ConnectionSimulator();
        try {
            RxtxConnection.getInstance();
        } catch (PortInUseException ex) {
            Logger.getLogger(SerialHelpers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(SerialHelpers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SerialHelpers.class.getName()).log(Level.SEVERE, null, ex);
        }
        hardware = simulator.getHardwareSpeaker();
    }

    @AfterClass
    public static void afterClass() {
        RxtxConnection.close();
        simulator.stop();
    }
}
