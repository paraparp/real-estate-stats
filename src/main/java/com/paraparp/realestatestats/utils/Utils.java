package com.paraparp.realestatestats.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Utils {


    public static int convertToInt(String str) {
        NumberFormat format = NumberFormat.getInstance(new Locale("es", "ES"));
        Number number;
        try {
            number = format.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return (int) number.doubleValue();
    }

}
