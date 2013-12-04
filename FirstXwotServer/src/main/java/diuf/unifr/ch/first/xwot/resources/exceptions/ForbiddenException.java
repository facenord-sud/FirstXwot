/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diuf.unifr.ch.first.xwot.resources.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 *
 * @author <a href="mailto:andreas.ruppen@gmail.com">Andreas Ruppen</a>
 */
public class ForbiddenException extends WebApplicationException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        this("Please authenticate.", "userauthdb");
    }

    public ForbiddenException(String _message, String _realm) {
        super(Response.status(Response.Status.FORBIDDEN).header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"" + _realm + "\"").entity(_message).build());
    }
}
