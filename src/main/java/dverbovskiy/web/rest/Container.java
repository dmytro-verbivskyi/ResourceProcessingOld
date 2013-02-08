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

        Object o6 = container.write("data.array.4", 14);

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
        Object parent = this;

        String[] pathParts = fullPath.toLowerCase().split("\\.");
        int limit = pathParts.length - 1;

        for (int i = 0; i < limit; i++) {
            String path = pathParts[i];
            boolean isInt = Util.isInteger(path);

            if (parent instanceof JSONArray) {
                JSONArray node = (JSONArray) parent;
                int index = Integer.parseInt(path);

                if (index < 0) {
                    log.warn("Negative array index:" + index + ". It's not effective.");
                    index = Math.abs(index);
                }

                if (isInt) {
                    if (!node.contains(index)) {
                        // adding elements for parent[index]
                        int size = node.size();

                        if (size < index) {
                            log.warn("Not proper order of adding values. It's not effective. Current max index: " + (size - 1) + "; index : " + index);
                        }
                        for (; size <= index; size++) {
                            node.add(new JSONObject()); // adding empty object to array
                        }
                    }
                    parent = node.get(index);       // going deeper
                } else {
                    // implying that user wants to work with array[0] element
                    if (!node.contains(0)) {
                        // TODO: verify this case
                        node.add(0, new JSONObject());
                    }
                    JSONObject element = (JSONObject) node.get(0);

                    if (!element.containsKey(path)) {
                        // TODO: verify this case
                        element.put(path, new JSONObject()); // adding empty object to array[0] element
                    }
                    parent = element.get(index);    // going deeper
                }
            } else if (parent instanceof JSONObject) {
                JSONObject node = (JSONObject) parent;

                parent = node.get(path);       // going deeper
            } else {
                // TODO: verify this case
                throw new Exception("Unknown type of parent object: " + parent.getClass());
            }
        }
        // TODO here and now, we can add value, and return previous value
        return new Object();

        // TODO such agile logic for writing
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





















