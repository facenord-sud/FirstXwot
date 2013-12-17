/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.notifications;

import diuf.unifr.ch.first.xwot.jaxb.Client;
import diuf.unifr.ch.first.xwot.jaxb.Open;
import diuf.unifr.ch.first.xwot.rxtx.components.ArduinoComponents;
import diuf.unifr.ch.first.xwot.rxtx.components.LinearPotentiometer;
import diuf.unifr.ch.first.xwot.rxtx.mapper.OpenMapper;
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
public class OpenNotificationBuilder extends NotificationBuilder {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LockNotificationBuilder.class);
    private Open open;
    private Open oldOpen;

    /**
     * Determine if the state or the position of the open context has changed
     * 
     * @see Open
     * @return <code>true</code> if a the open context has changed after the last call of this method. <code>false</code> otherwise.
     */
    @Override
    public boolean hasNotification() {
        setOpen();
        if (open.equalsToOpen(oldOpen)) {
            return false;
        }
        oldOpen = open;
        return true;
    }

    /**
     * Encode into xml the Open JAXB class
     * 
     * @see Open
     * @param client
     * @return instance of a StringEntity containg xml informations
     */
    @Override
    public StringEntity jaxbToStringEntity(Client client) {
        StringEntity body = null;
        if (open == null || !open.equalsToOpen(oldOpen)) {
            setOpen();
        }
        try {
            body = new StringEntity(jaxbToXml(Open.class, open));
            body.setContentType("application/xml");
        } catch (UnsupportedEncodingException ex) {
            logger.error("Unable to encode StringEntity", ex);
        }
        return body;
    }

    /**
     * Fetch information from the json string hardware and put it in the Open JAXB class
     */
    private void setOpen() {
        LinearPotentiometer lp = new RxtxUtils().getComponent(LinearPotentiometer.class, ArduinoComponents.OPEN_SENSOR);
        open = new OpenMapper(lp).map();
    }

}
