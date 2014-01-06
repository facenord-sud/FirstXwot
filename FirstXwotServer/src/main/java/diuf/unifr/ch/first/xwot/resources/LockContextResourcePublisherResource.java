/**
 * Created by Apache CXF WadlToJava code generator
*
 */
package diuf.unifr.ch.first.xwot.resources;

import diuf.unifr.ch.first.xwot.jaxb.Client;
import diuf.unifr.ch.first.xwot.notifications.NotificationFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

@Path("/door/lock/pub")
public class LockContextResourcePublisherResource{

    @GET
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response getLockContextResourcePublisherResourceXML() {
        List<Client> clients = new ArrayList<Client>() {
        };
        for (Object o : NotificationFactory.getInstance().getLockNotification().getClients()) {
            clients.add((Client) o);
        }
        final GenericEntity<List<Client>> list = new GenericEntity<List<Client>>(clients) {
        };
        return Response.ok(list).build();
    }

    @PUT
    @Path("/{uri: [a-zA-Z0-9_.:\\-/]+}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response putLockContextResourcePublisherResourceXML(Client client, @PathParam("uri") String uri) {
        client.setUri(uri);
        NotificationFactory.getInstance().getLockNotification().addClient(client.getUri(), client);
        return Response.ok(client).build();
    }

}
