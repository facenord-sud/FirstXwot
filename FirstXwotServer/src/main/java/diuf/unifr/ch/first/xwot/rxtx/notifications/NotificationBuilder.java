package diuf.unifr.ch.first.xwot.rxtx.notifications;

import com.google.gson.Gson;
import diuf.unifr.ch.first.xwot.jaxb.Client;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.http.entity.StringEntity;

/**
 *
 * @author leo
 */
public abstract class NotificationBuilder {
    public abstract StringEntity jaxbToStringEntity(Client client);
    public abstract boolean hasNotification();
    
    public String jaxbToXml(Class type, Object entity) throws JAXBException{
        StringWriter writer = new StringWriter();
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.marshal(type.cast(entity), writer);
        return writer.getBuffer().toString();
    }
    
    public String jaxbToJson(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }
}
