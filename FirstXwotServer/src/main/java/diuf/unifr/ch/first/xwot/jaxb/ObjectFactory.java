
package diuf.unifr.ch.first.xwot.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the diuf.unifr.ch.first.xwot.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _State_QNAME = new QName("http://jaxb.xwot.first.ch.unifr.diuf", "state");
    private final static QName _Position_QNAME = new QName("http://jaxb.xwot.first.ch.unifr.diuf", "position");
    private final static QName _Speed_QNAME = new QName("http://jaxb.xwot.first.ch.unifr.diuf", "speed");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: diuf.unifr.ch.first.xwot.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Lock }
     * 
     */
    public Lock createLock() {
        return new Lock();
    }

    /**
     * Create an instance of {@link Open }
     * 
     */
    public Open createOpen() {
        return new Open();
    }

    /**
     * Create an instance of {@link Door }
     * 
     */
    public Door createDoor() {
        return new Door();
    }

    /**
     * Create an instance of {@link Door.ListOfDevices }
     * 
     */
    public Door.ListOfDevices createDoorListOfDevices() {
        return new Door.ListOfDevices();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link State }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxb.xwot.first.ch.unifr.diuf", name = "state")
    public JAXBElement<State> createState(State value) {
        return new JAXBElement<State>(_State_QNAME, State.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxb.xwot.first.ch.unifr.diuf", name = "position")
    public JAXBElement<Integer> createPosition(Integer value) {
        return new JAXBElement<Integer>(_Position_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jaxb.xwot.first.ch.unifr.diuf", name = "speed")
    public JAXBElement<Integer> createSpeed(Integer value) {
        return new JAXBElement<Integer>(_Speed_QNAME, Integer.class, null, value);
    }

}
