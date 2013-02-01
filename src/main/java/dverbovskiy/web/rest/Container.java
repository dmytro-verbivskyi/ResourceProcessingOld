package dverbovskiy.web.rest;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This container is the highest level of processing json data containers
 */
public class Container extends JSONObject {
    public static final String CMD = "cmd";
    public static final String DATA = "data";
    public static final String OPTIONS = "options";
    private static final Logger log = Logger.getLogger(Container.class);

    private Container() {
        super();
    }

    public static Container getInstance(String input) throws Exception {
        JSONParser parser = new JSONParser();
        Container container = new Container();

        JSONObject jsonObject = (JSONObject) parser.parse(input);

        container.put(CMD, getMandatory(jsonObject, CMD));
        container.put(DATA, getMandatory(jsonObject, DATA));
        container.put(OPTIONS, getMandatory(jsonObject, OPTIONS));

        Object o1 = container.get("cmd");
        //Object o3 = container.getByPath("data.id");
        //Object o4 = container.getByPath("data.idy.requestId");

        return container;
    }

    private static Object getMandatory(JSONObject jsonObject, String label) throws Exception {
        Object buffer;

        if (!(CMD.equals(label) || DATA.equals(label) || OPTIONS.equals(label))) {
            throw new Exception("\"" + label + "\" is unknown parameter");
        }

        if (jsonObject.containsKey(label)) {
            buffer = jsonObject.get(label);
        } else {
            throw new Exception("\"" + label + "\" parameter is mandatory, even if it is empty");
        }
        return buffer;
    }

    @Override
    public Object get(Object key) {
        return super.get(key);    //To change body of overridden methods use File | Settings | File Templates.
    }


/*    @Override
    public Object get(String fullPath) {
        Object goal = this;
        log.debug("fullPath: " + fullPath);

        for (String path : fullPath.split("\\.")) {
            if (goal == null) {
                break;
            }
            goal = ((JSONObject) goal).get(path);
        }
        return goal;
    }*/
}
