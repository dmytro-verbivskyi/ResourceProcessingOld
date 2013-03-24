package dverbovskiy.debugTraining;

import dverbovskiy.json.JSONObject;

public class Test {
    public int div(int x, int y) {
        return x / y / 0;
    }

    public static void main(String[] a) {
        Test f = new Test();
        System.out.println( f.div(1, 2));


        try {
            JSONObject j = JSONObject.getInstance();

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
