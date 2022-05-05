package io.github.underware.util;

public class StringUtil {

    public static String buildString(String... args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : args) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

}
