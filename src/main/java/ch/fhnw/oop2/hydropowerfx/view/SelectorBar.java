package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.LanguageSwitcherPM.*;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.StringConverter;

public class SelectorBar extends BorderPane implements ViewMixin {

    private final RootPM rootPM;

    private HBox titleArea;
    private ImageView imageArea;
    private Label titleLabel;
    private GridPane controlBar;
    private Button saveButton;
    private Button createButton;
    private Button deleteButton;
    private Button undoButton;
    private Button redoButton;
    private TextField searchField;
    private ChoiceBox languageChoiceBox;


    public SelectorBar(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        getStyleClass().add("selectorbar");
    }

    @Override
    public void initializeControls() {
        titleArea = new HBox();
        controlBar = new GridPane();
        saveButton = new Button();
        createButton = new Button();
        deleteButton = new Button();
        undoButton = new Button();
        redoButton = new Button();

        searchField = new TextField();
        languageChoiceBox = new ChoiceBox();

        titleLabel = new Label("title");
        titleLabel.setId("titleLabel");
    }

    @Override
    public void layoutControls() {
        saveButton.setMaxWidth(Double.MAX_VALUE);
        createButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        undoButton.setMaxWidth(Double.MAX_VALUE);
        redoButton.setMaxWidth(Double.MAX_VALUE);
        searchField.setMaxWidth(170);


        // margin
        setMargin(saveButton, new Insets(1));
        setMargin(createButton, new Insets(1));
        setMargin(deleteButton, new Insets(1));
        setMargin(undoButton, new Insets(1));
        setMargin(redoButton, new Insets(1));

        setMargin(searchField, new Insets(1));

        // padding
        setPadding(new Insets(5));

        // button images
        Image imageSave = new Image("/images/save_icon.png");
        ImageView imageSaveArea = new ImageView();
        imageSaveArea.setImage(imageSave);

        Image imageCreate = new Image("/images/create_icon.png");
        ImageView imageCreateArea = new ImageView();
        imageCreateArea.setImage(imageCreate);

        Image imageDelete = new Image("/images/delete_icon.png");
        ImageView imageDeleteArea = new ImageView();
        imageDeleteArea.setImage(imageDelete);

        Image imageUndo = new Image("/images/undo_icon.png");
        ImageView imageUndoArea = new ImageView();
        imageUndoArea.setImage(imageUndo);

        Image imageRedo = new Image("/images/redo_icon.png");
        ImageView imageRedoArea = new ImageView();
        imageRedoArea.setImage(imageRedo);

        imageSaveArea.setFitHeight(20);
        imageSaveArea.setFitWidth(20);

        imageCreateArea.setFitHeight(20);
        imageCreateArea.setFitWidth(20);

        imageDeleteArea.setFitHeight(20);
        imageDeleteArea.setFitWidth(20);

        imageRedoArea.setFitHeight(20);
        imageRedoArea.setFitWidth(20);

        imageUndoArea.setFitHeight(20);
        imageUndoArea.setFitWidth(20);

        saveButton.setGraphic(imageSaveArea);
        createButton.setGraphic(imageCreateArea);
        deleteButton.setGraphic(imageDeleteArea);
        undoButton.setGraphic(imageUndoArea);
        redoButton.setGraphic(imageRedoArea);

        ColumnConstraints cc = new ColumnConstraints();
        ColumnConstraints ccFix = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        ccFix.setHgrow(Priority.NEVER);
        controlBar.getColumnConstraints().addAll(ccFix, ccFix, ccFix, ccFix, ccFix, cc);

        controlBar.add(saveButton, 0, 0);
        controlBar.add(createButton, 1, 0);
        controlBar.add(deleteButton, 2, 0);
        controlBar.add(undoButton, 3, 0);
        controlBar.add(redoButton, 4, 0);
        controlBar.add(searchField, 5, 0,110,1);
        GridPane.setHalignment(searchField, HPos.RIGHT);

        controlBar.setPadding(new Insets(0,18,0,17));
        controlBar.setHgap(5);

        // image area
        imageArea = new ImageView();
        Image image = new Image("/images/drop_icon.png");
        imageArea.setImage(image);

        imageArea.setFitHeight(30);
        imageArea.setPreserveRatio(true);

        titleArea.getChildren().addAll(imageArea, titleLabel);
        titleArea.setAlignment(Pos.CENTER_LEFT);
        titleArea.setPadding(new Insets(10,0,50,12));

        setBottom(controlBar);
        setCenter(titleArea);
        setRight(languageChoiceBox);

        // Multilanguage-Support
        languageChoiceBox.setItems(FXCollections.observableArrayList(Lang.values()));
        languageChoiceBox.getSelectionModel().select(rootPM.getLanguageSwitcherPM().getCurrentLanguage());
    }

    @Override
    public void setupEventHandlers() {
        // TODO: allg. EventHandler vs Listener überprüfen!
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            rootPM.getFilteredPowerStations().setPredicate(powerStationPM -> {

                // If filter text is empty, display all powerstations.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Check if input value is number
                try {
                    Double numberFilter = Double.parseDouble(newValue);

                    if (powerStationPM.getId() == numberFilter) {
                        return true; // Filter matches id.
                    }

                    // TODO: Weitere Attribute in Filter nehmen?

                } catch (NumberFormatException e) {
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (powerStationPM.getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches name.
                    } else if (powerStationPM.getType().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches type.
                    } else if (powerStationPM.getCanton().getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches canton.
                    } else if (powerStationPM.getStatus().getName().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches status.
                    } else if (powerStationPM.getWaterbodies().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches waterbodies.
                    } else if (powerStationPM.getImageUrl().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches image url.
                    }
                }


                return false; // Does not match.
            });
        });

        deleteButton.setOnAction(event -> rootPM.removePowerStation());
        createButton.setOnAction(event -> rootPM.addPowerStation());

        undoButton.setOnAction(event -> rootPM.undo());
        redoButton.setOnAction(event -> rootPM.redo());
    }

    @Override
    public void setupValueChangedListeners() {
        // Multilanguage-Support
        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Lang>) (observable, oldValue, newValue) -> {
            rootPM.getLanguageSwitcherPM().setLanguage(newValue);
        });
    }

    @Override
    public void setupBindings() {
        // multilanguage support
        titleLabel.textProperty().bind(rootPM.getLanguageSwitcherPM().applicationNameProperty());
        searchField.promptTextProperty().bind(rootPM.getLanguageSwitcherPM().searchTextfieldTextProperty());

        // enable/disable buttons
        deleteButton.disableProperty().bind(rootPM.deleteEnabledProperty()); //TODO: Für weitere Buttons umsetzen
        undoButton.disableProperty().bind(rootPM.undoDisabledProperty());
        redoButton.disableProperty().bind(rootPM.redoDisabledProperty());
    }

}