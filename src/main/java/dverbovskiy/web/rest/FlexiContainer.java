package dverbovskiy.web.rest;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This container is the highest level of processing json data containers
 */
public class FlexiContainer {
    public static final String CMD = "cmd";
    public static final String CONTAINER = "container";
    public static final String OPTIONS = "options";
    private static final Logger log = Logger.getLogger(FlexiContainer.class);
    private String cmdValue;
    private JSONObject containerValue;
    private JSONObject optionsValue;

    public static FlexiContainer tryParse(String input) throws Exception {
        JSONParser parser = new JSONParser();
        FlexiContainer flexiContainer = new FlexiContainer();

        JSONObject jsonObject = (JSONObject) parser.parse(input);

        flexiContainer.setCmd(jsonObject);
        log.debug("\"" + CMD + "\": " + flexiContainer.getCmd());
        flexiContainer.setContainer(jsonObject);
        log.debug("\"" + CONTAINER + "\": " + flexiContainer.getContainer().toJSONString());
        flexiContainer.setOptions(jsonObject);
        log.debug("\"" + OPTIONS + "\": " + flexiContainer.getOptions().toJSONString());
        return flexiContainer;
    }

    public String getCmd() {
        return cmdValue;
    }

    public void setCmd(JSONObject jsonObject) throws Exception {
        this.cmdValue = (String) tryGetAndParse(jsonObject, CMD);
    }

    public JSONObject getContainer() {
        return containerValue;
    }

    public void setContainer(JSONObject jsonObject) throws Exception {
        this.containerValue = (JSONObject) tryGetAndParse(jsonObject, CONTAINER);
    }

    public JSONObject getOptions() {
        return optionsValue;
    }

    public void setOptions(JSONObject jsonObject) throws Exception {
        this.optionsValue = (JSONObject) tryGetAndParse(jsonObject, OPTIONS);
    }

    private static Object tryGetAndParse(JSONObject jsonObject, String label) throws Exception {
        Object buffer;

        if (!(CMD.equals(label) || CONTAINER.equals(label) || OPTIONS.equals(label))) {
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
