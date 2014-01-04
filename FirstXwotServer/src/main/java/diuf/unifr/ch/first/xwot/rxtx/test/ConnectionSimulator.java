/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author leo
 */
public class ConnectionSimulator {

    private String exec;
    private String slave;
    private String master;
    private Process process;
    private HardwareSpeaker hardware;
    private static final ConnectionSimulator instance = new ConnectionSimulator();

    public ConnectionSimulator() {
        initFromOs();
        startSocat();
    }
    
    public static synchronized ConnectionSimulator getInstance() {
        return instance;
    }

    private void initFromOs() {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println(os);
        if (os.equals("Linux")) {
            //TODO
            throw new NotImplementedException();
        } else if (os.equals("mac os x")) {
            slave = "/dev/ttys001";
            master = "/dev/ttys002";
            exec = "socat -d -d PTY PTY ";
            System.setProperty("gnu.io.rxtx.SerialPorts", slave + ":" + master);
            System.setProperty("xwot.test.port", slave);
        } else {
            //TODO
            throw new NotImplementedException();
        }
    }

    private void startSocat() {
        Runtime runtime = Runtime.getRuntime();
        try {
            process = runtime.exec(exec);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stop() {
        process.destroy();
        System.setProperty("gnu.io.rxtx.SerialPorts", "");
        System.setProperty("xwot.test.port", "");
        if(hardware != null) {
            hardware.close();
            hardware = null;
           // System.out.println("closing");
        }
    }
    
    public HardwareSpeaker getHardwareSpeaker() {
        if(hardware == null) {
            hardware = new HardwareSpeaker(master);
        }
        return hardware;
    }
}
