/**
 * Created by Apache CXF WadlToJava code generator
**/
package diuf.unifr.ch.first.xwot.resources;

import diuf.unifr.ch.first.xwot.jaxb.Lock;
import diuf.unifr.ch.first.xwot.jaxb.State;
import diuf.unifr.ch.first.xwot.rxtx.components.ArduinoComponents;
import diuf.unifr.ch.first.xwot.rxtx.components.ContiniousServo;
import diuf.unifr.ch.first.xwot.rxtx.components.LinearPotentiometer;
import diuf.unifr.ch.first.xwot.rxtx.utils.RxtxUtils;
import java.net.URI;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.slf4j.LoggerFactory;

@Path("/door/lock")
public class LockContextResource {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LockContextResource.class);
    
    @GET
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response getLockContextResourceXML() {
        Lock lock = new Lock();
        RxtxUtils utils = new RxtxUtils();
        LinearPotentiometer lp = null;
        ContiniousServo cs = null;
        
        lp = utils.getComponent(LinearPotentiometer.class, ArduinoComponents.LOCK_SENSOR);
        cs = utils.getComponent(ContiniousServo.class, ArduinoComponents.LOCK_SERVO);
        
        if(lp == null || cs == null) {
            logger.error("json failure ? "+utils.getRawJson());
            return Response.status(503).entity("problem with hardware").build();
        }
        
        lock.setPosition(lp.getPosition());
        lock.setSpeed(cs.getSpeed());
        if(!lp.getState().equals("nil")) {
            lock.setState(State.fromValue(lp.getState()));
        } else if(!cs.getDirectrion().equals("nil")) {
            lock.setState(State.fromValue(cs.getDirectrion()));
        } else {
            lock.setState(State.UNKNOW);
        }
        lock.setUri(URI.create("/door/lock"));
        
        return Response.ok(lock).build();
    }

    @GET
    @Produces("text/html")
    public Response getLockContextResourceHTML() {
        //TODO: implement
        return null;
    }

    @PUT
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response putLockContextResourceXML(Lock lock) {
        RxtxUtils utils = new RxtxUtils();
        ContiniousServo cs = utils.getComponent(ContiniousServo.class, ArduinoComponents.LOCK_SERVO);
        if(lock.getSpeed() != null) {
            cs.setSpeed(lock.getSpeed());
        }
        if(lock.getState().compareTo(State.OPENING) == 0 || lock.getState().compareTo(State.CLOSING) == 0) {
            cs.setDirectrion(lock.getState().value());
        }
        utils.setComponent(ArduinoComponents.LOCK_SERVO, cs);
        
        return Response.ok().build();
    }

}