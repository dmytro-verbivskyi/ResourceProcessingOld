package dverbovskiy.util;

/**
 * Created with IntelliJ IDEA.
 * User: dverbovskiy
 * Date: 06.02.13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */
public class Util {

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        // gotten from: http://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

}
