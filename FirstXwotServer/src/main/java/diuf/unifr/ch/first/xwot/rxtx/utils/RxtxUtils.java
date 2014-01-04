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
    private JsonObject jsonComponent = new JsonObject();
    
    public RxtxUtils() {
        aCom = new ArduinoCommunication();
    }
    
    public <T> T getComponent(Class type, TinkerShield pin) {
        JsonObject jsonObject = aCom.read(pin.toString());
        if(jsonObject == null) {
            return null;
        }
        Object ret_value = aCom.getGson().fromJson(jsonObject, type); 
        return (T) type.cast(ret_value);
    }
    
    public void addComponent(TinkerShield pin, Object o) {
        jsonComponent.add(pin.toString(), aCom.getGson().toJsonTree(o));
    }
    
    public void send() {
        aCom.write(jsonComponent);
        jsonComponent = new JsonObject();
    }

    public String getRawJson() {
        return aCom.getConnection().getLine();
    }

    public JsonObject getJsonComponent() {
        return jsonComponent;
    }
}
