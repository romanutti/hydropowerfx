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

public class Header extends HBox implements ViewMixin {

    private final RootPM rootPM;

    private VBox labelArea;
    private ImageView imageArea;
    private Label titleLabel;
    private Label nameLabel;
    private Label powerLabel;
    private Label startOfOperationFirstLabel;

    public Header(RootPM rootPM) {
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
        nameLabel = new Label("name");
        powerLabel = new Label("power");
        titleLabel = new Label("title");
        titleLabel.setId("titleLabel");
        startOfOperationFirstLabel = new Label("startofopblalba");


        // image area
        imageArea = new ImageView();
        Image image = new Image("http://consumersfederation.org.au/wp-content/uploads/2015/09/hydropower.png");
        imageArea.setImage(image);

    }

    @Override
    public void layoutControls() {

        imageArea.setFitHeight(70);
        imageArea.setFitWidth(70);
        imageArea.setPreserveRatio(true);

        labelArea.setPrefHeight(70);
        labelArea.setMinHeight(70);
        labelArea.setVgrow(nameLabel,Priority.ALWAYS);
        labelArea.setVgrow(powerLabel,Priority.ALWAYS);
        labelArea.setVgrow(titleLabel,Priority.ALWAYS);

        //TODO enable Vgrow for space between header and Editor
        // padding
        setPadding(new Insets(5));
        setSpacing(5);

        labelArea.getChildren().addAll(titleLabel, nameLabel, powerLabel, startOfOperationFirstLabel);
        getChildren().addAll(labelArea, imageArea);

    }

    @Override
    public void setupBindings() {
        PowerStationPM proxy = rootPM.getPowerStationProxy();

        // Title
        titleLabel.textProperty().bind(proxy.nameProperty());

        // Name
        nameLabel.textProperty().bind(proxy.nameProperty());

        // Power



        powerLabel.textProperty().bind(Bindings.concat(
                Bindings.selectString(proxy.maxWaterVolumeProperty()),
                " MW")
        );

        // Start of operation first
        startOfOperationFirstLabel.textProperty().bindBidirectional(proxy.startOfOperationFirstProperty(), new NumberStringConverter());

    }
}
