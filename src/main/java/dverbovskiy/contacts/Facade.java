package dverbovskiy.contacts;

/**
 * Facade for all GET/POST/PUT/DELETE request
 * User: dverbovskiy
 */

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("contacts")
public class Facade {
    private static final Logger log = Logger.getLogger(Facade.class);

    @GET
    @Produces("text/html")
    public String get(@Context UriInfo ui) {
        log.debug("get has started");
        MultivaluedMap<String, String> params = ui.getQueryParameters();

        if (params.containsKey("list")) {

        }
        log.debug("get has ended");
        return ("get");
    }
}
