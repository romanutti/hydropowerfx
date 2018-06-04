package ch.fhnw.oop2.hydropowerfx.control.watercontrol.custom;

import ch.fhnw.oop2.hydropowerfx.control.watercontrol.demo.PresentationModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class WaterAmount extends Region {

    private PresentationModel presentationModel;
    private DoubleProperty amountWater = new SimpleDoubleProperty();
    private TextField amountWaterLebel; //TODO: change to label
    Pane pane;

    public WaterAmount(PresentationModel presentationModel) {
        // Save the presentation model
        this.presentationModel = presentationModel;

        // Initialize the element
        initializeSelf();
        initializeParts();
        layoutParts();
        initializeHandlers();
        setupBindings();
    }

    private void initializeSelf() {
        String stylesheet = getClass().getResource("../style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private void initializeParts() {
        pane = new Pane();
        amountWaterLebel= new TextField();//TODO: change to label
        setAmountWater(80);
        amountWaterLebel.setText(String.valueOf(getAmountWater()));
    }

    private void layoutParts() {
        pane.getChildren().addAll(amountWaterLebel);
        getChildren().add(pane);
    }
    private void initializeHandlers() {

        amountWaterLebel.textProperty().addListener((observable, oldValue, newValue) -> {
            setAmountWater(Double.valueOf(newValue));
        });
    }

    private void setupBindings() {
        // Detect changes in the presentation model
        presentationModel.waterAmountProperty().bindBidirectional(amountWater);
    }


    public double percentageAmountOfAllWater(double value, double minValue, double maxValue) {
        return ((value - minValue) / (maxValue - minValue))*100;
    }

    public double getAmountWater() {
        return amountWater.get();
    }

    public DoubleProperty amountWaterProperty() {
        return amountWater;
    }

    public void setAmountWater(double amountWater) {
        this.amountWater.set(amountWater);
    }
}


