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
    private String cmdValue;
    private JSONObject dataValue;
    private JSONObject optionsValue;

    private Container(){
        super();
    }

    public static Container tryParse(String input) throws Exception {
        JSONParser parser = new JSONParser();
        Container container = new Container();

        JSONObject jsonObject = (JSONObject) parser.parse(input);

        container.setCmd(jsonObject);
        log.debug("\"" + CMD + "\": " + container.getCmd());
        container.setData(jsonObject);
        log.debug("\"" + DATA + "\": " + container.getData().toJSONString());
        container.setOptions(jsonObject);
        log.debug("\"" + OPTIONS + "\": " + container.getOptions().toJSONString());
        return container;
    }

    public String getCmd() {
        return cmdValue;
    }

    public void setCmd(JSONObject jsonObject) throws Exception {
        this.cmdValue = (String) tryGetAndParse(jsonObject, CMD);
    }

    public JSONObject getData() {
        return dataValue;
    }

    public void setData(JSONObject jsonObject) throws Exception {
        this.dataValue = (JSONObject) tryGetAndParse(jsonObject, DATA);
    }

    public JSONObject getOptions() {
        return optionsValue;
    }

    public void setOptions(JSONObject jsonObject) throws Exception {
        this.optionsValue = (JSONObject) tryGetAndParse(jsonObject, OPTIONS);
    }

    private static Object tryGetAndParse(JSONObject jsonObject, String label) throws Exception {
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
}
