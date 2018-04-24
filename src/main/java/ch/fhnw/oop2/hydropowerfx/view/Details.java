package ch.fhnw.oop2.hydropowerfx.view;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;

public class Details extends StackPane {
    private SplitPane horizontal;
    private SplitPane vertical;
    private CantonList cantonList;

    public Details(){
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
        horizontal.getItems().add(vertical);
        horizontal.getItems().add(cantonList);
        this.getChildren().add(horizontal);
    }

    private void initializeControls() {
        horizontal = new SplitPane();
        vertical = new SplitPane();
        cantonList = new CantonList();
    }

    public void setupBindings() {

    }

}
