/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import diuf.unifr.ch.first.xwot.rxtx.test.ConnectionSimulator;
import diuf.unifr.ch.first.xwot.rxtx.test.HardwareSpeaker;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author leo
 */
public class SerialHelpers extends TestHelpers {

    protected static ConnectionSimulator simulator;
    protected static HardwareSpeaker hardware;

    @Before
    public void setUpTest() {
        simulator = new ConnectionSimulator();
        hardware = simulator.getHardwareSpeaker();

    }

    @After
    public void tearDownTest() {
        simulator.stop();
    }

}
