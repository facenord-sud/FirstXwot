/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.utils;

import diuf.unifr.ch.first.xwot.rxtx.TinkerShield;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.SerialHelpers;

/**
 *
 * @author leo
 */
public class RxtxUtilsTest extends SerialHelpers{
    
    private final String json = "{\"14\":{\"i\":100}}";
    
    public RxtxUtilsTest() {
    }

    /**
     * Test of getComponent method, of class RxtxUtils.
     */
    @Test
    public void testGetComponent() {
        hardware.speak(json);
        TinkerShield t = TinkerShield.i_0;
        RxtxUtils utils  = new RxtxUtils();
        SimpleComponent result = utils.getComponent(SimpleComponent.class, t);
        assertEquals("the value of i of result should be equal to 100", result.i, 100);
    }

    /**
     * Test of addComponent method, of class RxtxUtils.
     */
    @Test
    public void testAddComponent() {
        TinkerShield t = TinkerShield.i_0;
        SimpleComponent sc = new SimpleComponent();
        RxtxUtils utils  = new RxtxUtils();
        utils.addComponent(t, sc);
        assertResponseContains(utils.getJsonComponent().toString(), json);
    }

    /**
     * Test of send method, of class RxtxUtils.
     */
    @Test
    public void testSend() {
        TinkerShield t = TinkerShield.i_0;
        SimpleComponent sc = new SimpleComponent();
        RxtxUtils utils  = new RxtxUtils();
        utils.addComponent(t, sc);
       // utils.send();
       // assertEquals(hardware.listen(), json);
    }

    /**
     * Test of getRawJson method, of class RxtxUtils.
     */
    @Test
    public void testGetRawJson() {
        
    }
    
    
    
}
