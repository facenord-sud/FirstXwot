/**
 * Created by Apache CXF WadlToJava code generator
 *
 */
package diuf.unifr.ch.first.xwot.resources;

import diuf.unifr.ch.first.xwot.jaxb.Lock;
import diuf.unifr.ch.first.xwot.rxtx.components.ArduinoComponents;
import diuf.unifr.ch.first.xwot.rxtx.components.ContiniousServo;
import diuf.unifr.ch.first.xwot.rxtx.components.LinearPotentiometer;
import diuf.unifr.ch.first.xwot.rxtx.utils.RxtxUtils;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/door/lock")
public class LockContextResource {

    /**
     * Parse arduino infos and return an Object Lock
     *
     * @return Response with a lock object encoded as the user wich
     */
    @GET
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response getLockContextResourceXML() {
        RxtxUtils utils = new RxtxUtils();
        LinearPotentiometer lp = utils.getComponent(LinearPotentiometer.class, ArduinoComponents.LOCK_SENSOR);
        if(lp == null) {
            return Response.status(503).entity("try to relaod").build();
        }
        Lock lock = new Lock();
        if (lp.getPosition() <= LinearPotentiometer.MIDDLE_POSITION) {
            lock.setState(Lock.State.OPEN);
        } else {
            lock.setState(Lock.State.CLOSED);
        }
        return Response.ok(lock).build();
    }

    /**
     * The user has only one manner to manipulate the lock of the door.<br/>
     * He can give the state <code>CLOSED</code>, in this case, the door will be
     * locked. Or he can give the state <code>OPEN</code> and the door will be
     * unlocked
     *
     * @param lock
     * @return HTTP 200 ok
     */
    @PUT
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response putLockContextResourceXML(Lock lock) {
        RxtxUtils utils = new RxtxUtils();
        ContiniousServo cs = new ContiniousServo();
        LinearPotentiometer lp = new LinearPotentiometer();
        if (lock.getState() == Lock.State.OPEN) {
            lp.setPosition(LinearPotentiometer.OPEN_POSITION);
            cs.setSpeed(ContiniousServo.OPEN_MAX_SPEED);
        } else {
            lp.setPosition(LinearPotentiometer.CLOSED_POSITION);
            cs.setSpeed(ContiniousServo.CLOSE_MAX_SPEED);
        }
        utils.addComponent(ArduinoComponents.LOCK_SERVO, cs);
        utils.addComponent(ArduinoComponents.LOCK_SENSOR, lp);
        utils.send();
        return Response.ok().build();
    }

}
