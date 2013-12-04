/**
 * Created by Apache CXF WadlToJava code generator
**/
package diuf.unifr.ch.first.xwot.resources;

import diuf.unifr.ch.first.xwot.jaxb.Lock;
import diuf.unifr.ch.first.xwot.jaxb.Open;
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

@Path("/door/open")
public class OpenContextResource {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OpenContextResource.class);

    @GET
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response getOpenContextResourceXML() {
        Open open = new Open();
        RxtxUtils utils = new RxtxUtils();
        LinearPotentiometer lp = null;
        ContiniousServo cs = null;
        
        lp = utils.getComponent(LinearPotentiometer.class, ArduinoComponents.OPEN_SENSOR);
        cs = utils.getComponent(ContiniousServo.class, ArduinoComponents.OPEN_SERVO);
        
        if(lp == null || cs == null) {
            logger.error("json failure ? "+utils.getRawJson());
            return Response.status(503).entity("problem with hardware").build();
        }
        
        open.setPosition(lp.getPosition());
        open.setSpeed(cs.getSpeed());
        if(!lp.getState().equals("nil")) {
            open.setState(State.fromValue(lp.getState()));
        } else if(cs.getDirectrion() != null && !cs.getDirectrion().equals("nil")) {
            open.setState(State.fromValue(cs.getDirectrion()));
        } else {
            open.setState(State.UNKNOW);
        }
        open.setUri(URI.create("/door/open"));
        
        return Response.ok(open).build();
    }

    @GET
    @Produces("text/html")
    public Response getOpenContextResourceHTML() {
        //TODO: implement
        return null;
    }

    @PUT
    @Produces({"application/xml", "application/json", "text/xml" })
    public Response putOpenContextResourceXML(Open open) {
        RxtxUtils utils = new RxtxUtils();
        ContiniousServo cs = utils.getComponent(ContiniousServo.class, ArduinoComponents.OPEN_SERVO);
        if(open.getSpeed() != null) {
            cs.setSpeed(open.getSpeed());
        }
        if(open.getState().compareTo(State.OPENING) == 0 || open.getState().compareTo(State.CLOSING) == 0) {
            cs.setDirectrion(open.getState().value());
        }
        utils.setComponent(ArduinoComponents.OPEN_SERVO, cs);
        
        return Response.ok().build();
    }

}