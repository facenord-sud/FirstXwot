package diuf.unifr.ch.first.xwot.rxtx.notifications;

import com.google.gson.Gson;
import diuf.unifr.ch.first.xwot.jaxb.Client;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.http.entity.StringEntity;
import org.slf4j.LoggerFactory;

/**
 * Abstract class that is implemented by a class responsible of a certain type
 * of notification.<br/>
 * A certain type of notification is sended when a part of a hardware has a
 * state changed. In this project, a context.
 * <p>
 * In this project, there exist two type of notification. Notification for the
 * context open and notification for the context lock</p>
 *
 * @author leo
 */
public abstract class NotificationBuilder {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NotificationBuilder.class);

    /**
     * This method encode hardware informations into a specific manner given by
     * an implementation of this class.
     *
     * @param client
     * @return <code>StringEntity</code> instance containing information about a
     * part of the hardware to send to the client.
     */
    public abstract StringEntity jaxbToStringEntity(Client client);

    /**
     * Determine if a group of clients need to receive notification of a certain
     * type
     *
     * @return <code>true</code> if a group of clients need to receive
     * notification, <code>false</code> otherwise.
     */
    public abstract boolean hasNotification();

    /**
     * Encode a Jaxb class into xml, following the Jaxb conventions.
     * 
     * @param type the type of the class to encode
     * @param entity the instance of the class to encode
     * @return String xml
     */
    public String jaxbToXml(Class type, Object entity) {
        StringWriter writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.marshal(type.cast(entity), writer);

        } catch (JAXBException ex) {
            logger.error("Unable to parse jaxb to xml", ex);
        }
        return writer.getBuffer().toString();
    }

    /**
     * Encode a Jaxb class into json, with the Gson library fromm google
     * 
     * @param o the instance of the jaxb class to encode
     * @return String json
     */
    public String jaxbToJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }
}
