/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.components;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author leo
 */
public class LinearPotentiometerTest {

    LinearPotentiometer lp = new LinearPotentiometer();

    public LinearPotentiometerTest() {
    }

    /**
     * Test of getPosition method, of class LinearPotentiometer.
     */
    @Test
    public void testGetPosition() {
        lp.setPosition(100);
        assertEquals(lp.getPosition(), 100);
    }

    /**
     * Test of setPosition method, of class LinearPotentiometer.
     */
    @Test
    public void testSetPosition() {
        lp = new LinearPotentiometer();
        int r = lp.getPosition();
        lp.setPosition(100);
        assertNotEquals(r, lp.getPosition());
        assertEquals(lp.getPosition(), 100);
    }

    /**
     * Test of getOldPosition method, of class LinearPotentiometer.
     */
    @Test
    public void testGetOldPosition() {
        lp.setOldPosition(100);
        assertEquals(lp.getOldPosition(), 100);
    }

    /**
     * Test of setOldPosition method, of class LinearPotentiometer.
     */
    @Test
    public void testSetOldPosition() {
        lp = new LinearPotentiometer();
        int r = lp.getOldPosition();
        lp.setOldPosition(100);
        assertNotEquals(r, lp.getOldPosition());
        assertEquals(lp.getOldPosition(), 100);
    }

    /**
     * Test of getDifference method, of class LinearPotentiometer.
     */
    @Test
    public void testGetDifference() {
        lp = new LinearPotentiometer();
        lp.setPosition(100);
        lp.setOldPosition(50);
        assertEquals(lp.getDifference(), 50-100);
    }

    /**
     * Test of isClosing method, of class LinearPotentiometer.
     */
    @Test
    public void testIsClosing() {
        lp = new LinearPotentiometer();
        lp.setPosition(100);
        lp.setOldPosition(50);
        assertTrue(lp.isClosing());
        lp.setPosition(50);
        lp.setOldPosition(50);
        assertTrue(lp.isClosing()==false);
        lp.setPosition(50);
        lp.setOldPosition(100);
        assertTrue(lp.isClosing()==false);
    }

    /**
     * Test of getPercentPosition method, of class LinearPotentiometer.
     */
    @Test
    public void testGetPercentPosition() {
        lp = new LinearPotentiometer();
        lp.setPosition(1023);
        assertEquals(lp.getPercentPosition(), 100);
        lp.setPosition(0);
        assertEquals(lp.getPercentPosition(), 0);
        lp.setPosition(512);
        assertEquals(lp.getPercentPosition(), 50);
    }

    /**
     * Test of setFromPercentPosition method, of class LinearPotentiometer.
     */
    @Test
    public void testSetFromPercentPosition() {
        lp = new LinearPotentiometer();
        lp.setFromPercentPosition(100);
        assertEquals(lp.getPosition(), 1023);
        lp.setFromPercentPosition(0);
        assertEquals(lp.getPosition(), 0);
        lp.setFromPercentPosition(50);
        assertEquals(lp.getPosition(), 512);
    }

}
