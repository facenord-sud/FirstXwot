
package diuf.unifr.ch.first.xwot.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for null.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
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
    UNKNOW,
    OPEN;

    public String value() {
        return name();
    }

    public static State fromValue(String v) {
        return valueOf(v);
    }

}
