package ch.fhnw.oop2.hydropowerfx.control.loadingcontrol.demo;

import javafx.beans.property.*;

public class PresentationModel {
    private final FloatProperty waterValue = new SimpleFloatProperty();
    private final FloatProperty powerValue = new SimpleFloatProperty();
    private final BooleanProperty isOnValue = new SimpleBooleanProperty();



    public double getWaterValue() {
        return waterValue.get();
    }

    public FloatProperty waterValueProperty() {
        return waterValue;
    }

    public void setWaterValue(float waterValue) {
        this.waterValue.set(waterValue);
    }

    public double getPowerValue() {
        return powerValue.get();
    }

    public FloatProperty powerValueProperty() {
        return powerValue;
    }

    public void setPowerValue(float powerValue) {
        this.powerValue.set(powerValue);
    }


    public boolean isIsOnValue() {
        return isOnValue.get();
    }

    public BooleanProperty isOnValueProperty() {
        return isOnValue;
    }

    public void setIsOnValue(boolean isOnValue) {
        this.isOnValue.set(isOnValue);
    }

}
