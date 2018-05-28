package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.Canton;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.LanguageSwitcherPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.StringConverter;
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
    private ChoiceBox typeChoiceBox;
    private TextField siteTextField;
    private ChoiceBox cantonChoiceBox;
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
        typeChoiceBox = new ChoiceBox();
        siteTextField = new TextField();
        cantonChoiceBox = new ChoiceBox();
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

        add(typeChoiceBox,3,0);
        add(cantonChoiceBox,3,1);
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
        // Disable on no selection
        nameTextField.disableProperty().bind(rootPM.labelsEnabledProperty()); //TODO: Für weitere Textproperties umsetzen


        // Type
        typeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().typeLabelTextProperty());
        typeChoiceBox.setItems(FXCollections.observableArrayList(PowerStationPM.Type.values()));
        typeChoiceBox.getSelectionModel().select(proxy.getType());
        typeChoiceBox.setConverter(new StringConverter<PowerStationPM.Type>() {
            @Override
            public String toString(PowerStationPM.Type object) {
                return object.name();
            }

            @Override
            public PowerStationPM.Type fromString(String string) {
                return PowerStationPM.Type.L;
            }
        });
        cantonChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Canton>) (observable, oldValue, newValue) -> {
            proxy.setCanton(newValue);
            proxy.cantonProperty().set(newValue);
        });
        // Disable on no selection
        typeChoiceBox.disableProperty().bind(rootPM.labelsEnabledProperty());


        // Site
        siteLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().siteLabelTextProperty());
        siteTextField.textProperty().bindBidirectional(proxy.siteProperty());

        // Disable on no selection
        siteTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // Canton
        cantonLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().cantonLabelTextProperty());

        // TODO: Enums convertieren?
        cantonChoiceBox.setItems(FXCollections.observableArrayList(Canton.values()));

        cantonChoiceBox.getSelectionModel().select(proxy.getCanton());
        cantonChoiceBox.setConverter(new StringConverter<Canton>() {
            @Override
            public String toString(Canton object) {
                return object.getName();
            }

            @Override
            public Canton fromString(String string) {
                return Canton.ZH;
            }});

        cantonChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Canton>) (observable, oldValue, newValue) -> {
            proxy.setCanton(newValue);
        });
        // Disable on no selection
        cantonChoiceBox.disableProperty().bind(rootPM.labelsEnabledProperty());




        // MaxWaterVolume
        maxWaterVolumeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().maxWaterVolumeLabelTextProperty());
        // TODO: Enums convertieren?
        maxWaterVolumeTextField.textProperty().bindBidirectional(proxy.maxWaterVolumeProperty(), new NumberStringConverter());
        // Disable on no selection
        maxWaterVolumeTextField.disableProperty().bind(rootPM.labelsEnabledProperty());


        // MawPowerMw
        maxPowerMwLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().maxPowerMwLabelTextProperty());
        maxPowerMwTextField.textProperty().bindBidirectional(proxy.maxPowerMwProperty(), new NumberStringConverter());
        // Disable on no selection
        maxPowerMwTextField.disableProperty().bind(rootPM.labelsEnabledProperty());


        // StartOfOperationFirst
        startOfOperationFirstLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationFirstLabelTextProperty());
        startOfOperationFirstTextField.textProperty().bindBidirectional(proxy.startOfOperationFirstProperty(), new NumberStringConverter());
        // Disable on no selection
        startOfOperationFirstTextField.disableProperty().bind(rootPM.labelsEnabledProperty());



        // StartOfOperationLast
        startOfOperationLastLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationLastLabelTextProperty());
        startOfOperationLastTextField.textProperty().bindBidirectional(proxy.startOfOperationLastProperty(), new NumberStringConverter());
        // Disable on no selection
        startOfOperationLastTextField.disableProperty().bind(rootPM.labelsEnabledProperty());


        // Latitude
        latitudeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().latitudeLabelTextProperty());
        latitudeTextField.textProperty().bindBidirectional(proxy.latitudeProperty(), new NumberStringConverter());
        // Disable on no selection
        latitudeTextField.disableProperty().bind(rootPM.labelsEnabledProperty());


        // Longitude
        longitudeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().longitudeLabelTextProperty());
        longitudeTextField.textProperty().bindBidirectional(proxy.longitudeProperty(), new NumberStringConverter());
        // Disable on no selection
        longitudeTextField.disableProperty().bind(rootPM.labelsEnabledProperty());


        // Status
        statusLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().statusLabelTextProperty());
        statusTextField.textProperty().bindBidirectional(proxy.statusProperty());
        // Disable on no selection
        statusTextField.disableProperty().bind(rootPM.labelsEnabledProperty());


        // Waterbodies
        waterbodiesLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().waterbodiesLabelTextProperty());
        waterbodiesTextField.textProperty().bindBidirectional(proxy.waterbodiesProperty());
        // Disable on no selection
        waterbodiesTextField.disableProperty().bind(rootPM.labelsEnabledProperty());


        // ImageUrl
        imageUrlLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().imageUrlLabelTextProperty());
        imageUrlTextField.textProperty().bindBidirectional(proxy.imageUrlProperty());
        // Disable on no selection
        imageUrlTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

    }
}
