package ch.fhnw.oop2.hydropowerfx.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class NumberFormatUtil {

    public static final DecimalFormat YEAR_FORMAT;

    static {
        YEAR_FORMAT = new DecimalFormat();
        YEAR_FORMAT.setGroupingUsed(false);
    }
}
