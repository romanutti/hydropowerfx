package ch.fhnw.oop2.hydropowerfx.view;

import javafx.scene.layout.StackPane;

public class CantonList extends StackPane {

    public CantonList(){
        initializeSelf();
        layoutControls();
        initializeControls();
        setupBindings();
    }

    private void initializeSelf() {
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    private void layoutControls() {

    }

    private void initializeControls() {

    }

    public void setupBindings() {

    }

}