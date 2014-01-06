/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.notifications;

import diuf.unifr.ch.first.xwot.jaxb.Client;
import diuf.unifr.ch.first.xwot.rxtx.RxtxConnection;
import diuf.unifr.ch.first.xwot.rxtx.listener.RxtxInputListener;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible of sending http post request to a list of
 * clients.<br/>
 * A Client is a JAXB class which have an uri paramter indicating where to send
 * notifications<br/>
 * The list of clients is a list of Client objects. When an event, indicating
 * that there are new hardware informations, has been thrown. All the list is
 * iterated and each client receive a notifiaction if they need one. The pattern
 * builder is used to manage different type of notifications.<br/>
 * The pattern observer is used to observe change of hardware informations.
 *
 * @see Client
 * @author leo
 */
public class Notification {

    /**
     * The list of client
     */
    private final HashMap<String, Client> clients = new HashMap<String, Client>();
    /**
     * logger
     */
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Notification.class);

    /**
     * Part of the pattern builder
     */
    private NotificationBuilder builder;

    public Notification() {
        init();
    }

    /**
     * Start observing changes of hardware informations and notifiy client if
     * they need it.
     */
    private void init() {
        try {
            RxtxConnection.getInstance().addRxtxInputListener(new RxtxInputListener() {

                @Override
                public void jsonChanged(String oldJson, String newJsons) {
                    if (builder.hasNotification()) {
                        for (Object o : getClients()) {
                            Client client = (Client) o;
                            notifyClient(client);
                        }
                    }
                }
            });
        } catch (PortInUseException ex) {
            logger.error("Port already in use", ex);
        } catch (UnsupportedCommOperationException ex) {
            logger.error("Error with hardware", ex);
        } catch (IOException ex) {
            logger.error("Error with hardware connection", ex);
        }
    }

    /**
     * Send http POST request to the uri specified by each client 
     * @param client 
     */
    private void notifyClient(Client client) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost request = new HttpPost(client.getUri());
            request.setEntity(builder.jaxbToStringEntity(client));
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.info("Request to client " + client.getUri()
                        + " failed. HTTP response code: "
                        + response.getStatusLine().getStatusCode()
                );
            }
        } catch (IOException ex) {
            logger.error("Error during the request to " + client.getUri(), ex);
        }
    }

    public void addClient(String url, Client client) {
        clients.put(url, client);
    }

    public void removeClient(String url, Client client) {
        clients.remove(url);
    }

    public Object[] getClients() {
        return clients.values().toArray();
    }
    
    public HashMap<String, Client> getHasMapClients() {
        return clients;
    }

    public void setBuilder(NotificationBuilder builder) {
        this.builder = builder;
    }
}
