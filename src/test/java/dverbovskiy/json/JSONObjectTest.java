package dverbovskiy.json;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.assertEquals;

/**
 * User: dverbovskiy
 * Date: 24.03.13
 * Time: 18:56
 */
public class JSONObjectTest {
    @Rule
    public TestName name = new TestName();

    @Test
    public void testGetInstance_Empty() throws Exception {
        JSONObject json = JSONObject.getInstance();
        assertEquals("{}", json.toString());
    }

    @Test
    public void testGetInstance_MinimalObject() throws Exception {
        JSONObject json = JSONObject.getInstance("{}");
        assertEquals("{}", json.toString());
    }

    @Test
    public void testPut_Simple() throws Exception {
        JSONObject json = JSONObject.getInstance();

        json.put("a.b.4.6.d.5", 12);


        assertEquals("{}", json.toString());
    }
}
