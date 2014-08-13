package fr.alsace.lacroix.utils;

/**
 *
 * @author Florent
 */
public class StringUtils {
     public static boolean isNumeric(String s) {
        return s.matches("-?\\d+(\\.\\d+)?");
    }
     
    public static String getLastChars(String s, int n) {
        if(s.length() <= n) {
            return s;
        } else {
            return s.substring(s.length()-n);
        }
    }
    
    public static String eraseLastChars(String s, int n) {
        if(s.length() <= 1) {
            return "";
        } else {
            return s.substring(0, s.length()-n);
        }
    }
}
