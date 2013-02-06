package dverbovskiy.web.rest;

import dverbovskiy.util.Util;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import javax.ws.rs.Path;
import java.util.ArrayList;

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

        Object o6 = container.write("data.array.8", 14);

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
        Object parent = this;

        String[] pathParts = fullPath.split("\\.");

        for (String path : pathParts) {
            boolean isInt = Util.isInteger(path);
            // TODO if (path == "-2")

            if (isInt) {
                int index = Integer.parseInt(path);

                if (parent instanceof JSONArray) {
                    if (((JSONArray) parent).contains(index)) {
                        // going deeper
                        parent = ((JSONObject) parent).get(index);
                    } else {
                        // adding element for parent[index]
                        int i = ((JSONArray) parent).size();

                        if (i < index) {
                            log.warn("Not proper order of adding values. It's not effective. Size: " + i + "; index : " + index);
                        }
                        for (; i < index; i++) {
                            ((JSONArray) parent).add(new JSONObject()); // adding empty object
                        }
                        ((JSONArray) parent).add(index, value);
                    }
                }
            } else {
                parent = ((JSONObject) parent).get(path);
            }

        }

        return new Object();

        /*fullPath = fullPath.toLowerCase();

        int lastSeparator = fullPath.lastIndexOf(".");
        if (lastSeparator == -1) {
            return super.put(fullPath, value);
        }

        String parentPath = fullPath.substring(0, lastSeparator);
        String nodeName = fullPath.substring(lastSeparator + 1);

        JSONObject parent = this;
        try {
            String[] path = fullPath.split("\\.");

            for (int i = 0; i < path.length; i++) {
                if (parent.containsKey(path[i])) {
                    parent = (JSONObject) parent.get(path[i]);
                } else {
                    if (i + 1 < path.length) {
                        int index = -1;

                        try {
                            index = Integer.parseInt(path[i + 1]);
                        } catch (NumberFormatException nfe) {
                            // okey then next part is not JSONArray
                        }
                    }
                }
            }


            //parent = read(parentPath);
        } finally { // if anything went wrong parent will be null
            if (parent == null) {
                throw new Exception("Parent location [" + parentPath + "] doesn't exist");
            }
        }
        //TODO parent instanceof JSONArray
        //TODO parent contains value of int
        Object r = ((JSONObject) parent).put(nodeName, value);
        return r;*/

        //TODO such agile logic for writing
        /*oo.AddStory( {backlog: { id: 12312 },
                 storyItem: {
                    external: {
                      id: {value: '23423423', a:'23'}
                    },
                    title: 'wewerwe'
                 },
                 position: { after: {id:'23423'}
                 }
               });
        // true JSON
        {
           "backlog":{
              "id":12312
           },
           "storyItem":{
              "external":{
                 "id":{
                    "value":"23423423",
                    "a":23
                 }
              },
              "title":"wewerwe"
           },
           "position":{
              "after":{
                 "id":23423
              }
           }
        }

          obj.write("backlog.id", 1341324);
          obj.write("storyItem.external.id.value", '23423423');
          obj.write("storyItem.external.2.id.a", '23');
          obj.write("storyItem.title", '234234234');
          obj.write("position.after.id", '23423');
        */

    }

}





















