/**
 * Created by Apache CXF WadlToJava code generator
**/
package diuf.unifr.ch.first.xwot.resources;

import diuf.unifr.ch.first.xwot.jaxb.Door;
import diuf.unifr.ch.first.xwot.jaxb.Lock;
import diuf.unifr.ch.first.xwot.jaxb.Open;
import java.net.URI;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/door")

public class DoorDeviceResource {

    @Context UriInfo uri;
    @GET
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response getDoorDeviceResourceXML() {
        Door door = new Door();
        Door.ListOfDevices devices = new Door.ListOfDevices();
        
        Lock lock = new Lock();
        lock.setUri(URI.create("/FirstXwotServer/resources/door/lock"));
        
        Open open = new Open();
        open.setUri(URI.create("/FirstXwotServer/resources/door/open"));
        
        devices.setLock(lock);
        devices.setOpen(open);
        door.setListOfDevices(devices);
        
        return Response.ok(door).build();
    }

    @GET
    @Produces("text/html")
    public Response getDoorDeviceResourceHTML() {
        //TODO: implement
        return null;
    }

}