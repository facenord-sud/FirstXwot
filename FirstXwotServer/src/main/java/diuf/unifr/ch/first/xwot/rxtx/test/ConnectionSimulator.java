/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.test;

import diuf.unifr.ch.first.xwot.rxtx.RxtxConnection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
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
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ConnectionSimulator.class);
    private static ConnectionSimulator instance;

    public ConnectionSimulator() {
        initFromOs();
        startSocat();
    }

    public static synchronized ConnectionSimulator getInstance() {
        if (instance == null) {
            instance = new ConnectionSimulator();
        }
        return instance;
    }

    private synchronized void initFromOs() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.equals("Linux")) {
            slave = "/dev/pts/6";
            master = "/dev/pts/7";
            exec = "socat -d -d PTY: PTY: ";
        } else if (os.equals("mac os x")) {
            slave = "/dev/ttys001";
            master = "/dev/ttys002";
            exec = "socat -d -d PTY PTY ";
        } else {
            //TODO
            throw new NotImplementedException();
        }
        System.setProperty("gnu.io.rxtx.SerialPorts", slave + ":" + master);
        System.setProperty("xwot.test.port", slave);
    }

    private synchronized void startSocat() {
        Runtime runtime = Runtime.getRuntime();
        try {
            process = runtime.exec(exec);
            Thread.sleep(1000);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSimulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ConnectionSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.debug("socat strated");
    }

    public synchronized void stop() {
        if (hardware != null) {
           // hardware.speak("");
            hardware.close();
            hardware = null;
            logger.debug("closing hardware speaker");
        }
        process.destroy();
        System.setProperty("gnu.io.rxtx.SerialPorts", "");
        System.setProperty("xwot.test.port", "");
        
        logger.debug("stopping socat and setting default system properties");
    }

    public HardwareSpeaker getHardwareSpeaker() {
        if (hardware == null) {
            hardware = new HardwareSpeaker(master);
            logger.debug("Hardware speaker initialized");
        }
        return hardware;
    }
}
