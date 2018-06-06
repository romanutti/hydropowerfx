package ch.fhnw.oop2.hydropowerfx.control.watercontrol.demo;

import ch.fhnw.oop2.hydropowerfx.control.watercontrol.custom.ImageControl;
import ch.fhnw.oop2.hydropowerfx.control.watercontrol.custom.WaterAmount;
import ch.fhnw.oop2.hydropowerfx.control.watercontrol.custom.WaterLevelControl;
import ch.fhnw.oop2.hydropowerfx.control.watercontrol.custom.WaterLevelSlider;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.layout.*;


public class DemoPane extends StackPane {

    private final PresentationModel presentationModel;

    // Water level control that displays the water level
    private WaterLevelControl waterLevelControl;
    // MR: Image control added
    private ImageControl imageControl;
    // Water level slider that allows changing the water level
    private WaterLevelSlider waterLevelSlider;
    // all controls
    private WaterAmount waterAmount;
    private HBox controlArea;


    public DemoPane(PresentationModel presentationModel) {
        this.presentationModel = presentationModel;
        initializeControls();
        layoutControls();
        setupVelueChangeListener();
        setupBindings();
    }

    private void initializeControls() {
        waterLevelControl = new WaterLevelControl();
        imageControl = new ImageControl();
        waterLevelSlider = new WaterLevelSlider(presentationModel);
        waterLevelSlider.setOrientation(Orientation.VERTICAL);
        waterAmount = new WaterAmount(presentationModel);
        controlArea = new HBox();
    }

    private void layoutControls() {
        setMaxWidth(100);
        setPadding(new Insets(5,10,5,5));

        controlArea.getChildren().addAll(imageControl, waterLevelControl);
        controlArea.setSpacing(10);
        getChildren().addAll(controlArea);

    }


    private void setupBindings() {
        // Bindings for the water level slider
        waterLevelSlider.valueProperty().bindBidirectional(presentationModel.waterLevelValueProperty());
        // Bindings for the water level Control
        waterLevelControl.valueProperty().bindBidirectional(presentationModel.waterLevelValueProperty());
        //Bindings for the water amount
        waterAmount.amountWaterProperty().bindBidirectional(presentationModel.waterAmountProperty());//TODO: muss an die richtige Wassermenge vom oop2 projekt gebunden werden
    }

    private void setupVelueChangeListener() {
        waterAmount.amountWaterProperty().addListener((observable, oldValue, newValue) -> {
            double max = 1500;
            double min =0;
            double value = waterAmount.getAmountWater(); //TODO: Beispiels wert, sollte später Amount of Kraftwerk sein

            double range = waterAmount.percentageAmountOfAllWater(value, min, max);
            //je nach range wird der Slider angepasst
            if(range==0){
                waterLevelSlider.setValue(0);
            } else if (0< range && range < 16.67){
                waterLevelSlider.setValue(1);
            }else if(16.67< range && range < 33.33){
                waterLevelSlider.setValue(2);
            }else if(33.33< range && range < 50){
                waterLevelSlider.setValue(3);
            }else if(50< range && range < 66.67){
                waterLevelSlider.setValue(4);
            }else if(66.67< range && range < 83.33){
                waterLevelSlider.setValue(5);
            }else if(83.33< range && range<=100){
                waterLevelSlider.setValue(6);
            }else{
                waterLevelSlider.setValue(6);
            }

        });
        waterLevelSlider.valueProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    public ImageControl getImageControl() {
        return imageControl;
    }

    public void setImageControl(ImageControl imageControl) {
        this.imageControl = imageControl;
    }
}
