/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.notifications;

import diuf.unifr.ch.first.xwot.jaxb.Client;
import diuf.unifr.ch.first.xwot.jaxb.Lock;
import diuf.unifr.ch.first.xwot.rxtx.components.ArduinoComponents;
import diuf.unifr.ch.first.xwot.rxtx.components.LinearPotentiometer;
import diuf.unifr.ch.first.xwot.rxtx.mapper.LockMapper;
import diuf.unifr.ch.first.xwot.rxtx.utils.RxtxUtils;
import java.io.UnsupportedEncodingException;
import org.apache.http.entity.StringEntity;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the class NotificationBuilder<br/>
 * Responsible to manage the notification of the lock context
 *
 * @author leo
 */
public class LockNotificationBuilder extends NotificationBuilder {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LockNotificationBuilder.class);
    private Lock oldLock = new Lock();
    private Lock lock;

    public LockNotificationBuilder() {
        logger.debug("LockNotificationBuilder Initialized");
    }

    /**
     * Determine if the state of the lock context has changed
     * 
     * @see Lock
     * @return <code>true</code> if a the lock context has changed after the last call of this method. <code>false</code> otherwise.
     */
    @Override
    public boolean hasNotification() {
        setLock();
        boolean isSame = lock.equalsToLock(oldLock);
        if (isSame) {
            logger.debug(lock.getState() + " == " + oldLock.getState());
            return false;
        } else {
            logger.debug("lock: " + lock.getState() + " != " + "oldLock:" + oldLock.getState());
            oldLock = lock;
            return true;
        }
    }

    /**
     * Encode into xml the Lock JAXB class
     * 
     * @see Lock
     * @param client
     * @return instance of a StringEntity containg xml informations
     */
    @Override
    public StringEntity jaxbToStringEntity(Client client) {
        StringEntity body = null;
        if (lock == null || !lock.equalsToLock(oldLock)) {
            setLock();
        }
        try {
            body = new StringEntity(jaxbToXml(Lock.class, lock));
            body.setContentType("application/xml");
        } catch (UnsupportedEncodingException ex) {
            logger.error("Unable to encode StringEntity", ex);
        }
        return body;
    }

    private void setLock() {
        LinearPotentiometer lp = new RxtxUtils().getComponent(LinearPotentiometer.class, ArduinoComponents.LOCK_SENSOR);
        lock = new LockMapper(lp).map();
        logger.debug("lock: " + lock.getState());
    }
}
