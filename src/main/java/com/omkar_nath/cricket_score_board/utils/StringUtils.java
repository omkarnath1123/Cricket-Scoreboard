package com.omkar_nath.cricket_score_board.utils;

public class StringUtils {

    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static String rightPadSpaces(String value, int length) {
        return String.format("%1$-" + length + "s", value);
    }

    public static String rightPadSpaces(Integer value, int length) {
        return rightPadSpaces(Integer.toString(value), length);
    }

    public static String rightPadSpaces(Double value, int length) {
        if (Double.isNaN(value))
            value = 0D;
        return rightPadSpaces(String.format("%.2f", value), length);
    }
}
