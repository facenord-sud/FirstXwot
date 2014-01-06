/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import diuf.unifr.ch.first.xwot.components.ArduinoComponents;
import diuf.unifr.ch.first.xwot.components.LinearPotentiometer;
import diuf.unifr.ch.first.xwot.rxtx.test.AbstractJaxbFactory;
import diuf.unifr.ch.first.xwot.rxtx.test.HardwareSpeaker;

/**
 *
 * @author leo
 */
public class JaxbTestFactory extends AbstractJaxbFactory{

    public JaxbTestFactory(HardwareSpeaker hardware) {
        super(hardware);
    }

    public void createLockOpen() {
        LinearPotentiometer lp = new LinearPotentiometer();
        lp.setPosition(0);
        lp.setOldPosition(0);
        send(ArduinoComponents.LOCK_SENSOR, lp);
    }
}
