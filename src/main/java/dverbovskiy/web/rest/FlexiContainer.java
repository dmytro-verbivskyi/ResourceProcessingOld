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
    private String containerValue;
    private String optionsValue;

    public static FlexiContainer tryParse(String input) throws Exception {
        JSONParser parser = new JSONParser();
        FlexiContainer flexiContainer = new FlexiContainer();

        JSONObject jsonObject = (JSONObject) parser.parse(input);

        flexiContainer.setCmd(jsonObject);
        flexiContainer.setContainer(jsonObject);
        flexiContainer.setOptions(jsonObject);
        return flexiContainer;
    }

    public String getCmd() {
        return cmdValue;
    }

    public void setCmd(JSONObject jsonObject) throws Exception {
        this.cmdValue = tryGetAndParse(jsonObject, CMD);
    }

    public String getContainer() {
        return containerValue;
    }

    public void setContainer(JSONObject jsonObject) throws Exception {
        this.containerValue = tryGetAndParse(jsonObject, CONTAINER);
    }

    public String getOptions() {
        return optionsValue;
    }

    public void setOptions(JSONObject jsonObject) throws Exception {
        this.optionsValue = tryGetAndParse(jsonObject, OPTIONS);
    }

    private static String tryGetAndParse(JSONObject jsonObject, String label) throws Exception {
        String temp = "";

        if (!(CMD.equals(label) || CONTAINER.equals(label) || OPTIONS.equals(label))) {
            throw new Exception("\"" + label + "\" is unknown parameter");
        }

        if (jsonObject.containsKey(label)) {
            try {
                temp = (String) jsonObject.get(label);
            } catch (ClassCastException ex) {
                // empty json statements goes here
            }
            log.debug("\"" + label + "\":" + temp);
        } else {
            throw new Exception("\"" + label + "\" parameter is mandatory, even if it is empty");
        }
        return temp;
    }
}
