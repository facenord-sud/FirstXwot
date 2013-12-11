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
import diuf.unifr.ch.first.xwot.rxtx.mapper.LockMapper;
import diuf.unifr.ch.first.xwot.rxtx.mapper.OpenMapper;
import diuf.unifr.ch.first.xwot.rxtx.utils.RxtxUtils;
import java.io.UnsupportedEncodingException;
import org.apache.http.entity.StringEntity;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leo
 */
public class OpenNotificationBuilder extends NotificationBuilder {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LockNotificationBuilder.class);
    private Open open;
    private Open oldOpen;

    @Override
    public boolean hasNotification() {
        LinearPotentiometer lp = new RxtxUtils().getComponent(LinearPotentiometer.class, ArduinoComponents.OPEN_SENSOR);
        open = new OpenMapper(lp).map();
        if (open.equalsToOpen(oldOpen)) {
            return false;
        }
        oldOpen = open;
        return true;
    }

    @Override
    public StringEntity jaxbToStringEntity(Client client) {
        StringEntity body = null;
        if (open == null || open.equalsToOpen(oldOpen)) {
            setOpen();
        }
        try {
            body = new StringEntity(jaxbToJson(open));
            body.setContentType("application/xml");
        } catch (UnsupportedEncodingException ex) {
            logger.error("Unable to encode StringEntity", ex);
        }
        return body;
    }

    private void setOpen() {
        LinearPotentiometer lp = new RxtxUtils().getComponent(LinearPotentiometer.class, ArduinoComponents.LOCK_SENSOR);
        open = new OpenMapper(lp).map();
    }

}
