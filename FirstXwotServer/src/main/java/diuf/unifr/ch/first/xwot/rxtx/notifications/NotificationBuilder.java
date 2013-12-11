package diuf.unifr.ch.first.xwot.rxtx.notifications;

import com.google.gson.Gson;
import diuf.unifr.ch.first.xwot.jaxb.Client;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.http.entity.StringEntity;
import org.slf4j.LoggerFactory;

/**
 *
 * @author leo
 */
public abstract class NotificationBuilder {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NotificationBuilder.class);

    public abstract StringEntity jaxbToStringEntity(Client client);

    public abstract boolean hasNotification();

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

    public String jaxbToJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }
}
