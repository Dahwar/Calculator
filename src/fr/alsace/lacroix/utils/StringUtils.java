package fr.alsace.lacroix.utils;

/**
 *
 * @author Florent
 */
public class StringUtils {
     public static boolean isNumeric(String s) {
        return s.matches("-?\\d+(\\.\\d+)?");
    }
}
