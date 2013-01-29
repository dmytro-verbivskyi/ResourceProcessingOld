package dverbovskiy.contacts;

/**
 * Facade for all GET/POST/PUT/DELETE request
 * User: dverbovskiy
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("contacts")
public class Facade {
    @GET
    @Produces("text/html")
    public String get(@Context UriInfo ui) {
        MultivaluedMap<String, String> params = ui.getQueryParameters();

        if (params.containsKey("list")) {

        }
        return ("get");
    }
}
