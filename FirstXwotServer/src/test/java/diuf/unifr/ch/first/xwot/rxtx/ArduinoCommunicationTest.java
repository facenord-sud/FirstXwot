/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.TestHelpers;

/**
 *
 * @author leo
 */
public class ArduinoCommunicationTest extends  TestHelpers{
    
    private RxtxConnection conn;
    public ArduinoCommunicationTest() {
        try {
            conn =  RxtxConnection.getInstance();
        } catch (PortInUseException ex) {
            Logger.getLogger(ArduinoCommunicationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedCommOperationException ex) {
            Logger.getLogger(ArduinoCommunicationTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArduinoCommunicationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Test of write method, of class ArduinoCommunication.
     */
    @Test
    public void testWrite() {
        ArduinoCommunication com = new ArduinoCommunication();
        JsonObject json = new JsonObject();
        json.addProperty("hello", "world");
        com.write(json);
        assertEquals("should be the same", json.toString(), hardware.listen());
    }

    /**
     * Test of read method, of class ArduinoCommunication.
     */
    @Test
    public void testRead() {
        ArduinoCommunication com = new ArduinoCommunication();
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        SimpleClass simple = new SimpleClass();
        JsonElement world = gson.toJsonTree(simple);
        json.add("1", world);
        hardware.speak(json.toString());
        assertEquals("Should be equal", world.toString(), com.read("1").toString());
    }
    
    class SimpleClass {
        String world = "world";
    }
}
