/**
 * Created by Apache CXF WadlToJava code generator
 *
 */
package diuf.unifr.ch.first.xwot.resources;

import diuf.unifr.ch.first.xwot.jaxb.Open;
import diuf.unifr.ch.first.xwot.rxtx.RxtxConnection;
import diuf.unifr.ch.first.xwot.components.ArduinoComponents;
import diuf.unifr.ch.first.xwot.components.ContiniousServo;
import diuf.unifr.ch.first.xwot.components.LinearPotentiometer;
import diuf.unifr.ch.first.xwot.mapper.OpenMapper;
import diuf.unifr.ch.first.xwot.rxtx.utils.RxtxUtils;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.slf4j.LoggerFactory;

@Path("/door/open")
public class OpenContextResource {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RxtxConnection.class);

    /**
     * Parse les informations renvoyés par l'ardiuno et retourne un objet Open
     *
     * @return
     */
    @GET
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response getOpenContextResourceXML() {
        RxtxUtils utils = new RxtxUtils();
        LinearPotentiometer lp = utils.getComponent(LinearPotentiometer.class, ArduinoComponents.OPEN_SENSOR);
        if (lp == null) {
            return Response.status(503).entity("try to relaod").build();
        }
        Open open = new OpenMapper(lp).map();
        logger.debug(lp.toString());
        return Response.ok(open).build();
    }

    /**
     * il y a deux possibilité de commander une porte:
     * <ol>
     * <li>indiquer uniquement une position. Dans ce cas, le serveur décide si
     * il faut fermer ou ouvrir la porte en fonction de la positon actuelle de
     * la porte. La porte s'ouvrira ou se fermera jusqu'à avoir atteint la
     * position indiquée</li>
     * <li>Indiquer l'état <code>OPEN</code> ou <code>CLOSED</code>. Dans ce
     * cas, la porte se fermera ou s'ouvira totalement.</li>
     * </ol>
     *
     * @param open
     * @return HTTP 200 ok
     */
    @PUT
    @Produces({"application/xml", "application/json", "text/xml"})
    public Response putOpenContextResourceXML(Open open) {
        RxtxUtils utils = new RxtxUtils();
        ContiniousServo cs = new ContiniousServo();
        LinearPotentiometer lp = utils.getComponent(LinearPotentiometer.class, ArduinoComponents.OPEN_SENSOR);

        // l'utilisateur indique une position
        if (open.getState() == null) {
            int actualPosition = lp.getPercentPosition();
            lp.setOldPosition(lp.getPosition());
            lp.setFromPercentPosition(open.getPosition());
            if (actualPosition < open.getPosition()) {
                cs.setSpeed(ContiniousServo.CLOSE_MAX_SPEED);
            } else if (actualPosition > open.getPosition()) {
                cs.setSpeed(ContiniousServo.OPEN_MAX_SPEED);
            } else {
                cs.setSpeed(ContiniousServo.NULL_SPEED);
            }
        } // l'utilisateur indique un état
        else {
            if (open.getState() == Open.State.OPEN) {
                lp.setPosition(LinearPotentiometer.CLOSED_POSITION);
                cs.setSpeed(ContiniousServo.CLOSE_MAX_SPEED);
                logger.debug("open the door");
            } else {
                lp.setPosition(LinearPotentiometer.OPEN_POSITION);
                cs.setSpeed(ContiniousServo.OPEN_MAX_SPEED);
                logger.debug("close the door");
            }
        }
        utils.addComponent(ArduinoComponents.OPEN_SERVO, cs);
        utils.addComponent(ArduinoComponents.OPEN_SENSOR, lp);
        utils.send();
        return Response.ok().build();
    }

}
