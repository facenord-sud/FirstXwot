/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.mapper;

import diuf.unifr.ch.first.xwot.components.LinearPotentiometer;
import diuf.unifr.ch.first.xwot.jaxb.Lock;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author leo
 */
public class LockMapperTest {
    
//    public LockMapperTest() {
//    }

    /**
     * Test of map method, of class LockMapper.
     */
    @Test
    public void testMap() {
        LinearPotentiometer lp = new LinearPotentiometer();
        lp.setPosition(0);
        Lock lock;
        lock = new LockMapper(lp).map();
        assertEquals("lock should be open", lock.getState(), Lock.State.OPEN);
        lp.setPosition(1023);
        lock = new LockMapper(lp).map();
        assertEquals("lock should be closed", lock.getState(), Lock.State.CLOSED);
    }   
}
