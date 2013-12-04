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
public class NotFoundException extends WebApplicationException{
    
    private static final long serialVersionUID = 1L;

    public NotFoundException()
    {
        this("Could not find the desired resource");
    }

    public NotFoundException(String _message)
    {
        super(Response.status(Response.Status.NOT_FOUND).entity(_message).build());
    }

}
