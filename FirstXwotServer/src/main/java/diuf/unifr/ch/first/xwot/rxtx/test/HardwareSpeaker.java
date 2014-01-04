/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.test;

import diuf.unifr.ch.first.xwot.rxtx.RxtxConnection;
import static diuf.unifr.ch.first.xwot.rxtx.RxtxConnection.close;
import diuf.unifr.ch.first.xwot.rxtx.exception.PortNotFoundException;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leo
 */
public class HardwareSpeaker {

    private String port;

    private static SerialPort serialPort;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HardwareSpeaker.class);

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

    public HardwareSpeaker(String port) {
        this.port = port;
        init();
    }

    private void init() {
        try {
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

            //First, Find an instance of serial port as set in PORT_NAMES.
            while (portEnum.hasMoreElements()) {
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
                if (currPortId.getName().equals(port)) {
                    portId = currPortId;
                }
            }
            if (portId == null) {
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
        } catch (PortInUseException ex) {
            logger.error("Port already in use", ex);
        } catch (UnsupportedCommOperationException ex) {
            logger.error("Error while simulating hardware connection", ex);
        } catch (IOException ex) {
            logger.error("Error while simulating hardware connection", ex);
        } catch (PortNotFoundException ex) {
            logger.error("The port " + port + " can not be found", ex);
        }

    }

    public void speak(String aPhrase) {
        try {
            output.write((aPhrase + "\r\n").getBytes());
            output.flush();
            Thread.sleep(1);
        } catch (IOException ex) {
            logger.error("error while writing data to the server", ex);
        } catch (InterruptedException ex) {
            logger.error("error with thread", ex);
        }
    }

    public String listen() {

        try {
            char[] buff = new char[1024];
            // reads to the end of the stream 
            // converts int to character
            int value = input.read(buff);
            String s = new String(buff, 0, value);
            System.out.println(s);
            return s;
        } catch (IOException e) {
            logger.error("error while reading data sended from server", e);
            return null;
        }
    }

    public void close() {
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }
}
