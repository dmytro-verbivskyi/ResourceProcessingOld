package dverbovskiy.web.rest;

import dverbovskiy.util.DebugUtil;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TestName;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ContainerTest {
    @Rule
    public TestName name = new TestName();
    private static String emptyContainer = "{\"cmd\":\"\",\"data\":{},\"options\":{}}";

    @Before
    public void setUp() throws Exception {
        System.out.println(DebugUtil.start(name.getMethodName()));
    }

    @After
    public void tearDown() throws Exception {
        //System.out.println(DebugUtil.end(name.getMethodName()));
    }

    @Test
    public void testGetInstance_Empty() throws Exception {
        Container container = Container.getInstance();
        assertEquals(emptyContainer, container.toString());
    }

    @Test
    public void testGetInstance_MinimalEmpty() throws Exception {
        Container container = Container.getInstance(emptyContainer);
        assertEquals(emptyContainer, container.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInstance_MandatoryCmd() throws Exception {
        String wrong = emptyContainer.replace(Container.CMD, "wrong");
        Container.getInstance(wrong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInstance_MandatoryData() throws Exception {
        String wrong = emptyContainer.replace(Container.DATA, "wrong");
        Container.getInstance(wrong);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInstance_MandatoryOptions() throws Exception {
        String wrong = emptyContainer.replace(Container.OPTIONS, "wrong");
        Container.getInstance(wrong);
    }

    @Test
    public void testWrite_PreviousCmdEmpty() throws Exception {
        Container container = Container.getInstance();
        Object emptyStr = container.put(Container.CMD, "text");
        assertEquals(emptyStr.toString(), "");
    }

    @Test
    public void testWrite_PreviousDataEmpty() throws Exception {
        Container container = Container.getInstance();
        Object emptyStr = container.put(Container.DATA, "text");
        assertEquals(emptyStr, new JSONObject());
    }

    @Test
    public void testWrite_PreviousOptionsEmpty() throws Exception {
        Container container = Container.getInstance();
        Object emptyStr = container.put(Container.OPTIONS, "text");
        assertEquals(emptyStr, new JSONObject());
    }

    @Test
    public void testRead_SimpleCmd() throws Exception {
        Container container = Container.getInstance();
        container.put(Container.CMD, "text");
        Object o = container.get(Container.CMD);
        assertEquals(o.toString(), "text");
    }
}
