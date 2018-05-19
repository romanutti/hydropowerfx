package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.LanguageSwitcherPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.converter.NumberStringConverter;

public class Editor extends GridPane implements ViewMixin {
    private final RootPM rootPM;

    private Label nameLabel;
    private Label typeLabel;
    private Label siteLabel;
    private Label cantonLabel;
    private Label maxWaterVolumeLabel;
    private Label maxPowerMwLabel;
    private Label startOfOperationFirstLabel;
    private Label startOfOperationLastLabel;
    private Label latitudeLabel;
    private Label longitudeLabel;
    private Label statusLabel;
    private Label waterbodiesLabel;
    private Label imageUrlLabel;

    private TextField nameTextField;
    private TextField typeTextField;
    private TextField siteTextField;
    private TextField cantonTextField;
    private TextField maxWaterVolumeTextField;
    private TextField maxPowerMwTextField;
    private TextField startOfOperationFirstTextField;
    private TextField startOfOperationLastTextField;
    private TextField latitudeTextField;
    private TextField longitudeTextField;
    private TextField statusTextField;
    private TextField waterbodiesTextField;
    private TextField imageUrlTextField;


    public Editor(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("editor");
    }

    @Override
    public void initializeControls() {
        nameLabel = new Label();
        typeLabel = new Label();
        siteLabel = new Label();
        cantonLabel = new Label();
        maxWaterVolumeLabel = new Label();
        maxPowerMwLabel = new Label();
        startOfOperationFirstLabel = new Label();
        startOfOperationLastLabel = new Label();
        latitudeLabel = new Label();
        longitudeLabel = new Label();
        statusLabel = new Label();
        waterbodiesLabel = new Label();
        imageUrlLabel = new Label();


        nameTextField = new TextField();
        typeTextField = new TextField();
        siteTextField = new TextField();
        cantonTextField = new TextField();
        maxWaterVolumeTextField = new TextField();
        maxPowerMwTextField = new TextField();
        startOfOperationFirstTextField = new TextField();
        startOfOperationLastTextField = new TextField();
        latitudeTextField = new TextField();
        longitudeTextField = new TextField();
        statusTextField = new TextField();
        waterbodiesTextField = new TextField();
        imageUrlTextField = new TextField();

    }

    @Override
    public void layoutControls() {

        setMinWidth(USE_PREF_SIZE);
        // Set horizontal gap
        setHgap(10);
        // Set vertical gap
        setVgap(3);
        // Set padding

        setPadding(new Insets(5,5,5,5));
        ColumnConstraints cc = new ColumnConstraints();
        ColumnConstraints ccFix = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        ccFix.setHgrow(Priority.NEVER);
        cc.setMinWidth(140);
        ccFix.setMinWidth(120);
        getColumnConstraints().addAll(ccFix, cc, ccFix, cc);

        add(nameLabel,0,0);
        add(siteLabel,0,1);
        add(maxWaterVolumeLabel,0,2);
        add(startOfOperationFirstLabel,0,3);
        add(latitudeLabel,0,4);
        add(statusLabel,0,5);
        add(waterbodiesLabel,0,6);
        add(imageUrlLabel,0,7);

        add(typeLabel,2,0);
        add(cantonLabel,2,1);
        add(maxPowerMwLabel,2,2);
        add(startOfOperationLastLabel,2,3);
        add(longitudeLabel,2,4);

        add(nameTextField,1,0);
        add(siteTextField,1,1);
        add(maxWaterVolumeTextField,1,2);
        add(startOfOperationFirstTextField,1,3);
        add(latitudeTextField,1,4);
        add(statusTextField,1,5);
        add(waterbodiesTextField,1,6,3,1);
        add(imageUrlTextField,1,7,3,1);

        add(typeTextField,3,0);
        add(cantonTextField,3,1);
        add(maxPowerMwTextField,3,2);
        add(startOfOperationLastTextField,3,3);
        add(longitudeTextField,3,4);

    }

    @Override
    public void setupEventHandlers() {

    }

    @Override
    public void setupBindings() {
        PowerStationPM proxy = rootPM.getPowerStationProxy();

        // Name
        nameLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().nameLabelTextProperty());
        nameTextField.textProperty().bindBidirectional(proxy.nameProperty());

        // Type
        typeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().typeLabelTextProperty());
        // TODO: Enums convertieren?
        //typeTextField.textProperty().bindBidirectional(new StringProperty(proxy.getType().toString());

        // Site
        siteLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().siteLabelTextProperty());
        siteTextField.textProperty().bindBidirectional(proxy.siteProperty());

        // Canton
        cantonLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().cantonLabelTextProperty());
        // TODO: Enums convertieren?
        // cantonTextField.textProperty().bindBidirectional(proxy.cantonProperty());

        // MaxWaterVolume
        maxWaterVolumeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().maxWaterVolumeLabelTextProperty());
        // TODO: Enums convertieren?
        maxWaterVolumeTextField.textProperty().bindBidirectional(proxy.maxWaterVolumeProperty(), new NumberStringConverter());

        // MawPowerMw
        maxPowerMwLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().maxPowerMwLabelTextProperty());
        maxPowerMwTextField.textProperty().bindBidirectional(proxy.maxPowerMwProperty(), new NumberStringConverter());

        // StartOfOperationFirst
        startOfOperationFirstLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationFirstLabelTextProperty());
        startOfOperationFirstTextField.textProperty().bindBidirectional(proxy.startOfOperationFirstProperty(), new NumberStringConverter());

        // StartOfOperationLast
        startOfOperationLastLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationLastLabelTextProperty());
        startOfOperationLastTextField.textProperty().bindBidirectional(proxy.startOfOperationLastProperty(), new NumberStringConverter());

        // Latitude
        latitudeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().latitudeLabelTextProperty());
        latitudeTextField.textProperty().bindBidirectional(proxy.latitudeProperty(), new NumberStringConverter());

        // Longitude
        longitudeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().longitudeLabelTextProperty());
        longitudeTextField.textProperty().bindBidirectional(proxy.longitudeProperty(), new NumberStringConverter());

        // Status
        statusLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().statusLabelTextProperty());
        statusTextField.textProperty().bindBidirectional(proxy.statusProperty());

        // Waterbodies
        waterbodiesLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().waterbodiesLabelTextProperty());
        waterbodiesTextField.textProperty().bindBidirectional(proxy.waterbodiesProperty());

        // ImageUrl
        imageUrlLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().imageUrlLabelTextProperty());
        imageUrlTextField.textProperty().bindBidirectional(proxy.imageUrlProperty());
    }
}
