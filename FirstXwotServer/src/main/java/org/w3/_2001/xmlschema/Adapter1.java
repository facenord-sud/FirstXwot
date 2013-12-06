
package org.w3._2001.xmlschema;

import java.net.URI;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, URI>
{


    public URI unmarshal(String value) {
        return URI.create(value);
    }

    public String marshal(URI value) {
        if (value == null) {
            return null;
        }
        return value.toASCIIString();
    }

}
