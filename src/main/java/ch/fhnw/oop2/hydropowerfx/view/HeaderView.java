package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.control.watercontrol.demo.DemoPane;
import ch.fhnw.oop2.hydropowerfx.control.watercontrol.demo.PresentationModel;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

import static ch.fhnw.oop2.hydropowerfx.util.ImageUtil.*;

import static ch.fhnw.oop2.hydropowerfx.util.NumberFormatUtil.YEAR_FORMAT;

public class HeaderView extends HBox implements ViewMixin {

    // model
    private final RootPM rootPM;

    // gui elements
    private VBox labelArea;
    private VBox imageBox;
    private ImageView imageArea;
    private Label titleLabel;
    private Label nameLabel;
    private Label powerLabel;
    private Label startOfOperationFirstLabel;
    private DemoPane waterControl;
    private PresentationModel waterControlPM;

    private Pane spacer;


    public HeaderView(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("header");
    }

    @Override
    public void initializeControls() {
        // label area
        labelArea = new VBox();
        imageBox = new VBox();
        nameLabel = new Label();
        powerLabel = new Label();
        titleLabel = new Label();
        waterControlPM = new PresentationModel();
        waterControl = new DemoPane(waterControlPM);
        titleLabel.setId("titleLabel");
        startOfOperationFirstLabel = new Label();

        spacer = new Pane();

        // image area
        imageArea = new ImageView();
    }

    @Override
    public void layoutControls() {

        /********************************************************************************
         LAYOUT
         Layouting sources:
         - View classes: via css
         - Added items: via java
         ********************************************************************************/

        /********************************************************************************
         IMAGE area formatting
         *******************************************************************************/
        imageBox.setPadding(new Insets(50,0,10,0));
        imageBox.getChildren().add(imageArea);
        imageArea.setFitHeight(60);
        imageArea.setPreserveRatio(true);

        /********************************************************************************
         LABEL area formatting
         *******************************************************************************/
        labelArea.setPrefHeight(100);
        labelArea.setMinHeight(70);
        labelArea.setVgrow(nameLabel, Priority.ALWAYS);
        labelArea.setVgrow(powerLabel, Priority.ALWAYS);
        labelArea.setVgrow(titleLabel, Priority.ALWAYS);
        labelArea.setVgrow(startOfOperationFirstLabel, Priority.ALWAYS);

        labelArea.getChildren().addAll(titleLabel, nameLabel, powerLabel, startOfOperationFirstLabel);
        HBox.setHgrow(spacer, Priority.ALWAYS);


        getChildren().addAll(labelArea, spacer, imageArea, waterControl);

    }

    @Override
    public void setupBindings() {
        PowerStationPM proxy = rootPM.getPowerStationProxy();

        /********************************************************************************
         BINDINGS
         ********************************************************************************/
        // title
        titleLabel.textProperty().bind(proxy.nameProperty());
        // name
        nameLabel.textProperty().bind(Bindings.concat(proxy.nameProperty(), ", ", proxy.cantonProperty()));
        // power
        powerLabel.textProperty().bind(Bindings.concat(
                Bindings.selectString(proxy.maxWaterVolumeProperty()), " MW") // add suffix "MW"
        );
        // operationfirst
        startOfOperationFirstLabel.textProperty().bindBidirectional(proxy.startOfOperationFirstProperty(), new NumberStringConverter(YEAR_FORMAT));

        /********************************************************************************
         HIDE image if no url entered
         ********************************************************************************/
        // image area
        imageArea.visibleProperty().bind(rootPM.photoIconEnabledProperty());

        /********************************************************************************
         CUSTOM CONTROL functionality
         ********************************************************************************/
        // custom control
        waterControlPM.waterAmountProperty().bindBidirectional(proxy.maxWaterVolumeProperty());

    }

    public void setupValueChangedListeners() {
        PowerStationPM proxy = rootPM.getPowerStationProxy();

        /********************************************************************************
         REFRESH IMAGE functionality
        ********************************************************************************/
        proxy.imageUrlProperty().addListener((observable, oldValue, newValue) -> {
            imageArea.setImage(getImage(newValue)); // refresh Image

        });
    }


}
