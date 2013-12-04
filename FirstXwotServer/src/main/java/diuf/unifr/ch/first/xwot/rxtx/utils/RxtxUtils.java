/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.utils;

import com.google.gson.JsonObject;
import diuf.unifr.ch.first.xwot.rxtx.ArduinoCommunication;
import diuf.unifr.ch.first.xwot.rxtx.TinkerShield;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leo
 */
public class RxtxUtils {
    
    private ArduinoCommunication aCom;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RxtxUtils.class);
    
    public RxtxUtils() {
        aCom = new ArduinoCommunication();
    }
    
    public <T> T getComponent(Class type, TinkerShield pin) {
        JsonObject jsonComponent = aCom.read(pin.toString());
        if(jsonComponent == null) {
            return null;
        }
        Object ret_value = aCom.getGson().fromJson(jsonComponent, type); 
        return (T) type.cast(ret_value);
    }
    
    public void setComponent(TinkerShield pin, Object o) {
        JsonObject jsonComponent = new JsonObject();
        jsonComponent.add(pin.toString(), aCom.getGson().toJsonTree(o));
        aCom.write(jsonComponent);
    }

    public String getRawJson() {
        return aCom.getConnection().getLine();
    }
}
