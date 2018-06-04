package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import static ch.fhnw.oop2.hydropowerfx.util.NumberFormatUtil.*;

public class HeaderView extends HBox implements ViewMixin {

    // model
    private final RootPM rootPM;

    // gui elements
    private VBox labelArea;
    private ImageView imageArea;
    private Label titleLabel;
    private Label nameLabel;
    private Label powerLabel;
    private Label startOfOperationFirstLabel;


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
        nameLabel = new Label();
        powerLabel = new Label();
        titleLabel = new Label();
        titleLabel.setId("titleLabel");
        startOfOperationFirstLabel = new Label();

        // image area
        imageArea = new ImageView();
        Image image = new Image("/images/metrics_icon.png");
        imageArea.setImage(image);

    }

    @Override
    public void layoutControls() {

        // sizing
        setPadding(new Insets(5));
        setSpacing(5);

        imageArea.setFitHeight(14);
        imageArea.setFitWidth(14);
        imageArea.setPreserveRatio(true);

        labelArea.setPrefHeight(70);
        labelArea.setMinHeight(70);
        labelArea.setVgrow(nameLabel, Priority.ALWAYS);
        labelArea.setVgrow(powerLabel, Priority.ALWAYS);
        labelArea.setVgrow(titleLabel, Priority.ALWAYS);
        labelArea.setVgrow(startOfOperationFirstLabel,Priority.ALWAYS);

        labelArea.getChildren().addAll(titleLabel, nameLabel, powerLabel, startOfOperationFirstLabel);

        getChildren().addAll(labelArea, imageArea);

    }

    @Override
    public void setupBindings() {
        PowerStationPM proxy = rootPM.getPowerStationProxy();

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

    }
}
