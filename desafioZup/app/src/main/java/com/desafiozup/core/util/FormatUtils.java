package com.desafiozup.core.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FormatUtils {


    public static String convertAmericanDateToBrazilian(String date) {
        String[] calendarParts = date.split("-");
        return String.format("%s/%s/%s", calendarParts[2], calendarParts[1], calendarParts[0]);
    }
}
