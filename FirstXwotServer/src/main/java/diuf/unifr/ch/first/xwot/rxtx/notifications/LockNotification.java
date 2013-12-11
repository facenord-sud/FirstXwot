/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.notifications;

import diuf.unifr.ch.first.xwot.jaxb.Lock;
import diuf.unifr.ch.first.xwot.rxtx.components.ArduinoComponents;
import diuf.unifr.ch.first.xwot.rxtx.components.LinearPotentiometer;
import diuf.unifr.ch.first.xwot.rxtx.mapper.LockMapper;
import diuf.unifr.ch.first.xwot.rxtx.utils.RxtxUtils;


/**
 *
 * @author leo
 */
public class LockNotification extends Notification {

    private static LockNotification instance;
    private Lock oldLock;
    private Lock lock;

    public static synchronized LockNotification getInstance() {
        if (instance == null) {
            instance = new LockNotification();
            instance.init();
        }
        return instance;
    }

    @Override
    protected Class getJaxbClass() {
        return Lock.class;
    }

    @Override
    protected <T> T getJaxbEntity() {
        Class type = getJaxbClass();
        return (T) type.cast(lock);
    }

    @Override
    protected boolean hasNotification() {
        LinearPotentiometer lp = new RxtxUtils().getComponent(LinearPotentiometer.class, ArduinoComponents.LOCK_SENSOR);
        lock = new LockMapper(lp).map();
        if(lock.equalsToLock(oldLock)) {
            return false;
        }
        oldLock = lock;
        return true;
    }
}
