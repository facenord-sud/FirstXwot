/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.rxtx.notifications;

import diuf.unifr.ch.first.xwot.jaxb.Client;
import diuf.unifr.ch.first.xwot.jaxb.Open;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.apache.http.entity.StringEntity;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.TestHelpers;

/**
 *
 * @author leo
 */
public class NotificationBuilderTest extends TestHelpers {

    public NotificationBuilderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of jaxbToStringEntity method, of class NotificationBuilder.
     */
    @Test
    public void testJaxbToStringEntity() {
        NotificationBuilderImpl nbi = new NotificationBuilderImpl();
        Open open = new Open();
        open.setPosition(50);
        open.setState(Open.State.OPEN);
        String response = nbi.jaxbToXml(Open.class, open);
        assertXmlResponse(response);
        System.out.println(response);
    }

    @Test
    public void testJaxbToJson() {
        NotificationBuilderImpl nbi = new NotificationBuilderImpl();
        Open open = new Open();
        open.setPosition(50);
        open.setState(Open.State.OPEN);
        String response = nbi.jaxbToJson(open);
        assertJsonRsponse(response);
        System.out.println(response);
    }

    @Override
    protected void assertXmlResponse(String response) {
        assertNotNull("No text returned!", response);

        assertResponseContains(response, "<?xml");
        assertResponseContains(response, "<open");
        assertResponseContains(response, "<position>50</position>");
        assertResponseContains(response, "</open>");
    }

    private void assertJsonRsponse(String response) {
        assertNotNull("No text returned!", response);

        assertResponseContains(response, "position");
        assertResponseContains(response, "50");
    }

    public class NotificationBuilderImpl extends NotificationBuilder {

        @Override
        public StringEntity jaxbToStringEntity(Client client) {
            return null;
        }

        @Override
        public boolean hasNotification() {
            return false;
        }
    }

}
