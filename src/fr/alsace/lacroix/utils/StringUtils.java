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
    
    public static String insertInSpecialPosition(String s, String s1, int position) {
        if(s.isEmpty()) {
            return s1;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(s.substring(0,position));
            sb.append(s1);
            if(s.length()>position) {
                sb.append(s.substring(position));
            }
            return sb.toString();
        }
    }
    
    public static String eraseAtSpecialPosition(String s, int position, int n) {
        if(s.length() <= 1) {
            return "";
        } else if(position <= 0) {
            return s;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(eraseLastChars(s.substring(0,position), n));
            if(s.length()>position) {
                sb.append(s.substring(position));
            }
            
            return sb.toString();
        }
    }
}
