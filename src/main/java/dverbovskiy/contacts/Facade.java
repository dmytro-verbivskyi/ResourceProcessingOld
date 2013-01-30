package dverbovskiy.contacts;

/**
 * Facade for all GET/POST/PUT/DELETE request
 * User: dverbovskiy
 */

import dverbovskiy.util.DebugUtil;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("contacts")
public class Facade {
    private static final Logger log = Logger.getLogger(Facade.class);

    @GET
    @Produces("text/html")
    public String get(@Context UriInfo ui) {
        log.debug(DebugUtil.START);
        MultivaluedMap<String, String> params = ui.getQueryParameters();

        if (params.containsKey("list")) {

        }
        log.debug(DebugUtil.END);
        return ("get");
    }



    @PUT
    @Produces("text/html")
    public String put(/*@Context UriInfo ui*/) {
        log.debug(DebugUtil.START);
        log.debug(DebugUtil.END);
        return ("put");
    }

    @DELETE
    @Produces("text/html")
    public String delete(/*@Context UriInfo ui*/) {
        log.debug(DebugUtil.START);
        log.debug(DebugUtil.END);
        return ("delete");
    }
}
