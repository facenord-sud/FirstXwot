
package diuf.unifr.ch.first.xwot.jaxb;

import javax.xml.bind.annotation.XmlRegistry;


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
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

    /**
     * Create an instance of {@link Open }
     * 
     */
    public Open createOpen() {
        return new Open();
    }

}
