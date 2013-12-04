/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;
import org.netbeans.saas.RestResponse;


/** Jersey REST client generated for REST resource:TasksResource [tasks]<br>
 *  USAGE:<pre>
 *        LPLTasksClient client = new LPLTasksClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 *  </pre>
 * @author ruppena
 */
public class LPLTasksClient {
    private static WebResource webResource;
    private Client client;
    //private static final String BASE_URI = "http://localhost:8080/SlowLPLServer/work";
    //private static final String BASE_URI = "http://diufvm06.unifr.ch:8080/SlowLPLServer/work";
    private static  String BASE_URI = null;//"${lpl.RestServer}/SlowLPLServer/work";
    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String AUTHENTICATION_STRING = "Basic cnVwcGVuYTpoZWxsbw==";

    public LPLTasksClient() {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("server.properties"));
        } catch (IOException ex) {
            Logger.getLogger(LPLTasksClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        BASE_URI = properties.getProperty("lpl.restserver")+"/SlowLPLServer/work";
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("tasks");
    }

    public ClientResponse createTaskText(Object requestEntity) throws UniformInterfaceException {
        return webResource.type(javax.ws.rs.core.MediaType.TEXT_PLAIN).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).post(ClientResponse.class, requestEntity);
    }

    public <T> T getTasksHTML(Class<T> responseType, int start, int size) throws UniformInterfaceException {
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("start", Integer.toString(start));
        queryParams.add("size", Integer.toString(size));
        return webResource.queryParams(queryParams).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).accept(javax.ws.rs.core.MediaType.TEXT_HTML).get(responseType);
    }

    public <T> T getTasksXML_XML(Class<T> responseType, int start, int size) throws UniformInterfaceException {
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("start", Integer.toString(start));
        queryParams.add("size", Integer.toString(size));
        return webResource.queryParams(queryParams).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).accept(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getTasksXML_JSON(Class<T> responseType, int start, int size) throws UniformInterfaceException {
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("start", Integer.toString(start));
        queryParams.add("size", Integer.toString(size));
        return webResource.queryParams(queryParams).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getTasksXML_TEXT_XML(Class<T> responseType, int start, int size) throws UniformInterfaceException {
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("start", Integer.toString(start));
        queryParams.add("size", Integer.toString(size));
        return webResource.queryParams(queryParams).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).accept(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
    }

    public String getTasksText(int start, int size) throws UniformInterfaceException {
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("start", Integer.toString(start));
        queryParams.add("size", Integer.toString(size));
        return webResource.queryParams(queryParams).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).accept(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public static RestResponse getTasksList(int start, int size) throws IOException
    {
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("start", Integer.toString(start));
        queryParams.add("size", Integer.toString(size));
        String result = webResource.queryParams(queryParams).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).accept(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
        return new RestResponse(result.getBytes());
    }

    public ClientResponse createTaskXML_XML(Object requestEntity) throws UniformInterfaceException {
        return webResource.type(javax.ws.rs.core.MediaType.APPLICATION_XML).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).post(ClientResponse.class, requestEntity);
    }

    public ClientResponse createTaskXML_JSON(Object requestEntity) throws UniformInterfaceException {
        return webResource.type(javax.ws.rs.core.MediaType.APPLICATION_JSON).header(AUTHENTICATION_HEADER, AUTHENTICATION_STRING).post(ClientResponse.class, requestEntity);
    }

    public void close() {
        client.destroy();
    }

}
