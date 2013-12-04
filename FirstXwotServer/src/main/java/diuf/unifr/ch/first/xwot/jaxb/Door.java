
package diuf.unifr.ch.first.xwot.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listOfDevices">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://jaxb.xwot.first.ch.unifr.diuf}open"/>
 *                   &lt;element ref="{http://jaxb.xwot.first.ch.unifr.diuf}lock"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "listOfDevices"
})
@XmlRootElement(name = "door")
public class Door {

    @XmlElement(required = true)
    protected Door.ListOfDevices listOfDevices;

    /**
     * Gets the value of the listOfDevices property.
     * 
     * @return
     *     possible object is
     *     {@link Door.ListOfDevices }
     *     
     */
    public Door.ListOfDevices getListOfDevices() {
        return listOfDevices;
    }

    /**
     * Sets the value of the listOfDevices property.
     * 
     * @param value
     *     allowed object is
     *     {@link Door.ListOfDevices }
     *     
     */
    public void setListOfDevices(Door.ListOfDevices value) {
        this.listOfDevices = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element ref="{http://jaxb.xwot.first.ch.unifr.diuf}open"/>
     *         &lt;element ref="{http://jaxb.xwot.first.ch.unifr.diuf}lock"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "open",
        "lock"
    })
    public static class ListOfDevices {

        @XmlElement(required = true)
        protected Open open;
        @XmlElement(required = true)
        protected Lock lock;

        /**
         * Gets the value of the open property.
         * 
         * @return
         *     possible object is
         *     {@link Open }
         *     
         */
        public Open getOpen() {
            return open;
        }

        /**
         * Sets the value of the open property.
         * 
         * @param value
         *     allowed object is
         *     {@link Open }
         *     
         */
        public void setOpen(Open value) {
            this.open = value;
        }

        /**
         * Gets the value of the lock property.
         * 
         * @return
         *     possible object is
         *     {@link Lock }
         *     
         */
        public Lock getLock() {
            return lock;
        }

        /**
         * Sets the value of the lock property.
         * 
         * @param value
         *     allowed object is
         *     {@link Lock }
         *     
         */
        public void setLock(Lock value) {
            this.lock = value;
        }

    }

}
