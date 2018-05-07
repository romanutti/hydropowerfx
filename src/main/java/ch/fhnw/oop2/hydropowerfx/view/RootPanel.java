package ch.fhnw.oop2.hydropowerfx.view;

import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;

public class RootPanel extends StackPane implements ViewMixin {
    private final RootPM rootPM;

    private Button button1;
    private SplitPane horizontal;
    private SplitPane vertical;
    private CantonOverview cantonList;


    public RootPanel(RootPM model) {
        this.rootPM = model;
        initializeSelf();
        initializeControls();
        layoutControls();
        setupBindings();
        init();
    }

    @Override
    public void initializeSelf() {
        String stylesheet = getClass().getResource("style.css").toExternalForm();
        getStylesheets().add(stylesheet);
    }

    @Override
    public void initializeControls() {
        button1 = new Button();
        horizontal = new SplitPane();
        vertical = new SplitPane();
        cantonList = new CantonOverview();
    }

    @Override
    public void layoutControls() {
        getChildren().add(button1);
        horizontal.getItems().add(vertical);
        horizontal.getItems().add(cantonList);
        this.getChildren().add(horizontal);


    }

    @Override
    public void setupBindings() {
        button1.textProperty().bind(rootPM.getName());
    }
}
