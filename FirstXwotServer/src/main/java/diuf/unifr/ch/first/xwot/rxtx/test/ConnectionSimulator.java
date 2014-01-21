/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.test;

import diuf.unifr.ch.first.xwot.rxtx.test.exception.SocatNotStartedError;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
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
    private String correctPort;
    private Process process;
    private HardwareSpeaker hardware;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ConnectionSimulator.class);
    private static ConnectionSimulator instance;

    public ConnectionSimulator() {
        initFromOs();
        startSocat();
        waitForSocatStarted();
        setSystemProperty();
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
            exec = "socat -d -d PTY: PTY: ";
            correctPort = "/dev/pt/s";
        } else if (os.equals("mac os x")) {
            exec = "socat -d -d PTY PTY ";
            correctPort = "/dev/ttys";
        } else {
            //TODO
            throw new NotImplementedException();
        }
    }

    private synchronized void startSocat() {
        Runtime runtime = Runtime.getRuntime();
        try {
            process = runtime.exec(exec);
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.debug("socat strated");
    }

    private synchronized void waitForSocatStarted() {
        try {
            Timer t = new Timer(5000, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    throw new SocatNotStartedError("Socat take more than 5 seconds to be started. Too long.");
                }
            });
            t.setRepeats(false);
            t.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            slave = getSocatPort(in.readLine());
            logger.debug("slave is on port: " + slave);
            master = getSocatPort(in.readLine());
            logger.debug("master is on port: " + master);
            if (!in.readLine().contains("N starting data transfer loop with FDs [3,3] and [5,5]")
                    || !master.contains(correctPort) || !slave.contains(correctPort)) {
                throw new SocatNotStartedError("socat can not be strated properly");
            }
            t.stop();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private synchronized void setSystemProperty() {
        System.setProperty("gnu.io.rxtx.SerialPorts", slave + ":" + master);
        System.setProperty("xwot.test.port", slave);
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
