package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.Canton;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM.Type;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.Status;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class Editor extends GridPane implements ViewMixin {
    // model
    private final RootPM rootPM;

    // gui elements
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
    private Label inputValidatorLabel;

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
    private ChoiceBox statusChoiceBox;
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
        // labels
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
        inputValidatorLabel = new Label();
        inputValidatorLabel.setId("inputValidatorLabel");


        // textfields
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
        statusChoiceBox = new ChoiceBox();
        waterbodiesTextField = new TextField();
        imageUrlTextField = new TextField();

    }

    @Override
    public void layoutControls() {

        // sizing
        // horizontal gap
        setHgap(10);
        // vertical gap
        setVgap(3);
        // padding
        setPadding(new Insets(5, 5, 5, 5));

        // grid setup
        ColumnConstraints cc = new ColumnConstraints();
        ColumnConstraints ccFix = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        ccFix.setHgrow(Priority.NEVER);
        cc.setMinWidth(140);
        ccFix.setMinWidth(120);
        getColumnConstraints().addAll(ccFix, cc, ccFix, cc);

        add(nameLabel, 0, 0);
        add(siteLabel, 0, 1);
        add(maxWaterVolumeLabel, 0, 2);
        add(startOfOperationFirstLabel, 0, 3);
        add(latitudeLabel, 0, 4);
        add(statusLabel, 0, 5);
        add(waterbodiesLabel, 0, 6);
        add(imageUrlLabel, 0, 7);
        add(inputValidatorLabel, 0, 8, 4, 1);

        add(typeLabel, 2, 0);
        add(cantonLabel, 2, 1);
        add(maxPowerMwLabel, 2, 2);
        add(startOfOperationLastLabel, 2, 3);
        add(longitudeLabel, 2, 4);

        add(nameTextField, 1, 0);
        add(siteTextField, 1, 1);
        add(maxWaterVolumeTextField, 1, 2);
        add(startOfOperationFirstTextField, 1, 3);
        add(latitudeTextField, 1, 4);
        add(statusChoiceBox, 1, 5);
        add(waterbodiesTextField, 1, 6, 3, 1);
        add(imageUrlTextField, 1, 7, 3, 1);

        add(typeChoiceBox, 3, 0);
        add(cantonChoiceBox, 3, 1);
        add(maxPowerMwTextField, 3, 2);
        add(startOfOperationLastTextField, 3, 3);
        add(longitudeTextField, 3, 4);

    }

    @Override
    public void setupValueChangedListeners() {
        // type enum
        typeChoiceBox.setItems(FXCollections.observableArrayList(Type.values()));
        rootPM.getPowerStationProxy().typeProperty().addListener((observable, oldValue, newValue) -> typeChoiceBox.getSelectionModel().select(newValue));

        typeChoiceBox.setConverter(new StringConverter<Type>() {
            @Override
            public String toString(Type object) {
                return object.name();
            }

            @Override
            public Type fromString(String string) {
                return null;
            }
        });

        typeChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Type>) (observable, oldValue, newValue) -> {
            rootPM.getPowerStationProxy().setType(newValue);
        });

        // canton enum
        cantonChoiceBox.setItems(FXCollections.observableArrayList(Canton.values()));
        rootPM.getPowerStationProxy().cantonProperty().addListener((observable, oldValue, newValue) -> cantonChoiceBox.getSelectionModel().select(newValue));

        cantonChoiceBox.setConverter(new StringConverter<Canton>() {
            @Override
            public String toString(Canton object) {
                return object.getName();
            }

            @Override
            public Canton fromString(String string) {
                return null;
            }
        });

        cantonChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Canton>) (observable, oldValue, newValue) -> {
            rootPM.getPowerStationProxy().setCanton(newValue);
        });

        // status enum
        statusChoiceBox.setItems(FXCollections.observableArrayList(Status.values()));
        statusChoiceBox.getSelectionModel().select(rootPM.getPowerStationProxy().getStatus());
        rootPM.getPowerStationProxy().statusProperty().addListener((observable, oldValue, newValue) -> statusChoiceBox.getSelectionModel().select(newValue));

        statusChoiceBox.setConverter(new StringConverter<Status>() {
            @Override
            public String toString(Status object) {
                return object.getName();
            }

            //TODO return null valid?
            @Override
            public Status fromString(String string) {
                return null;
            }
        });

        statusChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Status>) (observable, oldValue, newValue) -> {
            rootPM.getPowerStationProxy().setStatus(newValue);
        });

        // input validation
        maxPowerMwTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "double");
        });
        maxWaterVolumeTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "double");
        });
        startOfOperationFirstTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "int");
        });
        startOfOperationLastTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "int");
        });
        longitudeTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
             rootPM.isValidInput(newValue, "double");
        });
        latitudeTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "double");
        });

    }

    @Override
    public void setupBindings() {
        PowerStationPM proxy = rootPM.getPowerStationProxy();

        // name
        nameLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().nameLabelTextProperty());
        nameTextField.textProperty().bindBidirectional(proxy.nameProperty());
        // disable on no selection
        nameTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // type
        typeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().typeLabelTextProperty());
        // disable on no selection
        typeChoiceBox.disableProperty().bind(rootPM.labelsEnabledProperty());

        // site
        siteLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().siteLabelTextProperty());
        siteTextField.textProperty().bindBidirectional(proxy.siteProperty());
        // disable on no selection
        siteTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // canton
        cantonLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().cantonLabelTextProperty());
        // disable on no selection
        cantonChoiceBox.disableProperty().bind(rootPM.labelsEnabledProperty());

        // maxwatervolume
        maxWaterVolumeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().maxWaterVolumeLabelTextProperty());
        maxWaterVolumeTextField.textProperty().bindBidirectional(proxy.maxWaterVolumeProperty(), new NumberStringConverter());
        // disable on no selection
        maxWaterVolumeTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // maxpower
        maxPowerMwLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().maxPowerMwLabelTextProperty());
        maxPowerMwTextField.textProperty().bindBidirectional(proxy.maxPowerMwProperty(), new NumberStringConverter());
        // disable on no selection
        maxPowerMwTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // operationfirst
        startOfOperationFirstLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationFirstLabelTextProperty());
        startOfOperationFirstTextField.textProperty().bindBidirectional(proxy.startOfOperationFirstProperty(), new NumberStringConverter());
        // disable on no selection
        startOfOperationFirstTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // operationlast
        startOfOperationLastLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationLastLabelTextProperty());
        startOfOperationLastTextField.textProperty().bindBidirectional(proxy.startOfOperationLastProperty(), new NumberStringConverter());
        // disable on no selection
        startOfOperationLastTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // latitude
        latitudeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().latitudeLabelTextProperty());
        latitudeTextField.textProperty().bindBidirectional(proxy.latitudeProperty(), new NumberStringConverter());
        // disable on no selection
        latitudeTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // longitude
        longitudeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().longitudeLabelTextProperty());
        longitudeTextField.textProperty().bindBidirectional(proxy.longitudeProperty(), new NumberStringConverter());
        // disable on no selection
        longitudeTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // status
        statusLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().statusLabelTextProperty());
        // disable on no selection
        statusChoiceBox.disableProperty().bind(rootPM.labelsEnabledProperty());

        // waterbodies
        waterbodiesLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().waterbodiesLabelTextProperty());
        waterbodiesTextField.textProperty().bindBidirectional(proxy.waterbodiesProperty());
        // disable on no selection
        waterbodiesTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // imageurl
        imageUrlLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().imageUrlLabelTextProperty());
        imageUrlTextField.textProperty().bindBidirectional(proxy.imageUrlProperty());
        // disable on no selection
        imageUrlTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        // input validation
        inputValidatorLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().inputValidationTextProperty());
        inputValidatorLabel.visibleProperty().bind(rootPM.invalidInputEnteredProperty());


    }

}
