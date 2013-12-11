/**
 * Created by Apache CXF WadlToJava code generator
**/
package diuf.unifr.ch.first.xwot.resources;

import diuf.unifr.ch.first.xwot.jaxb.Client;
import diuf.unifr.ch.first.xwot.rxtx.notifications.NotificationFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/door/lock/pub")
public class LockContextResourcePublisherResource {
    
    @GET
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response getLockContextResourcePublisherResourceXML() {
        Client c = new Client();
        c.setUri("salut");
        return Response.ok(c).build();
    }

    @GET
    @Produces("text/html")
    public Response getLockContextResourcePublisherResourceHTML() {
        //TODO: implement
        return null;
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response postLockContextResourcePublisherResourceXML(Client client) {
        NotificationFactory.getInstance().getLockNotification().addClient(client);
        return Response.ok(client).build();
    }

}