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
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leo
 */
public class Notification {

    private final Collection<Client> clients = new ArrayList<Client>();
    private final static Notification instance = new Notification();
    private final DefaultHttpClient httpClient = new DefaultHttpClient();
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Notification.class);

    private Notification() {
        try {
            RxtxConnection.getInstance().addRxtxInputListener(new RxtxInputListener() {

                @Override
                public void jsonChanged(String oldJson, String newJsons) {
                    if (clients.size() >= 1) {
                        for (Client client : getClients()) {
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

    private void notifyClient(Client client) {
        try {
            HttpPost request = new HttpPost(client.getUri());
            request.setEntity(clientToEntity(client));
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() != 200) {
                logger.info("Request to client " + client.getUri()
                        + " failed. HTTP response code: "
                        + response.getStatusLine().getStatusCode()
                );
            }
        } catch (IOException ex) {
            logger.error("Error during the request to " + client.getUri(), ex);

        } catch (JAXBException ex) {
            logger.error("Error while encoding client", ex);
        }
    }

    private StringEntity clientToEntity(Client client) throws JAXBException, UnsupportedEncodingException {
        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(Client.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(client, writer);
        StringEntity body = new StringEntity(writer.getBuffer().toString());
        body.setContentType("application/xml");
        return body;
    }

    public static Notification getInstace() {
        return instance;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public Client[] getClients() {
        return (Client[]) clients.toArray();
    }
}
