/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;


/** Jersey REST client generated for REST resource:Genkey [genkey]<br>
 *  USAGE:<pre>
 *        LPLGenkeyClient client = new LPLGenkeyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 *  </pre>
 * @author ruppena
 */
public class LPLGenkeyClient {
    private WebResource webResource;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/SlowLPLServer/resources";

    public LPLGenkeyClient() {
        com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource(BASE_URI).path("genkey");
    }

    public String getNewKey() throws UniformInterfaceException {
        return webResource.accept(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void close() {
        client.destroy();
    }

}
