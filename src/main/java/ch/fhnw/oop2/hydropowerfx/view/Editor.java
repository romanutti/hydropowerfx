package ch.fhnw.oop2.hydropowerfx.view;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Editor extends GridPane implements ViewMixin {
    private final RootPM rootPM;

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
    }

    @Override
    public void layoutControls() {

    }
}
