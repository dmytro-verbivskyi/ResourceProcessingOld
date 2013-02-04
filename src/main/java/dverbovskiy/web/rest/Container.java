package dverbovskiy.web.rest;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.lang.reflect.Array;

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

        /*Object o1 = container.read("CMD");
        Object o3 = container.read("data.id");
        Object o4 = container.read("data.ID.requestId");
        Object o5 = container.read("data.array.3.1");*/

        Object o6 = container.write("data.array.12.lol.2", 14);

        //o6 = container.write("data.ID", 98);

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

    public Object read(String fullPath) {
        Object node = this;
        fullPath = fullPath.toLowerCase();
        log.debug("fullPath: " + fullPath);

        for (String path : fullPath.split("\\.")) {
            if (node == null) {
                break;
            }

            if (node instanceof JSONArray) {
                node = ((JSONArray) node).get(Integer.parseInt(path));
            } else {
                node = ((JSONObject) node).get(path);
            }
        }
        return node;
    }


    public Object write(String fullPath, Object value) throws Exception {
        fullPath = fullPath.toLowerCase();

        int lastSeparator = fullPath.lastIndexOf(".");
        if (lastSeparator == -1) {
            return super.put(fullPath, value);
        }

        String parentPath = fullPath.substring(0, lastSeparator);
        String nodeName = fullPath.substring(lastSeparator + 1);

        Object parent = null;
        try {
            parent = read(parentPath);
        } finally { // if anything went wrong parent will be null
            if (parent == null) {
                throw new Exception("Parent location [" + parentPath + "] doesn't exist");
            }
        }
        //TODO parent instanceof JSONArray
        //TODO parent contains value of int
        Object r = ((JSONObject) parent).put(nodeName, value);
        return r;
    }
}





















