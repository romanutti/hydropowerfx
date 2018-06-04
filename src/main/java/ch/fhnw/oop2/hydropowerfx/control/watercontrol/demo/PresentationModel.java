package ch.fhnw.oop2.hydropowerfx.control.watercontrol.demo;

import javafx.beans.property.*;

public class PresentationModel {
    // All the properties waiting for being displayed
    private final StringProperty demoTitle = new SimpleStringProperty("Wasserpegel Applikation");
    private final DoubleProperty waterAmount   = new SimpleDoubleProperty(50.0) ;//TODO: init value muss herausgenommen werden
    private final DoubleProperty waterLevelValue = new SimpleDoubleProperty();
    private final StringProperty waterLevelLabel = new SimpleStringProperty();


    public double getWaterAmount() {
        return waterAmount.get();
    }

    public DoubleProperty waterAmountProperty() {
        return waterAmount;
    }

    public void setWaterAmount(double waterAmount) {
        this.waterAmount.set(waterAmount);
    }

    public String getDemoTitle() {
        return demoTitle.get();
    }

    public StringProperty demoTitleProperty() {
        return demoTitle;
    }

    public void setDemoTitle(String demoTitle) {
        this.demoTitle.set(demoTitle);
    }

    public double getWaterLevelValue() {
        return waterLevelValue.get();
    }

    public DoubleProperty waterLevelValueProperty() {
        return waterLevelValue;
    }

    public void setWaterLevelValue(double waterLevelValue) {
        this.waterLevelValue.set(waterLevelValue);
    }

    public String getWaterLevelLabel() {
        return waterLevelLabel.get();
    }

    public StringProperty waterLevelLabelProperty() {
        return waterLevelLabel;
    }

    public void setWaterLevelLabel(String waterLevelLabel) {
        this.waterLevelLabel.set(waterLevelLabel);
    }
}


