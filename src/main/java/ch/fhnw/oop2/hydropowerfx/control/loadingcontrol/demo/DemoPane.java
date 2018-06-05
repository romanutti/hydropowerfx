package ch.fhnw.oop2.hydropowerfx.control.loadingcontrol.demo;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import ch.fhnw.oop2.hydropowerfx.control.loadingcontrol.HydroControl;
import javafx.util.converter.NumberStringConverter;

class DemoPane extends BorderPane {

    private final PresentationModel pm;

    // declare the custom control
    private HydroControl cc;

    // all controls
    private TextField waterTextField;

    private TextField powerValueTextField;

    private Button isOnValueButton;


    public DemoPane(PresentationModel pm) {
        this.pm = pm;
        initializeControls();
        layoutControls();
        setupValueChangeListeners();
        setupBindings();
    }

    private void setupValueChangeListeners() {
        waterTextField.setOnAction(event -> {
            //cc.setWaterValue(10);
            cc.getPm().setWaterValue(Float.parseFloat(waterTextField.textProperty().getValue()));
        });

        powerValueTextField.setOnAction(event -> {
            //cc.setWaterValue(10);
            cc.getPm().setPowerValue(Float.parseFloat(powerValueTextField.textProperty().getValue()));
        });

        isOnValueButton.setOnAction(event -> {
            cc.getPm().isOnValueProperty().setValue(!cc.getPm().isOnValueProperty().getValue());
            if (cc.getPm().isOnValueProperty().getValue().equals(true)) {
                isOnValueButton.setText("Turn on");
            } else {
                isOnValueButton.setText("Turn off");
            }
        });
    }

    private void initializeControls() {
        //setPadding(new Insets(5));

        cc = new HydroControl();

        waterTextField = new TextField();

        powerValueTextField = new TextField();

        isOnValueButton = new Button("Turn on");
    }

    private void layoutControls() {
        /*VBox controlPane = new VBox(new Label("Hydro Power"),
                waterTextField, powerValueTextField, isOnValueButton);
        controlPane.setPadding(new Insets(0, 10, 0, 10));
        controlPane.setSpacing(5);


        setLeft(controlPane);*/
        setCenter(cc);
    }

    private void setupBindings() {

        NumberStringConverter converter = new NumberStringConverter() {
            @Override
            public Number fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (Exception e) {
                    return 0;
                }

            }
        };

        waterTextField.textProperty().bindBidirectional(pm.waterValueProperty(), converter);
        powerValueTextField.textProperty().bindBidirectional(pm.powerValueProperty(), converter);

    }
}
