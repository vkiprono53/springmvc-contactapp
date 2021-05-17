package com.vkiprono.utils;

public class StringUtility {
    public static String convertArrayToCommaList(Object[] objects) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Object object : objects) {
            stringBuilder.append(object).append(",");
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

}

