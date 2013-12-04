/**
 * Created by Apache CXF WadlToJava code generator
**/
package diuf.unifr.ch.first.xwot.resources;

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
        //TODO: implement
        return null;
    }

    @GET
    @Produces("text/html")
    public Response getLockContextResourcePublisherResourceHTML() {
        //TODO: implement
        return null;
    }

    @POST
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response postLockContextResourcePublisherResourceXML() {
        //TODO: implement
        return null;
    }

}