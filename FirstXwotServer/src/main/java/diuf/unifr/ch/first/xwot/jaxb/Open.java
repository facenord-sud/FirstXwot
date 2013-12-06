package diuf.unifr.ch.first.xwot.jaxb;

import java.net.URI;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter1;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="state">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="CLOSING"/>
 *               &lt;enumeration value="OPENING"/>
 *               &lt;enumeration value="CLOSED"/>
 *               &lt;enumeration value="OPEN"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *       &lt;attribute name="uri" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "state",
    "position"
})
@XmlRootElement(name = "open")
public class Open {

    @XmlElement(required = true)
    protected Open.State state;
    protected int position;
    @XmlAttribute(name = "uri")
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "anyURI")
    protected URI uri;

    /**
     * Gets the value of the state property.
     *
     * @return possible object is {@link Open.State }
     *
     */
    public Open.State getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     *
     * @param value allowed object is {@link Open.State }
     *
     */
    public void setState(Open.State value) {
        this.state = value;
    }

    /**
     * Gets the value of the position property in percent.
     *
     * @return int the doors' position in percent
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property in percent.
     *
     * @param value The value to set in percent
     */
    public void setPosition(int value) {
        this.position = value;
    }

    /**
     * Gets the value of the uri property.
     *
     * @return possible object is {@link String }
     *
     */
    public URI getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setUri(URI value) {
        this.uri = value;
    }

    /**
     * <p>
     * Java class for null.
     *
     * <p>
     * The following schema fragment specifies the expected content contained
     * within this class.
     * <p>
     * <ul>
     * <li>CLOSING means that the door is moving and is closing</li>
     * <li>OPENING means that the door is moving ans is closing</li>
     * <li>CLOSED means that the door is fully closed and is not moving</li>
     * <li>OPEN means that the door is not fully closed (it can be open at 50%)
     * and is not moving.</li>
     * </ul>
     * <pre>
     * &lt;simpleType>
     *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *     &lt;enumeration value="CLOSING"/>
     *     &lt;enumeration value="OPENING"/>
     *     &lt;enumeration value="CLOSED"/>
     *     &lt;enumeration value="OPEN"/>
     *   &lt;/restriction>
     * &lt;/simpleType>
     * </pre>
     *
     */
    @XmlType(name = "")
    @XmlEnum
    public enum State {

        CLOSING,
        OPENING,
        CLOSED,
        OPEN;

        public String value() {
            return name();
        }

        public static Open.State fromValue(String v) {
            return valueOf(v);
        }

    }

}
