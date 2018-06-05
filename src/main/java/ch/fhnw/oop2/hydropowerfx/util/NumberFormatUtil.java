package ch.fhnw.oop2.hydropowerfx.util;

import java.text.DecimalFormat;

public abstract class NumberFormatUtil {

    public static final DecimalFormat YEAR_FORMAT;

    static {
        YEAR_FORMAT = new DecimalFormat();
        YEAR_FORMAT.setGroupingUsed(false);
    }
}
