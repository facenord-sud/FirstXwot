/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.rxtx.notifications;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import org.apache.http.entity.AbstractHttpEntity;

/**
 * from http://mail-archives.apache.org/mod_mbox/hc-httpclient-users/201205.mbox/%3C1336131004.13924.16.camel@ubuntu%3E
 * Not used
 * @author leo
 */
class JAXBEntity extends AbstractHttpEntity {

    public JAXBEntity() {
        super();
        setContentType("application/xml");
    }
    
    @Override
    public InputStream getContent() throws IOException {
        // Omitted for brevity
        throw new UnsupportedOperationException();
    }

    @Override
    public long getContentLength() {
        return -1;
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public void writeTo(
       final OutputStream outstream) throws IOException {
        XMLOutputFactory xmloutputf = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter writer = xmloutputf.createXMLStreamWriter(
               outstream, "UTF-8");
            // do the actual writing using an appropriate Marshaller 
            // and do not forget to flush
            writer.flush();
        } catch (XMLStreamException ex) {
            // Re-throw appropriate i/o or runtime exception
            throw new IOException("Oppsie");
        }
    }
    
}
