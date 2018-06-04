package ch.fhnw.oop2.hydropowerfx.control.watercontrol.custom;

import java.util.Arrays;
import java.util.List;

/**
 * Represent the water level with a zero-index based water level and a one-index based human readable text label.
 *
 * @author Simon Wächter
 */
public enum WaterLevel {

    WATER_LEVEL_0(0.0, "Level 1"),
    WATER_LEVEL_1(1.0, "Level 2"),
    WATER_LEVEL_2(2.0, "Level 3"),
    WATER_LEVEL_3(3.0, "Level 4"),
    WATER_LEVEL_4(4.0, "Level 5"),
    WATER_LEVEL_5(5.0, "Level 6"),
    WATER_LEVEL_6(6.0, "Level 7");

    private final Double level;

    private final String text;

    WaterLevel(Double level, String text) {
        this.level = level;
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static WaterLevel fromValueToWaterLevel(double value) {
        if (value < 0.5) return WaterLevel.WATER_LEVEL_0;
        if (value < 1.5) return WaterLevel.WATER_LEVEL_1;
        if (value < 2.5) return WaterLevel.WATER_LEVEL_2;
        if (value < 3.5) return WaterLevel.WATER_LEVEL_3;
        if (value < 4.5) return WaterLevel.WATER_LEVEL_4;
        if (value < 5.5) return WaterLevel.WATER_LEVEL_5;
        return WaterLevel.WATER_LEVEL_6;
    }

    public static Double fromTextToLevel(String text) {
        List<WaterLevel> waterLevels = Arrays.asList(WATER_LEVEL_0, WATER_LEVEL_1, WATER_LEVEL_3, WATER_LEVEL_4, WATER_LEVEL_5);
        for (WaterLevel waterLevel : waterLevels) {
            if (text.equals(waterLevel.toString())) {
                return waterLevel.level;
            }
        }
        return WaterLevel.WATER_LEVEL_6.level;
    }
}
