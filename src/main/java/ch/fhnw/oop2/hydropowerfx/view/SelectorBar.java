package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SelectorBar extends HBox implements ViewMixin {

    private final RootPM rootPM;

    private Button saveButton;
    private Button createButton;
    private Button deleteButton;
    private Button undoButton;
    private Button redoButton;

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
        createButton = new Button ("create");
        deleteButton = new Button ("delete");
        undoButton = new Button ("undo");
        redoButton = new Button ("redo");
    }

    @Override
    public void layoutControls() {
        saveButton.setMaxWidth(Double.MAX_VALUE);
        createButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        undoButton.setMaxWidth(Double.MAX_VALUE);
        redoButton.setMaxWidth(Double.MAX_VALUE);

        setMargin(saveButton, new Insets(1));
        setMargin(createButton, new Insets(1));
        setMargin(deleteButton, new Insets(1));
        setMargin(undoButton, new Insets(1));
        setMargin(redoButton, new Insets(1));

        setPadding(new Insets((1)));
        setSpacing(1);

        getChildren().addAll(saveButton, createButton, deleteButton, undoButton, redoButton);

    }
}
