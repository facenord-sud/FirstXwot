/**
 * Created by Apache CXF WadlToJava code generator
**/
package diuf.unifr.ch.first.xwot.resources;

import diuf.unifr.ch.first.xwot.jaxb.Client;
import diuf.unifr.ch.first.xwot.notifications.NotificationFactory;
import diuf.unifr.ch.first.xwot.rxtx.RxtxConnection;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import org.slf4j.LoggerFactory;

@Path("/door/open/pub")
public class OpenContextResourcePublisherResource {

    private final String uriPattern = "/{uri: [a-zA-Z0-9_.:\\-/]+}";
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RxtxConnection.class);

    @GET
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response getOpenClientsResourceXML() {
        List<Client> clients = new ArrayList<Client>() {
        };
        for (Object o : NotificationFactory.getInstance().getOpenNotification().
                getClients()) {
            clients.add((Client) o);
        }
        final GenericEntity<List<Client>> list = new GenericEntity<List<Client>>(clients) {
        };
        return Response.ok(list).build();
    }

    @PUT
    @Path(uriPattern)
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response putOpenClientResourceXML(Client client, @PathParam("uri") String uri) {
        client.setUri(uri);
        NotificationFactory.getInstance().getOpenNotification().addClient(client
                .getUri(), client);
        return Response.ok(client).build();
    }
    
    @GET
    @Path(uriPattern)
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response getOpenClientResourceXML(@PathParam("uri") String uri) {
        Client c = NotificationFactory.getInstance().getOpenNotification().
                getClient(uri);
        return Response.ok(c).build();
    }
    
    @DELETE
    @Path(uriPattern)
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response deleteOpenClientResourceXML(@PathParam("uri") String uri) {
        logger.debug("THE uri params is: " + uri);
        Client c = NotificationFactory.getInstance().getOpenNotification().
                removeClient(uri);
        if(c != null)logger.debug("The client: " + c.getUri());
        return Response.ok(c).build();
    }

}