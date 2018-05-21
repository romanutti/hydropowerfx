package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.LanguageSwitcherPM;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SelectorBar extends HBox implements ViewMixin {

    private final RootPM rootPM;

    private Button saveButton;
    private Button createButton;
    private Button deleteButton;
    private Button undoButton;
    private Button redoButton;
    private TextField searchField;
    private Button germanButton;
    private Button englishButton;


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
        saveButton = new Button("save");
        createButton = new Button("create");
        deleteButton = new Button("delete");
        undoButton = new Button("undo");
        redoButton = new Button("redo");

        searchField = new TextField("search");

        germanButton = new Button();
        englishButton = new Button();
    }

    @Override
    public void layoutControls() {
        saveButton.setMaxWidth(Double.MAX_VALUE);
        createButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        undoButton.setMaxWidth(Double.MAX_VALUE);
        redoButton.setMaxWidth(Double.MAX_VALUE);

        searchField.setMaxWidth(Double.MAX_VALUE);

        englishButton.setMaxWidth(Double.MAX_VALUE);
        germanButton.setMaxWidth(Double.MAX_VALUE);

        // margin
        setMargin(saveButton, new Insets(1));
        setMargin(createButton, new Insets(1));
        setMargin(deleteButton, new Insets(1));
        setMargin(undoButton, new Insets(1));
        setMargin(redoButton, new Insets(1));

        setMargin(searchField, new Insets(1));

        // padding
        setPadding(new Insets(5));
        setSpacing(5);

        getChildren().addAll(saveButton, createButton, deleteButton, undoButton, redoButton, searchField, germanButton, englishButton);

    }

    @Override
    public void setupEventHandlers() {
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
                    } else if (powerStationPM.getStatus().toLowerCase().contains(lowerCaseFilter)) {
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

        germanButton.setOnAction(event -> rootPM.getLanguageSwitcherPM().setLanguage(LanguageSwitcherPM.Lang.DE));
        englishButton.setOnAction(event -> rootPM.getLanguageSwitcherPM().setLanguage(LanguageSwitcherPM.Lang.EN));
    }

    @Override
    public void setupBindings() {
        germanButton.textProperty().bind(rootPM.getLanguageSwitcherPM().englishButtonTextProperty());
        englishButton.textProperty().bind(rootPM.getLanguageSwitcherPM().germanButtonTextProperty());
    }
}
