/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package diuf.unifr.ch.first.xwot.resources.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

/**
 *
 * @author <a href="mailto:andreas.ruppen@gmail.com">Andreas Ruppen</a>
 */
public class UnauthorizedException  extends WebApplicationException{
    
    private static final long serialVersionUID = 1L;

    public UnauthorizedException()
    {
        this("Wrong Role.", "userauthdb");
    }

    public UnauthorizedException(String _message, String _realm)
    {
        super(Response.status(Status.UNAUTHORIZED).header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"" + _realm + "\"").entity(_message).build());
    }

}
