/**
 * Created by Apache CXF WadlToJava code generator
**/
package diuf.unifr.ch.first.xwot.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/door/open/pub")
public class OpenContextResourcePublisherResource {

    @GET
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response getOpenContextResourcePublisherResourceXML() {
        //TODO: implement
        return null;
    }

    @GET
    @Produces("text/html")
    public Response getOpenContextResourcePublisherResourceHTML() {
        //TODO: implement
        return null;
    }

    @POST
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response postOpenContextResourcePublisherResourceXML() {
        //TODO: implement
        return null;
    }

}