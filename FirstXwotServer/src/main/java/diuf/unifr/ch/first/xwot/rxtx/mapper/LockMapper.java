/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.mapper;

import diuf.unifr.ch.first.xwot.jaxb.Lock;
import diuf.unifr.ch.first.xwot.rxtx.components.LinearPotentiometer;

/**
 *
 * @author leo
 */
public class LockMapper {
    
    private final Lock lock = new Lock();
    private final LinearPotentiometer lp;
    
    public LockMapper(LinearPotentiometer lp) {
        this.lp = lp;
    }
    
    public Lock map() {
        if (lp.getPosition() <= LinearPotentiometer.MIDDLE_POSITION) {
            lock.setState(Lock.State.OPEN);
        } else {
            lock.setState(Lock.State.CLOSED);
        }
        return lock;
    }
}
