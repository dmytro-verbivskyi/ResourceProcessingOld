package dverbovskiy.debugTraining;

import java.util.Hashtable;

public class Test {
    public static void main(String[] args) {
        Hashtable ht = new Hashtable();
        ht.put("1", "2");
        ht.put("2", "3");

        if ( ht.contains("1") ) {
            System.out.print("1");
        }
        if ( ht.contains("2") ) {
            System.out.print("2");
        }
    }
}
