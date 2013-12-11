/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import gnu.io.PortInUseException;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leo
 */
public class ArduinoCommunication {

    private RxtxConnection connection;
    private final Gson gson;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ArduinoCommunication.class);

    public ArduinoCommunication() {
        gson = new Gson();
        try {
            connection = RxtxConnection.getInstance();
        } catch (PortInUseException ex) {
            Logger.getLogger(ArduinoCommunication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(ArduinoCommunication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArduinoCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void write(JsonObject o) {
        try {
            connection.getOutput().write(o.toString().getBytes());
            logger.debug("writing to arduino : " + gson.toJson(o));
        } catch (IOException ex) {
            Logger.getLogger(ArduinoCommunication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JsonObject read(String element) {
        JsonElement jElement;
        try {
            jElement = new JsonParser().parse(getConnection().getLine());
        } catch (NullPointerException e) {
            logger.info("error during communication with arduino. No informations from hardware");
            return null;
        } catch (JsonSyntaxException e) {
            logger.info("error during communication with arduino. Unable to parse json");
            return null;
        }
        JsonObject jObject = jElement.getAsJsonObject();
        if (!jObject.has(element)) {
            logger.debug("'" + element + "' not found in the json string: " + jObject.toString());
            return null;
        }
        return jObject.getAsJsonObject(element);
    }

    public RxtxConnection getConnection() {
        return connection;
    }

    public void setConnection(RxtxConnection connection) {
        this.connection = connection;
    }

    public Gson getGson() {
        return gson;
    }

}
