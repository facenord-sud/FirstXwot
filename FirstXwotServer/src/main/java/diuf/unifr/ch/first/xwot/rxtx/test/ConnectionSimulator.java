/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.test;

import diuf.unifr.ch.first.xwot.rxtx.RxtxConnection;
import diuf.unifr.ch.first.xwot.rxtx.test.exception.SocatNotStartedError;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
        if (os.equals("linux")) {
//            slave = "/dev/pts/6";
//            master = "/dev/pts/7";
            exec = "socat -d -d PTY: PTY: ";
        } else if (os.equals("mac os x")) {
//            slave = "/dev/ttys001";
//            master = "/dev/ttys002";
            exec = "socat -d -d PTY PTY ";
        } else {
            //TODO
            throw new NotImplementedException();
        }
    }

    private synchronized void startSocat() {
        Runtime runtime = Runtime.getRuntime();
        int maxIteration = 0;
        try {
            process = runtime.exec(exec);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            boolean isLaunched = false;
            while (!isLaunched) {
                String socatOutput = in.readLine();
                if (slave == null || slave.equals("")) {
                    slave = getSocatPort(socatOutput);
                    logger.debug("slave is on port: " + slave);
                } else if (master == null || master.equals("")) {
                    master = getSocatPort(socatOutput);
                    logger.debug("master is on port: " + master);
                } else if (!socatOutput.equals("")) {
                    isLaunched = true;
                    break;
                }
                if (maxIteration * 10 == 5000) {//wait max 5 seconds until socat is launched
                    break;
                }
                maxIteration++;
                Thread.sleep(10);
            }
            if (!isLaunched) {
                throw new SocatNotStartedError("socat can not be strated properly");
            }
            System.setProperty("gnu.io.rxtx.SerialPorts", slave + ":" + master);
            System.setProperty("xwot.test.port", slave);
            //Thread.sleep(1000);
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

    private synchronized String getSocatPort(String output) {
        String words[] = output.split(" ");
        return words[words.length - 1];
    }
}
