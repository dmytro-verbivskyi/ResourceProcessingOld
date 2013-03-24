package dverbovskiy.json;

import org.json.simple.parser.JSONParser;

/**
 * User: dverbovskiy
 * Date: 24.03.13
 * Time: 18:45
 */
public class JSONObject {
    private org.json.simple.JSONObject box = new org.json.simple.JSONObject();

    private JSONObject() {
        super();
    }

    public static JSONObject getInstance(String input) throws Exception {
        JSONParser parser = new JSONParser();
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(input);

        JSONObject jO = new JSONObject();
        jO.box = jsonObject;
        return jO;
    }

    public static JSONObject getInstance() throws Exception {
        return new JSONObject();
    }

    @Override
    public String toString() {
        return box.toJSONString();
    }

    @Override
    public int hashCode() {
        return box.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JSONObject)) return false;

        JSONObject that = (JSONObject) o;
        return box.equals(that.box);
    }


    public Object put(String fullPath, Object value) throws Exception {
        Object grandParent = this.box;
        Object parent = this.box;
        Object previousValue = null;

        JSONPath path = new JSONPath(fullPath);

        for (int i = 0; i < path.length(); i++) {
             if (path.getType(i) == JSONPathType.ARRAY) {

             }
        }

        return null;
    }


}


