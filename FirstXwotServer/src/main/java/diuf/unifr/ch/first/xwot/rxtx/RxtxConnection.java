package diuf.unifr.ch.first.xwot.rxtx;

import diuf.unifr.ch.first.xwot.rxtx.exception.PortNotFoundException;
import diuf.unifr.ch.first.xwot.rxtx.listener.RxtxInputListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.EventListenerList;
import org.slf4j.LoggerFactory;

/**
 * The most part of this code is exactly the same as in this web page:
 * http://playground.arduino.cc/Interfacing/Java A lit bit of apaptation
 *
 * @author leo and uknow author (http://playground.arduino.cc/Interfacing/Java)
 */
public class RxtxConnection {

    private static SerialPort serialPort;

    /**
     * The port we're normally going to use.
     */
    private static String PORT_NAMES[] = {
        "/dev/tty.usbmodem1421",// Mac OS X 10.8
        "/dev/tty.usbmodem1411",// Mac OS X 10.9
        "/dev/ttyUSB0", // Linux
        "/dev/ttyACM0", // debian/raspbian
        "/dev/ttyAMA0", // debian/raspbian
        "/dev/ttyS1",
        "COM3", // Windows
    };

    /**
     * A BufferedReader which will be fed by a InputStreamReader converting the
     * bytes into characters making the displayed results codepage independent
     * from arduino website
     */
    private BufferedReader input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;

    private static RxtxConnection instance = null;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RxtxConnection.class);

    private String line;

    private final Collection<RxtxInputListener> rxtxInputLIstener = new ArrayList<RxtxInputListener>();

    private RxtxConnection() throws PortInUseException, UnsupportedCommOperationException, IOException {
    }

    public static synchronized RxtxConnection getInstance() throws PortInUseException, UnsupportedCommOperationException, IOException {

        try {
            if (instance == null) {
                instance = new RxtxConnection();
                instance.initialize();
                logger.debug("Connection initialized");
            }
            return instance;
        } catch (PortNotFoundException e) {
            instance = null;
            return new RxtxConnection();
        }

    }

    private void initialize() throws PortInUseException, UnsupportedCommOperationException, IOException, PortNotFoundException {
        String testPort = System.getProperty("xwot.test.port");
        logger.debug(testPort);
        if(testPort != null && !testPort.equals("")) {
            PORT_NAMES = new String[1];
            PORT_NAMES[0] = testPort;
        }
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        logger.debug("looking for ports...");
        if (portEnum.hasMoreElements()) {
            logger.debug("serial port(s) found on your os");
        } else {
            logger.error("No port found. Linux user: don't forget that only ports like this: /dev/ttyS* will be scanned");
        }
        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                logger.debug(portName + " == " + currPortId.getName());
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    break;
                }
            }
        }
        if (portId == null) {
            logger.error("Could not find COM port.");
            close();
            throw new PortNotFoundException();
        }

        // open serial port, and use class name for the appName.
        serialPort = (SerialPort) portId.open(this.getClass().getName(),
                TIME_OUT);

        // set port parameters
        serialPort.setSerialPortParams(DATA_RATE,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        // open the streams
        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        output = serialPort.getOutputStream();
        try {
            addNewDataListener();
        } catch (TooManyListenersException ex) {
            Logger.getLogger(RxtxConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void addNewDataListener() throws TooManyListenersException {
        serialPort.addEventListener(new SerialPortEventListener() {

            @Override
            public void serialEvent(SerialPortEvent spEvent) {
                if (spEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                    try {
                        String _line = input.readLine();
                        if (!_line.equals("")) {
                            setLine(_line);
                            logger.debug("new line from arduino: " + line);
                        }
                    } catch (IOException e) {
                        logger.error("IO exception. Are you closing ?", e);
                    }
                }
            }
        });
        serialPort.notifyOnDataAvailable(true);
    }

    /**
     * This should be called when you stop using the port. This will prevent
     * port locking on platforms like Linux.
     */
    public static synchronized void close() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
            serialPort = null;
        }
        if(instance != null) {
            instance = null;
        }
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public BufferedReader getInput() {
        return input;
    }

    public OutputStream getOutput() {
        return output;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
        fireLineChanged(this.line, line);
    }

    public RxtxInputListener[] getRxtxInputLIstener() {
        return (RxtxInputListener[]) rxtxInputLIstener.toArray();
    }

    public void addRxtxInputListener(RxtxInputListener listener) {
        rxtxInputLIstener.add(listener);
        logger.debug("listener " + rxtxInputLIstener.size());
    }

    public void removeRxtxInputListener(RxtxInputListener listener) {
        rxtxInputLIstener.remove(listener);
    }

    protected void fireLineChanged(String oldLine, String newLine) {
        /*if(oldLine.equals(newLine)) {
            return;
        }*/
        //logger.debug("line changed" + rxtxInputLIstener.size());
        Object[] objects = rxtxInputLIstener.toArray();
        for (Object o : objects) {
            //logger.debug("linstener notified");
            RxtxInputListener listener = (RxtxInputListener) o;
            listener.jsonChanged(oldLine, newLine);
        }
    }
}
