package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.control.yearcontrol.BusinessControl;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.Canton;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStationPM.Type;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.Status;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class EditorView extends GridPane implements ViewMixin {
    // model
    private final RootPM rootPM;

    // gui elements
    private HBox imageUrlArea;
    private Button imageButton;

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
    private BusinessControl startOfOperationFirstTextField;
    private BusinessControl startOfOperationLastTextField;
    private TextField latitudeTextField;
    private TextField longitudeTextField;
    private ChoiceBox statusChoiceBox;
    private TextField waterbodiesTextField;
    private TextField imageUrlTextField;


    public EditorView(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("editor");
    }

    @Override
    public void initializeControls() {
        imageUrlArea = new HBox();
        imageButton = new Button();
        nameLabel = new Label();

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

        // textfields
        nameTextField = new TextField();
        typeChoiceBox = new ChoiceBox();
        siteTextField = new TextField();
        cantonChoiceBox = new ChoiceBox();
        maxWaterVolumeTextField = new TextField();
        maxPowerMwTextField = new TextField();
        startOfOperationFirstTextField = new BusinessControl(rootPM);
        startOfOperationLastTextField = new BusinessControl(rootPM);
        latitudeTextField = new TextField();
        longitudeTextField = new TextField();
        statusChoiceBox = new ChoiceBox();
        waterbodiesTextField = new TextField();
        imageUrlTextField = new TextField();

        // set ids
        imageButton.setId("imageButton");
        inputValidatorLabel.setId("inputValidatorLabel");
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
         IMAGE label formatting
         ********************************************************************************/
        // photo icon
        imageUrlLabel.setPadding(new Insets(0, 5, 0, 0));
        imageUrlArea.getChildren().addAll(imageUrlLabel, imageButton);

        /********************************************************************************
         GRID setup
         ********************************************************************************/
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
        add(imageUrlArea, 0, 7);
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
        /********************************************************************************
         ENUM functionality
         ********************************************************************************/
        // type enum
        typeChoiceBox.setItems(FXCollections.observableArrayList(Type.values()));
        typeChoiceBox.getSelectionModel().select(rootPM.getPowerStationProxy().getType());
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

            @Override
            public Status fromString(String string) {
                return null;
            }
        });

        statusChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Status>) (observable, oldValue, newValue) -> {
            rootPM.getPowerStationProxy().setStatus(newValue);
        });

        /********************************************************************************
         INPUT VALIDATION functionality
         ********************************************************************************/
        nameTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "String");
        });
        siteTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "String");
        });
        maxPowerMwTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "double");
        });
        maxWaterVolumeTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "double");
        });
        startOfOperationFirstTextField.valueProperty().addListener((observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue.toString(), "int");
        });
        startOfOperationLastTextField.valueProperty().addListener((observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue.toString(), "int");
        });
        longitudeTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "double");
        });
        latitudeTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "double");
        });
        waterbodiesTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "String");
        });
        imageUrlTextField.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
            rootPM.isValidInput(newValue, "url");
        });
    }

    @Override
    public void setupBindings() {
        PowerStationPM proxy = rootPM.getPowerStationProxy();

        /********************************************************************************
         BINDINGS
        ********************************************************************************/
        // name
        nameLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().nameLabelTextProperty());
        nameTextField.textProperty().bindBidirectional(proxy.nameProperty());
        // type
        typeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().typeLabelTextProperty());
        // site
        siteLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().siteLabelTextProperty());
        siteTextField.textProperty().bindBidirectional(proxy.siteProperty());
        // canton
        cantonLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().cantonLabelTextProperty());
        // maxwatervolume
        maxWaterVolumeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().maxWaterVolumeLabelTextProperty());
        maxWaterVolumeTextField.textProperty().bindBidirectional(proxy.maxWaterVolumeProperty(), new NumberStringConverter());
        // maxpower
        maxPowerMwLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().maxPowerMwLabelTextProperty());
        maxPowerMwTextField.textProperty().bindBidirectional(proxy.maxPowerMwProperty(), new NumberStringConverter());
        // operationfirst
        startOfOperationFirstLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationFirstLabelTextProperty());
        startOfOperationFirstTextField.valueProperty().bindBidirectional(proxy.startOfOperationFirstProperty());
        // operationlast
        startOfOperationLastLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().startOfOperationLastLabelTextProperty());
        startOfOperationLastTextField.valueProperty().bindBidirectional(proxy.startOfOperationLastProperty());
        // latitude
        latitudeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().latitudeLabelTextProperty());
        latitudeTextField.textProperty().bindBidirectional(proxy.latitudeProperty(), new NumberStringConverter());
        // longitude
        longitudeLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().longitudeLabelTextProperty());
        longitudeTextField.textProperty().bindBidirectional(proxy.longitudeProperty(), new NumberStringConverter());
        // status
        statusLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().statusLabelTextProperty());
        // waterbodies
        waterbodiesLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().waterbodiesLabelTextProperty());
        waterbodiesTextField.textProperty().bindBidirectional(proxy.waterbodiesProperty());
        // imageurl
        imageUrlLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().imageUrlLabelTextProperty());
        imageUrlTextField.textProperty().bindBidirectional(proxy.imageUrlProperty());

        /********************************************************************************
         DISABLE on no selection functionality
        ********************************************************************************/
        nameTextField.disableProperty().bind(rootPM.labelsEnabledProperty());
        typeChoiceBox.disableProperty().bind(rootPM.labelsEnabledProperty());
        siteTextField.disableProperty().bind(rootPM.labelsEnabledProperty());
        cantonChoiceBox.disableProperty().bind(rootPM.labelsEnabledProperty());
        maxWaterVolumeTextField.disableProperty().bind(rootPM.labelsEnabledProperty());
        maxPowerMwTextField.disableProperty().bind(rootPM.labelsEnabledProperty());
        startOfOperationFirstTextField.disableProperty().bind(rootPM.labelsEnabledProperty());
        startOfOperationLastTextField.disableProperty().bind(rootPM.labelsEnabledProperty());
        latitudeTextField.disableProperty().bind(rootPM.labelsEnabledProperty());
        longitudeTextField.disableProperty().bind(rootPM.labelsEnabledProperty());
        statusChoiceBox.disableProperty().bind(rootPM.labelsEnabledProperty());
        waterbodiesTextField.disableProperty().bind(rootPM.labelsEnabledProperty());
        imageUrlTextField.disableProperty().bind(rootPM.labelsEnabledProperty());

        /********************************************************************************
         HIDE image if no url entered
         ********************************************************************************/
        imageButton.visibleProperty().bind(rootPM.photoIconEnabledProperty());

        /********************************************************************************
         INPUT VALIDATION functionality
         ********************************************************************************/
        inputValidatorLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().inputValidationTextProperty());
        inputValidatorLabel.visibleProperty().bind(rootPM.invalidInputEnteredProperty());
    }

}
