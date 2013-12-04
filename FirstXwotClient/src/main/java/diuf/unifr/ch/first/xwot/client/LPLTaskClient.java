/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/** Jersey REST client generated for REST resource:TaskResource<br>
 *  USAGE:<pre>
 *        LPLTaskClient client = new LPLTaskClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 *  </pre>
 * @author ruppena
 */
public class LPLTaskClient {
    private WebResource webResource;
    private Client client;
    //private static final String BASE_URI = "http://localhost:8080/SlowLPLServer/work";
    //private static final String BASE_URI = "http://diufvm06.unifr.ch:8080/SlowLPLServer/work";
    //private static final String BASE_URI = "${lpl.RestServer}/SlowLPLServer/work";
    private static String BASE_URI = null;
    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String AUTHENTICATION_STRING = "Basic cnVwcGVuYTpoZWxsbw==";

    public LPLTaskClient(String id) {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("server.properties"));
        } catch (IOException ex) {
            Logger.getLogger(LPLTasksClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        BASE_URI = properties.getProperty("lpl.restserver")+"/SlowLPLServer/work";
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        String resourcePath = java.text.MessageFormat.format("tasks/{0}", new Object[]{id});
        webResource = client.resource(BASE_URI).path(resourcePath);
    }

    public void setResourcePath(String id) {
        String resourcePath = java.text.MessageFormat.format("tasks/{0}", new Object[]{id});
        webResource = client.resource(BASE_URI).path(resourcePath);
    }

    public <T> T getXml(Class<T> responseType) throws UniformInterfaceException {
        return webResource.accept(javax.ws.rs.core.MediaType.APPLICATION_XML).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).get(responseType);
    }

    public String delete() throws UniformInterfaceException {
        return webResource.header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).delete(String.class);
    }

    public ClientResponse deleteHTML() throws UniformInterfaceException {
        return webResource.header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).delete(ClientResponse.class);
    }

    public <T> T deleteXML(Class<T> responseType) throws UniformInterfaceException {
        return webResource.header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).delete(responseType);
    }

    public void close() {
        client.destroy();
    }

}
