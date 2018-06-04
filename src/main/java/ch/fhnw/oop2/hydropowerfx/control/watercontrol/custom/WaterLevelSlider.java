package ch.fhnw.oop2.hydropowerfx.control.watercontrol.custom;

import ch.fhnw.oop2.hydropowerfx.control.watercontrol.demo.PresentationModel;
import javafx.scene.control.Slider;
import javafx.util.StringConverter;

public class WaterLevelSlider extends Slider {

    private PresentationModel presentationModel;

    public WaterLevelSlider(PresentationModel presentationModel) {
        // Save the presentation model
        this.presentationModel = presentationModel;

        // Initialize the element
        initializeControls();
        initializeHandlers();
        setupBindings();
    }

    private void initializeControls() {
        // Set the slider range
        setMin(0);
        setMax(6);

        // Set the tick change
        setMinorTickCount(0);
        setMajorTickUnit(1);
        setBlockIncrement(1);
        setSnapToTicks(true);
        setShowTickMarks(true);
        setShowTickLabels(true);

        // Set the tick converter
        setLabelFormatter(new StringConverter<>() {
            @Override
            public String toString(Double object) {
                return fromDoubleToString(object);
            }

            @Override
            public Double fromString(String string) {
                return fromStringToDouble(string);
            }
        });
    }

    private void initializeHandlers() {
    }

    private void setupBindings() {
        // Detect changes in the presentation model
        presentationModel.waterLevelValueProperty().bindBidirectional(valueProperty());
    }

    private String fromDoubleToString(Double value) {
        return WaterLevel.fromValueToWaterLevel(value).toString();
    }

    private double fromStringToDouble(String text) {
        return WaterLevel.fromTextToLevel(text);
    }
}
