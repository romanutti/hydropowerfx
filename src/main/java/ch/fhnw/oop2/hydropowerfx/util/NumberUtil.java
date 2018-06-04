package ch.fhnw.oop2.hydropowerfx.util;

public abstract class NumberUtil {

    public static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
