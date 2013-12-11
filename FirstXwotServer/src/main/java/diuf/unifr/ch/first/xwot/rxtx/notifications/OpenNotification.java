/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.notifications;

import diuf.unifr.ch.first.xwot.jaxb.Open;
import diuf.unifr.ch.first.xwot.rxtx.components.ArduinoComponents;
import diuf.unifr.ch.first.xwot.rxtx.components.LinearPotentiometer;
import diuf.unifr.ch.first.xwot.rxtx.mapper.OpenMapper;
import diuf.unifr.ch.first.xwot.rxtx.utils.RxtxUtils;

/**
 *
 * @author leo
 */
public class OpenNotification extends Notification{

    private static OpenNotification instance;
    private Open open;
    private Open oldOpen;
    
    public static synchronized OpenNotification getInstance() {
        if(instance == null) {
            instance = new OpenNotification();
            instance.init();
        }
        return instance;
    }
    
    @Override
    protected Class getJaxbClass() {
        return Open.class;
    }

    @Override
    protected <T> T getJaxbEntity() {
        Class type = getJaxbClass();
        return (T) type.cast(open);
    }

    @Override
    protected boolean hasNotification() {
        LinearPotentiometer lp = new RxtxUtils().getComponent(LinearPotentiometer.class, ArduinoComponents.OPEN_SENSOR);
        open = new OpenMapper(lp).map();
        if(open.equalsToOpen(oldOpen)) {
            return false;
        }
        oldOpen = open;
        return true;
    }
    
}
