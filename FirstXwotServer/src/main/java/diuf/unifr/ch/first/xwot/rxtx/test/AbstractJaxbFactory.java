/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import diuf.unifr.ch.first.xwot.rxtx.TinkerShield;

/**
 *
 * @author leo
 */
public class AbstractJaxbFactory {
 
    private HardwareSpeaker hardware;
    
    public AbstractJaxbFactory(HardwareSpeaker hardware) {
        this.hardware = hardware;
    }
    
    public void send(TinkerShield pin, Object o) {
        JsonObject jObject = new JsonObject();
        Gson gson = new Gson();
        jObject.add(pin.toString(), gson.toJsonTree(o));
        hardware.speak(jObject.toString());
    }
}
