package dverbovskiy.web.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ContainerTest {
    private Container box;

    @Before
    public void setUp() throws Exception {
        System.out.println("@Before - ContainerTest.eachTimeSetup");

        //box = Container.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("@After - ContainerTest.eachTimeTearDown");
    }

    @Test
    public void testGetInstance() throws Exception {
        System.out.println("ContainerTest.testGetInstance()");
    }

    @Test
    public void testRead() throws Exception {
        System.out.println("ContainerTest.testRead()");
    }

    @Test
    public void testWrite() throws Exception {
        System.out.println("ContainerTest.testWrite()");
    }
}
